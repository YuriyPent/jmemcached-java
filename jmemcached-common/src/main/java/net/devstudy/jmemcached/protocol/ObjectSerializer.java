package net.devstudy.jmemcached.protocol;

public interface ObjectSerializer {

    byte[] toByteArray(Object object);

    Object fromByteArray(byte[] array);
}
