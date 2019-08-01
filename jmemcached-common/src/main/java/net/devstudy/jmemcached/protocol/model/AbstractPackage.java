package net.devstudy.jmemcached.protocol.model;

abstract class AbstractPackage {

    private byte[] data;

    AbstractPackage(byte[] data) {
        this.data = data;
    }

    AbstractPackage() {
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public final boolean hasData() {
        return data != null && data.length > 0;
    }
}
