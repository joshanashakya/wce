package org.onez.cluster;

import org.onez.data.DataItem;

import java.util.List;

/**
 * Created by joshanashakya on 8/13/17.
 */
public class Cluster {
    private String name;

    private List<DataItem> dataItems;

    private DataItem centroid;

    public Cluster() {

    }

    public Cluster(String name, DataItem centroid) {
        this.name = name;
        this.centroid = centroid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DataItem> getDataItems() {
        return dataItems;
    }

    public void setDataItems(List<DataItem> dataItems) {
        this.dataItems = dataItems;
    }

    public DataItem getCentroid() {
        return centroid;
    }

    public void setCentroid(DataItem centroid) {
        this.centroid = centroid;
    }
}
