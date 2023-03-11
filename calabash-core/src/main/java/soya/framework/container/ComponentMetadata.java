package soya.framework.container;

public interface ComponentMetadata {
    String getComponentType();

    String[] getAnnotations();

    boolean annotatedAs(String annotation);

    String[] getInterfaces();

    boolean implementedAs(String interfaceName);

}
