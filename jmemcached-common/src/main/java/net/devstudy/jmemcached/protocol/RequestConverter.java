package net.devstudy.jmemcached.protocol;

import net.devstudy.jmemcached.protocol.model.Request;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface RequestConverter {

    Request readrequest(InputStream inputStream) throws IOException;

    void writeRequest(OutputStream outputStream, Request request) throws IOException;
}
