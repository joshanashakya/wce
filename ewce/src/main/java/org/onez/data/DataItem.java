package org.onez.data;

/**
 * Created by joshanashakya on 8/13/17.
 */
public class DataItem {

    private int id;

    private double[] values;

    private String clusterId;

    public DataItem () {
        this.values = new double[4];
        this.values[0] = 0;
        this.values[1] = 0;
        this.values[2] = 0;
        this.values[3] = 0;
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

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    @Override
    public String toString() {
        return String.format ("[%d : %.1f, %.1f, %.1f, %.1f]", id, values[0], values[1], values[2], values[3]);
    }

    public double[] add(double[] values) {
        this.values[0] += values[0];
        this.values[1] += values[1];
        this.values[2] += values[2];
        this.values[3] += values[3];
        return this.values;
    }

    public void divide (int size) {
        this.values[0] = this.values[0]/size;
        this.values[1] = this.values[1]/size;
        this.values[2] = this.values[2]/size;
        this.values[3] = this.values[3]/size;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof DataItem)) {
            return false;
        }

        DataItem dataItem = (DataItem) obj;
        double[] itemValues = dataItem.getValues ();

        return itemValues[0] == values[0] && itemValues[1] == values[1] && itemValues[2] == values[2] && itemValues[3] == values[3];
    }

    @Override
    public int hashCode() {
        int result = 17;
        long value0 = Double.doubleToLongBits (values[0]);
        result = 31 * result + (int) (value0 ^ (value0 >>> 32));
        long value1 = Double.doubleToLongBits (values[1]);
        result = 31 * result + (int) (value1 ^ (value1 >>> 32));
        long value2 = Double.doubleToLongBits (values[2]);
        result = 31 * result + (int) (value2 ^ (value2 >>> 32));
        long value3 = Double.doubleToLongBits (values[3]);
        result = 31 * result + (int) (value3 ^ (value3 >>> 32));
        return result;
    }
}
