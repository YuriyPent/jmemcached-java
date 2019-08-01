package net.devstudy.jmemcached.client;

import net.devstudy.jmemcached.protocol.ObjectSerializer;
import net.devstudy.jmemcached.protocol.RequestConverter;
import net.devstudy.jmemcached.protocol.ResponseConverter;

public interface ClientConfig {

    String getHost();

    int getPort();

    RequestConverter getRequestConverter();

    ResponseConverter getResponseConverter();

    ObjectSerializer getObjectSerializer();
}
