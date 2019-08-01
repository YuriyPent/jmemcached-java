package net.devstudy.jmemcached.protocol.impl;

import net.devstudy.jmemcached.protocol.ResponseConverter;
import net.devstudy.jmemcached.protocol.model.Response;
import net.devstudy.jmemcached.protocol.model.Status;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class DefaultResponseConverter extends AbstractPackageConverter implements ResponseConverter {

    @Override
    public Response readResponse(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        checkProtocolVersion(dataInputStream.readByte());
        byte status = dataInputStream.readByte();
        Response response = new Response(Status.valueOf(status));
        byte data = dataInputStream.readByte();
        if (data == 1) {
            int dataLength = dataInputStream.readInt();
            response.setData(IOUtils.readFully(dataInputStream, dataLength));
        }
        return response;
    }

    @Override
    public void writeResponse(OutputStream outputStream, Response response) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeByte(getVersionByte());
        dataOutputStream.writeByte(response.getStatus().getByteCode());
        dataOutputStream.writeByte(response.hasData() ? 1 : 0);
        if (response.hasData()) {
            dataOutputStream.writeInt(response.getData().length);
            dataOutputStream.write(response.getData());
        }
        dataOutputStream.flush();

    }
}
