package net.devstudy.jmemcached.server;

import net.devstudy.jmemcached.server.impl.JmemcachedServerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServiceWrapper {

    private static final Server server = createServer();

    private static Server createServer() {
        return JmemcachedServerFactory.buildNewServer(getServerProperties());
    }

    private static Properties getServerProperties() {
        Properties prop = new Properties();
        String pathToServerProperties = System.getProperty("server-prop");
        try (InputStream in = new FileInputStream(pathToServerProperties)) {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static void main(String[] args) {
        if ("start".equals(args[0])) {
            start(args);
        } else if ("stop".equals(args[0])) {
            stop(args);
        }
    }

    private static void stop(String[] args) {
        server.stop();
    }

    private static void start(String[] args) {
        server.start();
    }
}
