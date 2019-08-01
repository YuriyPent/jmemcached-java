package net.devstudy.jmemcached.protocol.model;

import java.util.Date;

public class Request extends AbstractPackage {

    private final Command command;
    private String key;
    private Long ttl;

    public Request(Command command) {
        this.command = command;
    }

    public Request(Command command, String key, Long ttl, byte[] data) {
        super(data);
        this.command = command;
        this.key = key;
        this.ttl = ttl;
    }

    public Request(Command command, String key) {
        this.command = command;
        this.key = key;
    }

    public Command getCommand() {
        return command;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }

    public boolean hasKey() {
        return key != null;
    }

    public boolean hasTtl() {
        return ttl != null;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(getCommand().name());
        if (hasKey()) {
            s.append('[').append(getKey()).append(']');
        }
        if (hasData()) {
            s.append('=').append(getData().length).append(" bytes");
        }
        if (hasTtl()) {
            s.append(" (").append(new Date(ttl)).append(')');
        }
        return s.toString();
    }
}
