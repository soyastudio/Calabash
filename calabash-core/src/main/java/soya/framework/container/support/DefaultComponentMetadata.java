package soya.framework.container.support;

import soya.framework.container.ComponentMetadata;

import java.util.Arrays;
import java.util.Objects;

public class DefaultComponentMetadata implements ComponentMetadata {
    private String componentType;
    private String[] annotations;
    private String[] interfaces;

    @Override
    public String getComponentType() {
        return componentType;
    }

    @Override
    public String[] getAnnotations() {
        return annotations;
    }

    @Override
    public boolean annotatedAs(String annotation) {
        Objects.requireNonNull(annotation);
        return Arrays.stream(annotations).anyMatch(annotation::equals);
    }

    @Override
    public String[] getInterfaces() {
        return new String[0];
    }

    @Override
    public boolean implementedAs(String interfaceName) {
        Objects.requireNonNull(interfaceName);
        return Arrays.stream(interfaces).anyMatch(interfaceName::equals);
    }
}
