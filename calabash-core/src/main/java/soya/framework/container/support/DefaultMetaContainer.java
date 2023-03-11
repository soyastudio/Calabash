package soya.framework.container.support;

import soya.framework.container.ComponentMetadata;
import soya.framework.container.ComponentScanner;
import soya.framework.container.MetaContainer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DefaultMetaContainer implements MetaContainer {
    private Set<ComponentMetadata> repository = new HashSet<>();

    public void load(ComponentScanner scanner) {
        Arrays.stream(scanner.scan()).forEach(e -> {
            repository.add(e);
        });

    }

}
