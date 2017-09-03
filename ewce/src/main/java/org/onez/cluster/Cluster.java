package org.onez.cluster;

import org.onez.data.DataItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joshanashakya on 8/13/17.
 */
public class Cluster {

    private String id;

    private List<DataItem> dataItems = new ArrayList<> ();

    private DataItem centroid;

    public Cluster() {

    }

    public Cluster(String id, DataItem centroid) {
        this.id = id;
        this.centroid = centroid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder ("Cluster Name : ");
        sb.append (this.id);
        sb.append ("\n");
        sb.append ("Cluster Size : ");
        sb.append (this.dataItems.size ());
        sb.append ("\n");
        sb.append (String.format ("Cluster Centroid : [%.1f, %.1f, %.1f, %.1f]", this.centroid.getValues ()[0], this.centroid.getValues ()[1], this.centroid.getValues ()[2], this.centroid.getValues ()[3]));
        sb.append ("\n");
        for (DataItem item : this.dataItems) {
            sb.append (String.format ("[%d : %.1f, %.1f, %.1f, %.1f]\n", item.getId (), item.getValues ()[0], item.getValues ()[1], item.getValues ()[2], item.getValues ()[3]));
        }
        sb.append ("\n");
        return sb.toString ();
    }
}
