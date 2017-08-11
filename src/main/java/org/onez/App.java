package org.onez;

import org.onez.cluster.Cluster;
import org.onez.cluster.ClusterContextImpl;
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
        KMeansService service = new KMeansServiceImpl ();
        ClusterContextImpl context = service.initialize ("iris.data");
        List<Cluster> clusterList = service.cluster (context);
        for (Cluster cluster : clusterList) {
            System.out.println (cluster.getIrisList ());
        }
    }
}
