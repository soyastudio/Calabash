package soya.framework.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public final class AnnotationsUtils {

    private AnnotationsUtils() {
    }

    public static Properties getAsProperties(Annotations annotations) {
        Objects.requireNonNull(annotations);
        Properties properties = new Properties();
        Arrays.stream(annotations.properties()).forEach(p -> {
            String value = p.value().trim();
            if(value.startsWith("${") && value.endsWith("}")) {
                value = System.getProperty(value.substring(2, value.length() - 1));
                if(value == null) {
                    value = p.defaultValue();
                }
            }

            if(value == null) {
                throw new IllegalArgumentException("Cannot get property value: " + p.value());

            } else {
                properties.setProperty(p.key(), p.value());

            }
        });

        return properties;

    }

    public static <T> T parse(Annotations annotations, Class<T> target) {
        return parse(annotations, null, target);
    }

    public static <T> T parse(Annotations annotations, String prefix, Class<T> target) {
        Objects.requireNonNull(target);
        Properties properties = getAsProperties(annotations);
        try {
            T obj = target.newInstance();
            Set<String> set = new HashSet<>();

            Class<?> cls = target;
            while (!"java.lang.Object".equals(cls.getName())) {
                Arrays.stream(cls.getDeclaredFields()).forEach(e -> {
                    if (!set.contains(e.getName()) && writable(e)) {
                        e.setAccessible(true);

                        String key = e.getName();
                        if(prefix != null && prefix.trim().length() > 0) {
                            key = prefix.endsWith(".") ? prefix + key : prefix + "." + key;
                        }
                        String value = properties.getProperty(key);
                        if(value != null) {
                            try {
                                e.set(obj, getValue(value, e.getType()));
                            } catch (IllegalAccessException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        set.add(e.getName());
                    }
                });
                cls = cls.getSuperclass();
            }

            return obj;

        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object getValue(String value, Class<?> type) {
        if(String.class.equals(type)) {
            return value;
        }

        return null;
    }

    private static boolean writable(Field field) {
        int mod = field.getModifiers();
        return !Modifier.isStatic(mod) && !Modifier.isFinal(mod) && !Modifier.isTransient(mod);
    }
}
