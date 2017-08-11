package org.onez.cluster;

import org.onez.iris.Iris;

import java.util.List;

/**
 * Created by joshanashakya on 8/9/17.
 */
public class ClusterContextImpl implements ClusterContext {

    private int numberOfCluster;

    private List<Iris> irisList;

    private List<Cluster> clusters;

    public ClusterContextImpl(int numberOfCluster, List<Iris> irisList) {
        this.numberOfCluster = numberOfCluster;
        this.irisList = irisList;
    }

    public int getNumberOfCluster() {
        return numberOfCluster;
    }

    public void setNumberOfCluster(int numberOfCluster) {
        this.numberOfCluster = numberOfCluster;
    }

    public List<Iris> getIrisList() {
        return irisList;
    }

    public void setIrisList(List<Iris> irisList) {
        this.irisList = irisList;
    }

    public List<Cluster> getClusters() {
        return this.clusters;
    }

    public void setClusters(List<Cluster> clusters) {
        this.clusters = clusters;
    }
}
