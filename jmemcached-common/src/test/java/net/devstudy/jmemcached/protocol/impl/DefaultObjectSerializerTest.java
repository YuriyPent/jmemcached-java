package net.devstudy.jmemcached.protocol.impl;

import net.devstudy.jmemcached.exception.JMemcachedException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import test.SeriazableFailedClass;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.junit.Assert.*;

public class DefaultObjectSerializerTest {

    private final DefaultObjectSerializer defaultObjectSerializer = new DefaultObjectSerializer();
    private final Object testObject = "Test";
    private final byte[] testObjectArray = {-84, -19, 0, 5, 116, 0, 4, 84, 101, 115, 116};
    private final byte[] testClassNotFoundArray = {-84, -19, 0, 5, 115, 114, 0, 3, 97, 46, 66, 56, 54, 57, -101,
            -3, 120, 66, 4, 2, 0, 0, 120, 112};
    private final byte[] testIOExceptionDuringDeserialization = {-84, -19, 0, 5, 115, 114, 0, 26, 116, 101, 115,
            116, 46, 83, 101, 114, 105, 97, 122, 97, 98, 108, 101, 70, 97, 105, 108, 101, 100, 67, 108, 97, 115,
            115, -21, -110, -27, 28, 43, -101, 0, -74, 2, 0, 0, 120, 112};

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void toByteArraySuccess() {
        byte[] actual = defaultObjectSerializer.toByteArray(testObject);
        assertArrayEquals(testObjectArray, actual);
    }

    @Test
    public void toByteArrayNull() {
        assertNull(defaultObjectSerializer.toByteArray(null));
    }

    @Test
    public void toByteArraySerializableException() {
        thrown.expect(JMemcachedException.class);
        thrown.expectMessage(is("Class java.lang.Object should implement java.io.Serializable interface"));
        defaultObjectSerializer.toByteArray(new Object());
    }

    @Test
    public void toByteArrayIOException() {
        thrown.expect(JMemcachedException.class);
        thrown.expectMessage(is("Can't convert object to byte array: Write IO"));
        thrown.expectCause(isA(IOException.class));
        defaultObjectSerializer.toByteArray(new SeriazableFailedClass());
    }

    @Test
    public void fromByteArraySuccess() {
        String actual = (String) defaultObjectSerializer.fromByteArray(testObjectArray);
        assertEquals(testObject, actual);
    }

    @Test
    public void fromByteArrayNull() {
        assertNull(defaultObjectSerializer.fromByteArray(null));
    }

    @Test
    public void fromByteArrayIOException() {
        thrown.expect(JMemcachedException.class);
        thrown.expectMessage(is("Can't convert byte array to object: Read IO"));
        thrown.expectCause(isA(IOException.class));
        defaultObjectSerializer.fromByteArray(testIOExceptionDuringDeserialization);
    }

    @Test
    public void fromByteArrayClassNotFoundException() {
        thrown.expect(JMemcachedException.class);
        thrown.expectMessage(is("Can't convert byte array to object: a.B"));
        thrown.expectCause(isA(ClassNotFoundException.class));
        defaultObjectSerializer.fromByteArray(testClassNotFoundArray);
    }
}