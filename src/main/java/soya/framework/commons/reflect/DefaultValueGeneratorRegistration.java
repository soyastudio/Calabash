package soya.framework.commons.reflect;

public interface DefaultValueGeneratorRegistration {
    void register(Class<?> type, DefaultValueGenerator generator);
}
