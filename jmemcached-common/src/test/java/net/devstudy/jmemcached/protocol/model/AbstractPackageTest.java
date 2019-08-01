package net.devstudy.jmemcached.protocol.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AbstractPackageTest {
    private static AbstractPackage newInstance(byte[] array) {
        return new AbstractPackage(array) {
        };
    }

    @Test
    public void hasDataNull() {
        AbstractPackage aPackage = newInstance(null);
        assertFalse(aPackage.hasData());
    }

    @Test
    public void hasDataEmpty() {
        AbstractPackage aPackage = newInstance(new byte[0]);
        assertFalse(aPackage.hasData());
    }

    @Test
    public void hasData() {
        AbstractPackage aPackage = newInstance(new byte[]{1, 2, 3});
        assertTrue(aPackage.hasData());
    }
}