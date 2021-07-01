package com.senla.util.di;


import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

public class JavaConfig implements Config {

    private Reflections scanner;
    private Map<Class,Class> ifc2ImplClass;


    public JavaConfig(String packageToScan) {
//        this.ifc2ImplClass = ifc2ImplClass;
        this.scanner = new Reflections(packageToScan);
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
        System.out.println(classes.size());
        if (classes.size() !=1){
            throw new RuntimeException(ifc + " Implements 0 and more 1");
        }

        return classes.iterator().next();


//        return ifc2ImplClass.computeIfAbsent(ifc,aClass -> {
//            Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
//            if (classes.size() !=1){
//                throw new RuntimeException(ifc + " Implements 0 and more 1");
//            }
//
//            return classes.iterator().next();
//        });
    }
}
