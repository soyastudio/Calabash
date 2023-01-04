package soya.framework.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReflectUtils {

    public static Field[] getFields(Class<?> cls) {
        Map<String, Field> fields = new LinkedHashMap<>();
        Class<?> parent = cls;
        while (!parent.getName().equals("java.lang.Object")) {
            Arrays.stream(parent.getDeclaredFields()).forEach(e -> {
                if(!fields.containsKey(e.getName())) {
                    fields.put(e.getName(), e);
                }
            });

            parent = parent.getSuperclass();
        }

        return fields.values().toArray(new Field[fields.size()]);
    }

    public static Field[] getFieldsWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationType) {
        Map<String, Field> fields = new LinkedHashMap<>();
        Class<?> parent = cls;
        while (!parent.getName().equals("java.lang.Object")) {
            Arrays.stream(parent.getDeclaredFields()).forEach(e -> {
                if(!fields.containsKey(e.getName()) && isAnnotatedAs(e, annotationType)) {
                    fields.put(e.getName(), e);
                }
            });

            parent = parent.getSuperclass();
        }

        return fields.values().toArray(new Field[fields.size()]);
    }

    public static Field findField(Class<?> cls, String fieldName) {
        Class<?> parent = cls;
        while (!parent.getName().equals("java.lang.Object")) {
            try {
                Field field = parent.getDeclaredField(fieldName);
                if (field != null) {
                    return field;
                }
            } catch (NoSuchFieldException e) {

            }
            parent = parent.getSuperclass();
        }

        throw new IllegalArgumentException("Cannot find field '" + fieldName + "' for class: " + cls.getName());
    }

    public static boolean isAnnotatedAs(AnnotatedElement element, Class<? extends Annotation> annotationType) {
        String annotationClassName = annotationType.getName();
        Annotation[] annotations = element.getAnnotations();
        for(Annotation annotation: annotations) {
            if(annotation.annotationType().getName().equals(annotationClassName)) {
                return true;
            }
        }

        return false;
    }

}
