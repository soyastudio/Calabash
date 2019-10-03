package soya.framework.commons.reflect;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public final class AnnotationGroup {
    private final String name;
    private final ImmutableMap<String, Object> annotations;

    private AnnotationGroup(String name, Map<String, Object> annotations) {
        this.name = name;
        this.annotations = ImmutableMap.copyOf(annotations);
    }

    public static class AnnotationGroupBuilder extends AnnotatableDescriptorBuilder<AnnotationGroupBuilder> {

        private Map<String, Object> annotations = new HashMap<>();

        private AnnotationGroupBuilder() {

        }
    }

}
