package org.onez;

import org.onez.cluster.Cluster;
import org.onez.cluster.ClusterContext;
import org.onez.kmeans.KMeansService;
import org.onez.kmeans.KMeansServiceImpl;

import java.util.List;

/**
 * Main class for doing everthing.
 * <p>
 * Created by joshanashakya on 8/10/17.
 */
public class App {

    public static void main(String[] args) {
        long pretime = System.currentTimeMillis ();
        KMeansService service = new KMeansServiceImpl ();
        ClusterContext context = service.initialize ("new-iris.data");
        List<Cluster> clusters = service.cluster (context);
        System.out.println ("---------- Completion phase ----------");
        for (Cluster cluster : clusters) {
            service.print (cluster);
        }
        long nexttime = System.currentTimeMillis ();

        System.out.println ("Time " + (nexttime - pretime) + " milliseconds.");
    }
}
