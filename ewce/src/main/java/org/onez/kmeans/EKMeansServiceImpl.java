package org.onez.kmeans;

import org.onez.cluster.Cluster;
import org.onez.cluster.ClusterContext;
import org.onez.cluster.ClusterContextImpl;
import org.onez.data.DataItem;
import org.onez.data.DataItemValue;
import org.onez.io.DataHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class is the implementation of KMeansService. It contains methods required to cluster data based on K-Means
 * clustering algorithm.
 * <p>
 * Created by joshanashakya on 8/9/17.
 */
public class EKMeansServiceImpl implements KMeansService {

    public ClusterContext initialize(String source) {
        ClusterContextImpl context;
        try {
            // read data
            String[] data = DataHandler.loadData (source).split ("::");
            int numOfCluster = Integer.parseInt (data[0]);

            List<DataItem> dataItems = new ArrayList<> ();
            for (int i = 1; i < data.length; i++) {
                String[] values = data[i].split (",");
                dataItems.add (new DataItem (i, new double[]{Double.parseDouble (values[0]), Double.parseDouble (values[1]), Double.parseDouble (values[2]), Double.parseDouble (values[3])}));
            }

            System.out.println ("Number of clusters : " + numOfCluster);
            System.out.println ("Data size : " + dataItems.size ());

            // initialize cluster context
            context = new ClusterContextImpl (numOfCluster, dataItems);
            int size = (int) 0.75 * (dataItems.size () / numOfCluster);

            List<List<DataItem>> items = new ArrayList<> ();
            for (int m = 0; m < numOfCluster; m++) {
                List<DataItem> aSet = new ArrayList<> ();
                for (int i = 0; i < size; i++) {
                    DataItemValue dataItemValue = getItemSet (dataItems);
                    aSet.add (dataItemValue.getFrom ());
                    aSet.add (dataItemValue.getTo ());
                    dataItems.remove (dataItemValue.getFrom ());
                    dataItems.remove (dataItemValue.getTo ());
                }
                items.add (aSet);
            }
        } catch (Exception ex) {
            ex.printStackTrace ();
        }
        return null;
    }

    @Override
    public List<Cluster> cluster(ClusterContext context) {
        return null;
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

    //    public List<Cluster> cluster(ClusterContext context) {
//        Map<String, Iris> centroidByClusterName = new HashMap<> ();
//        Map<String, Cluster> clusterByName = new HashMap<> ();
//
//        while (true) {
//            boolean end = true;
//            List<Cluster> clusters = context.getClusters ();
//            for (Cluster cluster : clusters) {
//                centroidByClusterName.put (cluster.getName (), cluster.getCentroid ());
//                Iris centroid = calculateCentroid (cluster);
//                if (centroid.equals (centroidByClusterName.get (cluster.getName ()))) {
//                    end = false;
//                    centroidByClusterName.put (cluster.getName (), centroid);
//                }
//            }
//
//            if (end) {
//                break;
//            }
//
//            List<Iris> irisList = context.getIrisList ();
//            for (Iris iris : irisList) {
//                Cluster cluster = nearestCluster (clusters, iris);
//                cluster.getIrisList ().add (iris);
//                clusterByName.put (cluster.getName (), cluster);
//            }
//            context.setClusters (new ArrayList<> (clusterByName.values ()));
//        }
//        return context.getClusters ();
//    }
//
//    public boolean print(Cluster cluster) {
//        return false;
//    }
//
//    Iris calculateCentroid(Cluster cluster) {
//        List<Iris> irisList = cluster.getIrisList ();
//        double petalLen = 0;
//        double petalWid = 0;
//        double sepalLen = 0;
//        double sepalWid = 0;
//        double size = irisList.size ();
//        for (Iris iris : irisList) {
//            sepalLen += iris.getSepalLen ();
//            sepalWid += iris.getSepalWid ();
//            petalLen += iris.getPetalLen ();
//            petalWid += iris.getPetalWid ();
//        }
//        return new Iris (sepalLen / size, sepalWid / size, petalLen / size, petalWid / size);
//    }
//
//    /**
//     * @param clusters
//     * @param iris
//     * @return nearest cluster
//     */
//    Cluster nearestCluster(List<Cluster> clusters, Iris iris) {
//        Cluster nearest = clusters.get (0);
//        double distance = Double.MAX_VALUE;
//        for (Cluster cluster : clusters) {
//            Iris centroid = cluster.getCentroid ();
//            double calculatedVal = calculateEuclideanDistance (iris, centroid);
//            if (calculatedVal < distance) {
//                nearest = cluster;
//                distance = calculatedVal;
//            }
//        }
//        return nearest;
//    }
//
//    /**
//     * @param iris
//     * @param centroid
//     * @return distance value calculated in double
//     */
    double calculateEuclideanDistance(DataItem from, DataItem to) {
        double[] fromValues = from.getValues ();
        double[] toValues = to.getValues ();
        return Math.pow (Math.pow (fromValues[0] - toValues[0], 2) + Math.pow (fromValues[1] - toValues[1], 2) + Math.pow (fromValues[2] - toValues[2], 2) + Math.pow (fromValues[3] - toValues[3], 2), 0.5);
    }
}
