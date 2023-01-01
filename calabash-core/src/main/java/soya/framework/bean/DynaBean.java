package soya.framework.bean;

public interface DynaBean<T extends DynaClass> {
    T getDynaClass();

    Object get(String name);

    void set(String name, Object value);

}
