package org.onez.kmeans;

import org.onez.cluster.Cluster;
import org.onez.data.DataItem;
import org.onez.data.DataItemValue;
import org.onez.io.DataHandler;

import java.util.*;

/**
 * This class is the implementation of KMeansService. It contains methods required to cluster data based on K-Means
 * clustering algorithm.
 * <p>
 * Created by joshanashakya on 8/9/17.
 */
public class EKMeansServiceImpl implements KMeansService {

    private int numberOfCluster;

    private List<DataItem> dataItems;

    private List<Cluster> clusters;

    public List<DataItem> initialize(String source) {
        try {
            // read data
            String[] data = DataHandler.loadData (source).split ("::");
            numberOfCluster = Integer.parseInt (data[0]);

            dataItems = new ArrayList<> ();
            for (int i = 1; i < data.length; i++) {
                String[] values = data[i].split (",");
                dataItems.add (new DataItem (i, new double[]{Double.parseDouble (values[0]), Double.parseDouble (values[1]), Double.parseDouble (values[2]), Double.parseDouble (values[3])}));
            }

            System.out.println ("Number of clusters : " + numberOfCluster);
            System.out.println ("Data size : " + dataItems.size ());

            // create set from data items
            List<List<DataItem>> setList = getItemSetList ();

            List<DataItem> centroids = new ArrayList<> ();
            for (List<DataItem> set : setList) {
                centroids.add (calculateCentroid (set));
            }
            return centroids;

        } catch (Exception ex) {
            ex.printStackTrace ();
        }
        return Collections.emptyList ();
    }

    private List<List<DataItem>> getItemSetList() {
        List<DataItem> dataItemsCopy = new ArrayList<> (dataItems);
        int size = (int) (0.75 * (dataItems.size () / numberOfCluster));
        List<List<DataItem>> setList = new ArrayList<> ();
        for (int m = 0; m < numberOfCluster; m++) {
            List<DataItem> itemSet = new ArrayList<> ();
            for (int i = 0; i < size; i = i + 2) {
                DataItemValue dataItemValue = getItemSet (dataItemsCopy);
                itemSet.add (dataItemValue.getFrom ());
                itemSet.add (dataItemValue.getTo ());
                dataItemsCopy.remove (dataItemValue.getFrom ());
                dataItemsCopy.remove (dataItemValue.getTo ());
            }
            setList.add (itemSet);
        }
        return setList;
    }

    private DataItem calculateCentroid(List<DataItem> set) {
        DataItem centroid = new DataItem ();
        for (DataItem item : set) {
            centroid.add (item.getValues ());
        }
        centroid.divide (set.size ());
        return centroid;
    }

    @Override
    public List<Cluster> cluster(List<DataItem> centroids) {
        Map<String, Cluster> clusterById = new HashMap<> ();
        for (int i = 0; i < centroids.size (); i++) {
            String id = String.format ("Cluster(%s)", i + 1);
            clusterById.put (id, new Cluster (id, centroids.get (i)));
        }
        for (DataItem dataItem : dataItems) {
            Cluster cluster = nearestCluster (new ArrayList<> (clusterById.values ()), dataItem);

            dataItem.setClusterId (cluster.getId ());
            clusterById.get (cluster.getId ()).getDataItems ().add (dataItem);
        }

        boolean endIteration = false;
        int i = 1;

        while (true) {

            System.out.println ("Iteration " + i);

            for (Map.Entry<String, Cluster> entry : clusterById.entrySet ()) {
                Cluster cluster = entry.getValue ();
                DataItem newCentroid = calculateCentroid (cluster.getDataItems ());
                if (cluster.getCentroid ().equals (newCentroid)) {
                    endIteration = true;
                    continue;
                }
                endIteration = false;
                cluster.setCentroid (newCentroid);
                clusterById.put (cluster.getId (), cluster);
            }

            for (DataItem dataItem : dataItems) {
                Cluster presentCluster = clusterById.get (dataItem.getClusterId ());
                double presentDistance = calculateEuclideanDistance (dataItem, presentCluster.getCentroid ());
                Cluster newCluster = nearestCluster (new ArrayList<Cluster> (clusterById.values ()), dataItem);
                double newDistance = calculateEuclideanDistance (dataItem, newCluster.getCentroid ());
                if (presentDistance > newDistance) {
                    presentCluster.getDataItems ().remove (dataItem);
                    dataItem.setClusterId (newCluster.getId ());
                    newCluster.getDataItems ().add (dataItem);
                    clusterById.put (newCluster.getId (), newCluster);
                }
            }

            List<Cluster> clusters = new ArrayList<> (clusterById.values ());
            for (Cluster cluster : clusters) {
                System.out.println (cluster.toString ());
            }

            if (endIteration) {
                break;
            }
            i++;
        }

        return new ArrayList<> (clusterById.values ());
    }

    @Override
    public boolean print(Cluster cluster) {
        return false;
    }

    private DataItemValue getItemSet(List<DataItem> dataItems) {
        List<DataItemValue> values = new ArrayList<> ();
        for (DataItem fromDataItem : dataItems) {
            double minDis = Double.MAX_VALUE;
            DataItemValue val = new DataItemValue ();
            for (DataItem toDataItem : dataItems) {
                // do not calculate distance between same data point
                if (fromDataItem.getId () == toDataItem.getId ()) {
                    continue;
                }
                double distance = calculateEuclideanDistance (fromDataItem, toDataItem);
                if (minDis > distance) {
                    minDis = distance;
                    val.setFrom (fromDataItem);
                    val.setTo (toDataItem);
                    val.setMinValue (minDis);
                }
            }
            values.add (val);
        }
        Collections.sort (values, new Comparator<DataItemValue> () {
            @Override
            public int compare(DataItemValue o1, DataItemValue o2) {
                if (o1.getMinValue () > o2.getMinValue ()) {
                    return 1;
                } else if (o1.getMinValue () < o2.getMinValue ()) {
                    return -1;
                }
                return 0;
            }
        });
        return values.get (0);
    }

    Cluster nearestCluster(List<Cluster> clusters, DataItem dataItem) {
        Cluster nearest = clusters.get (0);
        double distance = Double.MAX_VALUE;
        for (Cluster cluster : clusters) {
            DataItem centroid = cluster.getCentroid ();
            double calculatedVal = calculateEuclideanDistance (dataItem, centroid);
            if (calculatedVal < distance) {
                nearest = cluster;
                distance = calculatedVal;
            }
        }
        return nearest;
    }

    double calculateEuclideanDistance(DataItem from, DataItem to) {
        double[] fromValues = from.getValues ();
        double[] toValues = to.getValues ();
        return Math.pow (Math.pow (fromValues[0] - toValues[0], 2) + Math.pow (fromValues[1] - toValues[1], 2) + Math.pow (fromValues[2] - toValues[2], 2) + Math.pow (fromValues[3] - toValues[3], 2), 0.5);
    }
}
