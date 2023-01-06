package soya.framework.io;

import soya.framework.lang.Named;

import java.io.InputStream;
import java.net.URI;

@Named("xyz")
public class XyzResource implements Resource{
    @Override
    public InputStream getAsInputStream(URI uri) throws ResourceException {
        return null;
    }
}
