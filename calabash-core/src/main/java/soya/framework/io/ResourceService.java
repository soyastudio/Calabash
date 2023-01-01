package soya.framework.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ResourceService {
    private static ResourceService INSTANCE;
    protected Map<String, Resource> resourceMap = new HashMap<>();

    protected ResourceService() {
        INSTANCE = this;
    }

    private static ResourceService getInstance() {
        if (INSTANCE == null) {
            new DefaultResourceService();
        }
        return INSTANCE;
    }

    public static String[] supportedSchemas() {
        return getInstance().resourceMap.keySet().toArray(new String[getInstance().resourceMap.size()]);
    }

    public static InputStream getAsInputStream(URI uri) throws ResourceException {
        if (getInstance().resourceMap.containsKey(uri.getScheme())) {
            return getInstance().resourceMap.get(uri.getScheme()).getAsInputStream(uri);

        } else {
            try {
                return uri.toURL().openStream();

            } catch (IOException e) {
                throw new ResourceException(e);
            }
        }
    }

    public static String getAsString(URI uri) throws ResourceException {
        return getAsString(uri, Charset.defaultCharset());
    }

    public static String getAsString(URI uri, Charset charset) throws ResourceException {
        try {
            return new String(StreamUtils.copyToByteArray(getAsInputStream(uri)), charset);

        } catch (IOException e) {
            throw new ResourceException(e);
        }
    }

    public static class DefaultResourceService extends ResourceService {
        protected DefaultResourceService() {
            super();
            findAllClassesUsingClassLoader(ResourceService.class.getPackage().getName()).forEach(e -> {

                if (Resource.class.isAssignableFrom(e) && !e.isInterface() && !Modifier.isAbstract(e.getModifiers())) {
                    try {
                        Resource resource = (Resource) e.newInstance();
                        resourceMap.put(resource.schema(), resource);
                    } catch (InstantiationException | IllegalAccessException ex) {
                        throw new ResourceException(ex);
                    }
                }

            });
        }

        public DefaultResourceService(Class<? extends Resource>[] resourceTypes) {
            this();
            Objects.requireNonNull(resourceTypes);
            for (Class<? extends Resource> rt : resourceTypes) {
                try {
                    Resource resource = (Resource) rt.newInstance();
                    resourceMap.put(resource.schema(), resource);
                } catch (InstantiationException | IllegalAccessException ex) {
                    throw new ResourceException(ex);
                }
            }
        }

        protected Set<Class> findAllClassesUsingClassLoader(String packageName) {
            InputStream stream = ClassLoader.getSystemClassLoader()
                    .getResourceAsStream(packageName.replaceAll("[.]", "/"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            return reader.lines()
                    .filter(line -> line.endsWith(".class"))
                    .map(line -> getClass(line, packageName))
                    .collect(Collectors.toSet());
        }

        private Class getClass(String className, String packageName) {
            try {
                return Class.forName(packageName + "."
                        + className.substring(0, className.lastIndexOf('.')));
            } catch (ClassNotFoundException e) {
                // handle the exception
            }
            return null;
        }
    }
}
