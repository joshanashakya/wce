package org.onez.kmeans;

import org.onez.cluster.Cluster;
import org.onez.cluster.ClusterContext;
import org.onez.cluster.ClusterContextImpl;
import org.onez.io.DataHandler;
import org.onez.iris.Iris;

import java.util.*;

/**
 * This class is the implementation of KMeansService. It contains methods required to cluster data based on K-Means
 * clustering algorithm.
 * <p>
 * Created by joshanashakya on 8/9/17.
 */
public class KMeansServiceImpl implements KMeansService {

    public ClusterContext initialize(String source) {
        ClusterContext context;
        try {
            // read data
            String[] data = DataHandler.loadData (source).split ("::");
            int numOfCluster = Integer.parseInt (data[0]);

            List<Iris> irisList = new ArrayList<> ();
            for (int i = 1; i < data.length; i++) {
                String[] values = data[i].split (",");
                irisList.add (new Iris (i, Double.parseDouble (values[0]), Double.parseDouble (values[1]), Double.parseDouble (values[2]), Double.parseDouble (values[3])));
            }

            System.out.println ("Number of clusters : " + numOfCluster);
            System.out.println ("Data size : " + irisList.size ());

            // initialize cluster context
            context = new ClusterContextImpl (numOfCluster, irisList);
            Map<String, Cluster> clusterByName = generateRandomCentroid (context, numOfCluster, irisList);
            System.out.println ("\nInitialization phase : ");
            for (Map.Entry<String, Cluster> entry : clusterByName.entrySet ()) {
                System.out.println (entry.getValue ().toString ());
            }
            context.setClusters (new ArrayList<> (clusterByName.values ()));

            return context;
        } catch (Exception ex) {
            context = new ClusterContextImpl (0, Collections.<Iris>emptyList ());
            ex.printStackTrace ();
        }
        return context;
    }

    public List<Cluster> cluster(ClusterContext context) {
        List<Iris> irisList = context.getIrisList ();
        boolean endIteration = false;
        int i = 1;
        while (true) {
            // get previous centroid
            Map<String, Iris> centroidByClusterName = getCentroids (context.getClusters ());

            // assign data to cluster
            List<Cluster> clusters = generateClusters (context.getClusters (), irisList);

            // check if centroids are equal
            System.out.println ("Iteration " + i);
            for (Cluster cluster : clusters) {
                System.out.println (cluster.toString ());
                if (cluster.getCentroid ().equals (centroidByClusterName.get (cluster.getName ()))) {
                    endIteration = true;
                    continue;
                }
                endIteration = false;
            }

            // set newly calculated cluster in context
            context.setClusters (clusters);
            if (endIteration) {
                break;
            }
            i++;
        }
        return context.getClusters ();
    }

    private List<Cluster> generateClusters(List<Cluster> clusters, List<Iris> irisList) {
        // clean cluster
        for (Cluster cluster : clusters) {
            cluster.setIrisList (new ArrayList<Iris> ());
        }

        Map<String, Cluster> clusterByName = new HashMap<> ();
        for (Iris iris : irisList) {
            // find nearest cluster
            Cluster cluster = nearestCluster (clusters, iris);
            // add data to nearest cluster
            cluster.getIrisList ().add (iris);
            clusterByName.put (cluster.getName (), cluster);
        }
        // calculate centroid of new cluster
        for (Map.Entry<String, Cluster> entry : clusterByName.entrySet ()) {
            Cluster newCluster = entry.getValue ();
            Iris centroid = calculateCentroid (newCluster);
            newCluster.setCentroid (centroid);
            clusterByName.put (newCluster.getName (), newCluster);
        }
        return new ArrayList<> (clusterByName.values ());
    }

    public boolean print(Cluster cluster) {
        System.out.println (cluster.toString ());
        return true;
    }

    Iris calculateCentroid(Cluster cluster) {
        List<Iris> irisList = cluster.getIrisList ();
        double petalLen = 0;
        double petalWid = 0;
        double sepalLen = 0;
        double sepalWid = 0;
        double size = irisList.size ();
        for (Iris iris : irisList) {
            sepalLen += iris.getSepalLen ();
            sepalWid += iris.getSepalWid ();
            petalLen += iris.getPetalLen ();
            petalWid += iris.getPetalWid ();
        }
        return new Iris (sepalLen / size, sepalWid / size, petalLen / size, petalWid / size);
    }

    /**
     * @param clusters
     * @param iris
     * @return nearest cluster
     */
    Cluster nearestCluster(List<Cluster> clusters, Iris iris) {
        Cluster nearest = clusters.get (0);
        double distance = Double.MAX_VALUE;
        for (Cluster cluster : clusters) {
            Iris centroid = cluster.getCentroid ();
            double calculatedVal = calculateEuclideanDistance (iris, centroid);
            if (calculatedVal < distance) {
                nearest = cluster;
                distance = calculatedVal;
            }
        }
        return nearest;
    }

    /**
     * @param iris
     * @param centroid
     * @return distance value calculated in double
     */
    double calculateEuclideanDistance(Iris iris, Iris centroid) {
        return Math.pow (Math.pow (iris.getPetalLen () - centroid.getPetalLen (), 2) + Math.pow (iris.getPetalWid () - centroid.getPetalWid (), 2) + Math.pow (iris.getSepalLen () - centroid.getSepalLen (), 2) + Math.pow (iris.getSepalWid () - centroid.getSepalWid (), 2), 0.5);
    }

    private Map<String, Cluster> generateRandomCentroid(ClusterContext context, int numOfCluster, List<Iris> irisList) {
        Map<String, Cluster> clusterByName = new HashMap<> (numOfCluster);
        for (int i = 0; i < numOfCluster; i++) {
            int random = new Random ().nextInt (context.getIrisList ().size ());
            String clusterName = String.format ("Cluster(%s)", i + 1);
            clusterByName.put (clusterName, new Cluster (clusterName, irisList.get (random)));
        }
        return clusterByName;
    }

    private Map<String, Iris> getCentroids(List<Cluster> clusters) {
        Map<String, Iris> centroidByClusterName = new HashMap<> ();
        for (Cluster cluster : clusters) {
            centroidByClusterName.put (cluster.getName (), cluster.getCentroid ());
        }
        return centroidByClusterName;
    }
}
