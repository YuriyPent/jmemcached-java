package net.devstudy.jmemcached.protocol.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestTest {
    private Request request;

    @Before
    public void before() {
        request = new Request(Command.CLEAR);
    }

    @Test
    public void hasKeyTrue() {
        request.setKey("key");
        assertTrue(request.hasKey());
    }

    @Test
    public void hasKeyFalse() {
        assertFalse(request.hasKey());
    }

    @Test
    public void hasTTLTrue() throws Exception {
        request.setTtl(System.currentTimeMillis());
        assertTrue(request.hasTtl());
    }

    @Test
    public void hasTTLFalse() throws Exception {
        assertFalse(request.hasTtl());
    }

    @Test
    public void toStringClear() throws Exception {
        assertEquals("CLEAR", request.toString());
    }

    @Test
    public void toStringRemove() throws Exception {
        request = new Request(Command.REMOVE);
        request.setKey("key");
        assertEquals("REMOVE[key]", request.toString());
    }

    @Test
    public void toStringPut() throws Exception {
        request = new Request(Command.PUT);
        request.setKey("key");
        assertEquals("PUT[key]", request.toString());
    }

    @Test
    public void toStringPutWithTTL() throws Exception {
        request = new Request(Command.PUT);
        request.setKey("key");
        request.setTtl(1484166240528L);
        request.setData(new byte[]{1, 2, 3});
        assertEquals("PUT[key]=3 bytes (Wed Jan 11 23:24:00 GMT+03:00 2017)", request.toString());
    }
}