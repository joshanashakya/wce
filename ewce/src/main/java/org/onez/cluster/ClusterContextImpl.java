package org.onez.cluster;

import org.onez.data.DataItem;

import java.util.List;

/**
 * Created by joshanashakya on 8/9/17.
 */
public class ClusterContextImpl implements ClusterContext {

    private int numberOfCluster;

    private List<DataItem> dataItems;

    private List<Cluster> clusters;

    public ClusterContextImpl(int numberOfCluster, List<DataItem> dataItems) {
        this.numberOfCluster = numberOfCluster;
        this.dataItems = dataItems;
    }

    @Override
    public int getNumberOfCluster() {
        return this.numberOfCluster;
    }

    @Override
    public List<DataItem> getDataItems() {
        return this.dataItems;
    }

    @Override
    public List<Cluster> getClusters() {
        return this.clusters;
    }

    @Override
    public void setNumberOfCluster(int numberOfCluster) {
        this.numberOfCluster = numberOfCluster;
    }

    @Override
    public void setDataItems(List<DataItem> dataItems) {
        this.dataItems = dataItems;
    }

    @Override
    public void setClusters(List<Cluster> clusters) {
        this.clusters = clusters;
    }
}
