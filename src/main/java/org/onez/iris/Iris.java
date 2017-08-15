package org.onez.iris;

/**
 * This class stores data of iris plant.
 * <p>
 * Created by joshanashakya on 8/9/17.
 */
public class Iris {

    private int id;

    private double sepalLen;

    private double sepalWid;

    private double petalLen;

    private double petalWid;

    public Iris() {

    }

    public Iris(double sepalLen, double sepalWid, double petalLen, double petalWid) {
        this.sepalLen = sepalLen;
        this.sepalWid = sepalWid;
        this.petalLen = petalLen;
        this.petalWid = petalWid;
    }

    public Iris(int id, double sepalLen, double sepalWid, double petalLen, double petalWid) {
        this.id = id;
        this.sepalLen = sepalLen;
        this.sepalWid = sepalWid;
        this.petalLen = petalLen;
        this.petalWid = petalWid;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Iris)) {
            return false;
        }

        Iris iris = (Iris) obj;

        return iris.getId () == id && iris.getSepalLen () == sepalLen && iris.getSepalWid () == sepalWid && iris.getPetalLen () == petalLen && iris.getPetalWid () == petalWid;
    }

    @Override
    public int hashCode() {
        int result = 17;
        long fsepanLen = Double.doubleToLongBits (sepalLen);
        result = 31 * result + (int) (fsepanLen ^ (fsepanLen >>> 32));
        long fsepalWid = Double.doubleToLongBits (sepalWid);
        result = 31 * result + (int) (fsepalWid ^ (fsepalWid >>> 32));
        long fpetalLen = Double.doubleToLongBits (petalLen);
        result = 31 * result + (int) (fpetalLen ^ (fpetalLen >>> 32));
        long fpetalWid = Double.doubleToLongBits (petalWid);
        result = 31 * result + (int) (fpetalWid ^ (fpetalWid >>> 32));
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSepalLen() {
        return sepalLen;
    }

    public void setSepalLen(double sepalLen) {
        this.sepalLen = sepalLen;
    }

    public double getSepalWid() {
        return sepalWid;
    }

    public void setSepalWid(double sepalWid) {
        this.sepalWid = sepalWid;
    }

    public double getPetalLen() {
        return petalLen;
    }

    public void setPetalLen(double petalLen) {
        this.petalLen = petalLen;
    }

    public double getPetalWid() {
        return petalWid;
    }

    public void setPetalWid(double petalWid) {
        this.petalWid = petalWid;
    }
}
