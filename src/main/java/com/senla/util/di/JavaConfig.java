package com.senla.util.di;

import lombok.Getter;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.util.Set;

public class JavaConfig implements Config {
    @Getter
    private Reflections scanner;

    public JavaConfig(String packageToScan) {
        this.scanner = new Reflections(packageToScan);
    }

    @SneakyThrows
    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
        if (classes.size()!=1){
            throw new Exception("Больше двух имплетаций");
        }

        return classes.iterator().next();
    }
}
