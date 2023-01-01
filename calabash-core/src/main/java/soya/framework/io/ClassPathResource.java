package soya.framework.io;

import java.io.InputStream;
import java.net.URI;

public class ClassPathResource implements Resource {
    private static final String SCHEMA = "classpath";

    @Override
    public String schema() {
        return SCHEMA;
    }

    @Override
    public InputStream getAsInputStream(URI uri) throws ResourceException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if(classLoader == null) {
            classLoader = ClassPathResource.class.getClassLoader();
        }

        String res = uri.getPath() != null ? uri.getHost() + uri.getPath() : uri.getHost();
        return classLoader.getResourceAsStream(res);
    }
}
