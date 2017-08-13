package org.onez.data;

/**
 * Created by joshanashakya on 8/13/17.
 */
public class DataItem {

    private int id;

    private double[] values;

    public DataItem () {

    }

    public DataItem (int id, double[] values) {
        this.id = id;
        this.values = values;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }
}
