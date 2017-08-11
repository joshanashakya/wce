package org.onez.cluster;

import org.onez.iris.Iris;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class contains fields and methods required for single cluster.
 * <p>
 * Created by joshanashakya on 8/9/17.
 */
public class Cluster {

    private String name;

    private List<Iris> irisList = new ArrayList<> ();

    private Iris centroid;

    public Cluster() {

    }

    public Cluster(String name, Iris centroid) {
        this.name = name;
        this.centroid = centroid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Iris> getIrisList() {
        return irisList;
    }

    public void setIrisList(List<Iris> irisList) {
        this.irisList = irisList;
    }

    public Iris getCentroid() {
        return centroid;
    }

    public void setCentroid(Iris centroid) {
        this.centroid = centroid;
    }
}
