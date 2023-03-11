package soya.framework.container.support;

import soya.framework.container.ComponentMetadata;
import soya.framework.container.ComponentScanner;
import soya.framework.container.MetaContainer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class DefaultMetaContainer implements MetaContainer {

    private Set<ComponentMetadata> repository = new HashSet<>();

    public void load(ComponentScanner scanner) {
        Arrays.stream(scanner.scan()).forEach(e -> {
            repository.add(e);
        });
    }

    @Override
    public ComponentMetadata[] components(String type) {
        Objects.requireNonNull(type);

        String className = type.replaceAll("/", ".");
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }

        Set<ComponentMetadata> set = new HashSet<>();
        // find from repository
        if(cls.isAnnotation()) {
            repository.forEach(e -> {
                if(e.annotatedAs(className)) {
                    set.add(e);
                }
            });
        } else if(cls.isInterface()){
            repository.forEach(e -> {
                if(e.implementedAs(className)) {
                    set.add(e);
                }
            });
        } else {
            // TODO:
        }

        return set.toArray(new ComponentMetadata[set.size()]);
    }
}
