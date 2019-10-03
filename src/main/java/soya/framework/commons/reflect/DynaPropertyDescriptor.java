package soya.framework.commons.reflect;

import java.lang.reflect.Type;
import java.util.Map;

public class DynaPropertyDescriptor extends AnnotatableDescriptor {
    private final String name;
    private final TypeDescriptor type;

    protected DynaPropertyDescriptor(String name, TypeDescriptor type, Map<String, Object> annotations) {
        this.name = name;
        this.type = type;
        setAnnotations(annotations);
    }

    public String getName() {
        return name;
    }

    public TypeDescriptor getType() {
        return type;
    }

    public static DynaPropertyDescriptorBuilder builder(String name, Type type) {
        return new DynaPropertyDescriptorBuilder(name, type);
    }

    public static DynaPropertyDescriptorBuilder builder(String name, DynaPropertyTemplate template) {
        return new DynaPropertyDescriptorBuilder(name, template);
    }

    public static class DynaPropertyDescriptorBuilder extends AnnotatableDescriptorBuilder<DynaPropertyDescriptorBuilder> {
        private String name;
        private TypeDescriptor type;

        private DynaPropertyDescriptorBuilder(String name, Type type) {
            this.name = name;
            this.type = TypeDescriptor.fromType(type);
        }

        private DynaPropertyDescriptorBuilder(String name, DynaPropertyTemplate template) {
            this.name = name;
            this.type = template.getType();
            this.annotations.putAll(template.getAnnotations());
        }

        public DynaPropertyDescriptor build() {
            return new DynaPropertyDescriptor(name, type, annotations);
        }
    }
}
