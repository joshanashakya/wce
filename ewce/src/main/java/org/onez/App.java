package org.onez;

import org.onez.cluster.Cluster;
import org.onez.data.DataItem;
import org.onez.kmeans.EKMeansServiceImpl;
import org.onez.kmeans.KMeansService;

import java.util.Date;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        long pretime = System.currentTimeMillis ();
        KMeansService service = new EKMeansServiceImpl ();
        List<DataItem> centroids = service.initialize ("iris.data");
        System.out.println ("Initial centroids: ");
        for (DataItem item : centroids) {
            System.out.println (item.toString ());
        }
        System.out.println ("---------- Completion phase ----------");
        List<Cluster> clusters = service.cluster (centroids);
        for (Cluster cluster : clusters) {
            System.out.println (cluster.toString ());
        }
        long nexttime = System.currentTimeMillis ();

        System.out.println ("Time " + (nexttime - pretime) + " milliseconds.");
    }
}
