package soya.framework.commons.reflect;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DynaEntityTypeDescriptor extends AnnotatableDescriptor {

    private final String name;
    private final ImmutableMap<String, DynaPropertyDescriptor> properties;

    private DynaEntityTypeDescriptor(String name, Map<String, DynaPropertyDescriptor> properties, Map<String, Object> annotations) {
        this.name = name;
        this.properties = ImmutableMap.copyOf(properties);
    }

    public static class DynaEntityTypeDescriptorBuilder extends AnnotatableDescriptorBuilder<DynaEntityTypeDescriptorBuilder> {
        private String name;
        private Map<String, DynaPropertyDescriptor.DynaPropertyDescriptorBuilder> propertyBuilders = new HashMap<>();

        private DynaEntityTypeDescriptorBuilder() {
        }

        public DynaEntityTypeDescriptorBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DynaEntityTypeDescriptorBuilder addProperty(String name, DynaPropertyTemplate template) {
            propertyBuilders.put(name, DynaPropertyDescriptor.builder(name, template));
            return this;
        }

        public DynaEntityTypeDescriptor build() {
            Map<String, DynaPropertyDescriptor> properties = new LinkedHashMap<>();
            propertyBuilders.entrySet().forEach(e -> {
                properties.put(e.getKey(), e.getValue().build());
            });
            return new DynaEntityTypeDescriptor(name, properties, annotations);
        }
    }

}
