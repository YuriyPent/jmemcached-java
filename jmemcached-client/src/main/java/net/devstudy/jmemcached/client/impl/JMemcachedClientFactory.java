package net.devstudy.jmemcached.client.impl;

import net.devstudy.jmemcached.client.Client;

import java.io.IOException;

/**
 * Created by yurap on 09.01.2019.
 */
public class JMemcachedClientFactory {
    public static Client buildNewClient(String host, int port) throws IOException {
        return new DefaultClient(new DefaultClientConfig(host, port));
    }

    public static Client buildNewClient(String host) throws IOException {
        return buildNewClient(host, 9010);
    }

    public static Client buildNewClient() throws IOException {
        return buildNewClient("localhost");
    }
}
