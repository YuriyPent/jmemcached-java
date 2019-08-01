package net.devstudy.jmemcached.protocol.model;

import net.devstudy.jmemcached.exception.JMemcachedException;

public enum Command {
    CLEAR(0),
    PUT(1),
    GET(2),
    REMOVE(3);

    private byte code;

    Command(int code) {
        this.code = (byte) code;
    }

    public static Command valueOf(byte byteCode) {
        for (Command command : Command.values()) {
            if (command.getByteCode() == byteCode) {
                return command;
            }
        }
        throw new JMemcachedException("Unsupported byteCode for Command: " + byteCode);
    }

    public byte getByteCode() {
        return code;
    }
}
