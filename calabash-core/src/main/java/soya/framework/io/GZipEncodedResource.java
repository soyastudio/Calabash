package soya.framework.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

public class GZipEncodedResource implements Resource {
    private static final String SCHEMA = "gzip";

    @Override
    public String schema() {
        return SCHEMA;
    }

    @Override
    public InputStream getAsInputStream(URI uri) throws ResourceException {
        if(uri.getHost() != null) {
            return unzip(uri.getHost().getBytes(StandardCharsets.UTF_8));
        } else {
            URI sub = URI.create(uri.getSchemeSpecificPart());
            if(sub.getScheme() != null && !sub.getScheme().equals(SCHEMA)) {
                try {
                    return unzip(StreamUtils.copyToByteArray(ResourceService.getAsInputStream(sub)));
                } catch (IOException e) {
                    throw new ResourceException(e);
                }
            }
        }

        throw new ResourceException(uri);
    }

    private InputStream unzip(byte[] data) {
        byte[] compressed = Base64.getDecoder().decode(data);
        try {
            return new GZIPInputStream(new ByteArrayInputStream(compressed));
        } catch (IOException e) {
            throw new ResourceException(e);
        }
    }
}
