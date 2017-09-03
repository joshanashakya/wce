package org.onez.cluster;

import org.onez.iris.Iris;

import java.util.ArrayList;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder ("Cluster Name : ");
        sb.append (this.name);
        sb.append ("\n");
        sb.append ("Cluster Size : ");
        sb.append (this.irisList.size ());
        sb.append ("\n");
        sb.append (String.format ("Cluster Centroid : [%.1f, %.1f, %.1f, %.1f]", this.centroid.getSepalLen (), this.centroid.getSepalWid (), this.centroid.getPetalLen (), this.centroid.getPetalWid ()));
        sb.append ("\n");
        for (Iris iris : this.irisList) {
            sb.append (String.format ("[%d : %.1f, %.1f, %.1f, %.1f]\n", iris.getId (), iris.getSepalLen (), iris.getSepalWid (), iris.getPetalLen (), iris.getPetalWid ()));
        }
        sb.append ("\n");
        return sb.toString ();
    }
}
