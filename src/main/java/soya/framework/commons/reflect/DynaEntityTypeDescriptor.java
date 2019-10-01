package soya.framework.commons.reflect;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class DynaEntityTypeDescriptor extends AnnotatableDescriptor {

    private final String name;
    private final ImmutableMap<String, DynaPropertyDescriptor> properties;

    public DynaEntityTypeDescriptor(String name, Map<String, DynaPropertyDescriptor> properties) {
        this.name = name;
        this.properties = ImmutableMap.copyOf(properties);
    }
}
