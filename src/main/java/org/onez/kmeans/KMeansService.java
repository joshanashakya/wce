package org.onez.kmeans;

import org.onez.cluster.Cluster;
import org.onez.cluster.ClusterContext;
import org.onez.cluster.ClusterContextImpl;

import java.util.List;

/**
 * Created by joshanashakya on 8/9/17.
 */
public interface KMeansService {

    /**
     * Loads data from resource file and initialize cluster (phase I).
     *
     * @param source of data
     * @return ClusterContext
     */
    ClusterContext initialize(String source);

    /**
     * Creates cluster by recursively grouping data based on mean calculation (phase II).
     *
     * @return ClusterContext
     */
    List<Cluster> cluster(ClusterContext context);

    /**
     * Prints the cluster of data.
     *
     * @param cluster
     * @return true if print is successful, otherwise false
     */
    boolean print(Cluster cluster);
}
