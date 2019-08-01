package net.devstudy.jmemcached.server;

import net.devstudy.jmemcached.server.impl.JmemcachedServerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CLI {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(CLI.class);
    private static final List<String> QUIT_CMDS =
            Collections.unmodifiableList(Arrays.asList(new String[]{"q", "quit", "exit"}));

    public static void main(String[] args) {
        Thread.currentThread().setName("CLI-main thread");
        try {
            Server server = JmemcachedServerFactory.buildNewServer(null);
            server.start();
            waitForStopCommand(server);
        } catch (Exception e) {
            LOGGER.error("Can't execute cmd: " + e.getMessage(), e);
        }
    }

    private static void waitForStopCommand(Server server) {
        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name())) {
            while (true) {
                String cmd = scanner.nextLine();
                if (QUIT_CMDS.contains(cmd.toLowerCase())) {
                    server.stop();
                    break;
                } else {
                    LOGGER.error("Undefined command: " + cmd + "! To shutdown server please type: q");
                }
            }
        }
    }
}
