package net.devstudy.jmemcached.protocol.model;

public class Response extends AbstractPackage {

    private final Status status;

    public Response(Status status, byte[] data) {
        super(data);
        this.status = status;
    }

    public Response(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        final StringBuilder s = new StringBuilder(status.name());
        if (hasData()) {
            s.append(" [").append(getData().length).append(" bytes]");
        }
        return s.toString();
    }
}
