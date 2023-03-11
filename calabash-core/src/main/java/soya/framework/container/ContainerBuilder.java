package soya.framework.container;

public interface ContainerBuilder<T extends Container> {

    T create(MetaContainer metaContainer);

}
