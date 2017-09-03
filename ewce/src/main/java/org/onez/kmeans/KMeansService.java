package org.onez.kmeans;

import org.onez.cluster.Cluster;
import org.onez.data.DataItem;

import java.util.List;

/**
 * Created by joshanashakya on 8/9/17.
 */
public interface KMeansService {

    /**
     * Loads data from resource file and initialize cluster (phase I).
     *
     * @param source of data
     * @return initial centroids
     */
    List<DataItem> initialize(String source);

    /**
     * Creates cluster by recursively grouping data based on mean calculation (phase II).
     *
     * @return ClusterContext
     */
    List<Cluster> cluster(List<DataItem> centroids);

    /**
     * Prints the cluster of data.
     *
     * @param cluster
     * @return true if print is successful, otherwise false
     */
    boolean print(Cluster cluster);
}
