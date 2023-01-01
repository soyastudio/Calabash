package soya.framework.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Resource implements Resource {
    private static String SCHEMA = "base64";

    @Override
    public String schema() {
        return SCHEMA;
    }

    @Override
    public InputStream getAsInputStream(URI uri) throws ResourceException {
        if (uri.getHost() != null) {
            return new ByteArrayInputStream(Base64.getDecoder().decode(uri.getHost().getBytes(StandardCharsets.UTF_8)));

        } else {
            URI sub = URI.create(uri.getSchemeSpecificPart());
            if (sub.getScheme() != null && !sub.getScheme().equalsIgnoreCase(SCHEMA)) {
                InputStream is = ResourceService.getAsInputStream(sub);
                try {
                    return new ByteArrayInputStream(Base64.getDecoder().decode(StreamUtils.copyToByteArray(is)));

                } catch (IOException e) {
                    throw new ResourceException(e);
                }
            }

            throw new ResourceException(uri);
        }
    }

}
