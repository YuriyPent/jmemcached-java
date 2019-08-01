package net.devstudy.jmemcached.protocol.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseTest {
    @Test
    public void toStringWithoutData() {
        Response response = new Response(Status.ADDED);
        assertEquals("ADDED", response.toString());
    }

    @Test
    public void toStringWithData() {
        Response response = new Response(Status.GOTTEN, new byte[]{1, 2, 3});
        assertEquals("GOTTEN [3 bytes]", response.toString());
    }
}