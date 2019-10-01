package soya.framework.commons.reflect;

public interface DefaultValueGenerator {
    <T> T generate(Class<T> type);
}
