package org.onez.cluster;

import org.onez.data.DataItem;

import java.util.List;

/**
 * Created by joshanashakya on 8/9/17.
 */
public interface ClusterContext {

    int getNumberOfCluster();

    List<DataItem> getDataItems();

    List<Cluster> getClusters();

    void setNumberOfCluster(int numberOfCluster);

    void setDataItems(List<DataItem> dataItems);

    void setClusters(List<Cluster> clusters);
}
