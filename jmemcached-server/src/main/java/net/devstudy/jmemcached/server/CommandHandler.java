package net.devstudy.jmemcached.server;

import net.devstudy.jmemcached.protocol.model.Request;
import net.devstudy.jmemcached.protocol.model.Response;

public interface CommandHandler {

    Response handle(Request request);
}
