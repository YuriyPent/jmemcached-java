package net.devstudy.jmemcached.client.impl;

import net.devstudy.jmemcached.client.ClientConfig;
import net.devstudy.jmemcached.protocol.ObjectSerializer;
import net.devstudy.jmemcached.protocol.RequestConverter;
import net.devstudy.jmemcached.protocol.ResponseConverter;
import net.devstudy.jmemcached.protocol.impl.DefaultObjectSerializer;
import net.devstudy.jmemcached.protocol.impl.DefaultRequestConverter;
import net.devstudy.jmemcached.protocol.impl.DefaultResponseConverter;

/**
 * Created by yurap on 09.01.2019.
 */
class DefaultClientConfig implements ClientConfig {
    private final String host;
    private final int port;
    private final RequestConverter requestConverter;
    private final ResponseConverter responseConverter;
    private final ObjectSerializer objectSerializer;

    DefaultClientConfig(String host, int port) {
        this.host = host;
        this.port = port;
        this.requestConverter = new DefaultRequestConverter();
        this.responseConverter = new DefaultResponseConverter();
        this.objectSerializer = new DefaultObjectSerializer();
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public RequestConverter getRequestConverter() {
        return requestConverter;
    }

    @Override
    public ResponseConverter getResponseConverter() {
        return responseConverter;
    }

    @Override
    public ObjectSerializer getObjectSerializer() {
        return objectSerializer;
    }
}