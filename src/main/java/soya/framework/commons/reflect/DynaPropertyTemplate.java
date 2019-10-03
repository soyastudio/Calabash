package soya.framework.commons.reflect;

import java.lang.reflect.Type;
import java.util.Map;

public class DynaPropertyTemplate extends AnnotatableDescriptor {

    private final String name;
    private final TypeDescriptor type;

    private DynaPropertyTemplate(String name, TypeDescriptor type, Map<String, Object> annotations) {
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



    public static class DynaPropertyTemplateBuilder extends AnnotatableDescriptorBuilder<DynaPropertyTemplateBuilder> {
        private String name;
        private TypeDescriptor type;

        private DynaPropertyTemplateBuilder(String name, Type type) {
            this.name = name;
            this.type = TypeDescriptor.fromType(type);
        }

        public DynaPropertyTemplate build() {
            return new DynaPropertyTemplate(name, type, annotations);
        }
    }
}
