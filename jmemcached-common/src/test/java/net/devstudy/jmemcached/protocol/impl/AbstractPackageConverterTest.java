package net.devstudy.jmemcached.protocol.impl;

import net.devstudy.jmemcached.exception.JMemcachedException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.fail;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class AbstractPackageConverterTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private AbstractPackageConverter abstractPackageConverter = new AbstractPackageConverter() {
    };

    @Test
    public void checkProtocolVersionSuccess() {
        try {
            abstractPackageConverter.checkProtocolVersion((byte) 16);
        } catch (Exception e) {
            fail("Supported protocol version should be 1.0");
        }
    }

    @Test
    public void checkProtocolVersionFailed() {
        thrown.expect(JMemcachedException.class);
        thrown.expectMessage(is("Unsupported protocol version: 0.0"));
        abstractPackageConverter.checkProtocolVersion((byte) 0);
    }

    @Test
    public void getVersionByte() {
        assertEquals(16, abstractPackageConverter.getVersionByte());
    }
}