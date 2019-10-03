package soya.framework.commons.reflect;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class AnnotationGroup extends AnnotatableDescriptor {
    private static Map<String, AnnotationGroup> groups = new ConcurrentHashMap<>();

    private final String name;

    private AnnotationGroup(String name, Map<String, Object> annotations) {
        this.name = name;
        setAnnotations(annotations);
    }

    public String getName() {
        return name;
    }

    public static String[] groups() {
        return groups.keySet().toArray(new String[groups.size()]);
    }

    public static AnnotationGroup get(String name) {
        return groups.get(name);
    }

    public static AnnotationGroupBuilder builder(String name) {
        return new AnnotationGroupBuilder(name);
    }

    public static class AnnotationGroupBuilder extends AnnotatableDescriptorBuilder<AnnotationGroupBuilder> {

        private String name;

        private AnnotationGroupBuilder(String name) {
            this.name = name;
        }

        public AnnotationGroup build() {
            if (groups.containsKey(name)) {
                throw new IllegalStateException("Annotation group '" + name + "' already exists.");
            }
            return new AnnotationGroup(name, annotations);
        }
    }

}
