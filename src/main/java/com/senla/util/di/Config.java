package com.senla.util.di;

public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> ifc);
}

