package org.onez.cluster;

import org.onez.iris.Iris;

import java.util.List;

/**
 * Created by joshanashakya on 8/9/17.
 */
public interface ClusterContext {

    int getNumberOfCluster();

    List<Iris> getIrisList();

    List<Cluster> getClusters();

    void setNumberOfCluster(int numberOfCluster);

    void setIrisList(List<Iris> irisList);

    void setClusters(List<Cluster> clusters);
}
