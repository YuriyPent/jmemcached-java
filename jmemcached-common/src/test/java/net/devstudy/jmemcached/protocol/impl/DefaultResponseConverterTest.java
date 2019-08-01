package net.devstudy.jmemcached.protocol.impl;

import net.devstudy.jmemcached.protocol.model.Response;
import net.devstudy.jmemcached.protocol.model.Status;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class DefaultResponseConverterTest {
    private final DefaultResponseConverter defaultResponseConverter = new DefaultResponseConverter();

    @Test
    public void readResponseWithoutData() throws IOException {
        Response response = defaultResponseConverter.readResponse(new ByteArrayInputStream(new byte[]
                //version, status, flags
                {16, 0, 0}));
        assertEquals(Status.ADDED, response.getStatus());
        assertFalse(response.hasData());
    }

    @Test
    public void readResponseWithData() throws IOException {
        Response response = defaultResponseConverter.readResponse(new ByteArrayInputStream(new byte[]
                //version, status, flags, int length, byte array
                {16, 0, 1, 0, 0, 0, 3, 1, 2, 3}));
        assertEquals(Status.ADDED, response.getStatus());
        assertTrue(response.hasData());
        assertArrayEquals(new byte[]{1, 2, 3}, response.getData());
    }

    @Test
    public void writeResponseWithoutData() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Response response = new Response(Status.GOTTEN);
        defaultResponseConverter.writeResponse(out, response);
        //version, status, flags
        assertArrayEquals(new byte[]{16, 2, 0}, out.toByteArray());

    }

    @Test
    public void writeResponseWithData() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Response response = new Response(Status.ADDED, new byte[]{1, 2, 3});
        defaultResponseConverter.writeResponse(out, response);
        //version, status, flags
        assertArrayEquals(new byte[]{16, 0, 1, 0, 0, 0, 3, 1, 2, 3}, out.toByteArray());

    }
}