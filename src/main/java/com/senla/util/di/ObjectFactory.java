package com.senla.util.di;


import com.senla.api.dao.IBookDao;
import com.senla.dao.BookDao;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class ObjectFactory {
    private static ObjectFactory ourInstance = new ObjectFactory();
    private Config config;
//    = new JavaConfig("api");

    private ObjectFactory() {
        HashMap<Class,Class> map = new HashMap<>();
        map.put(IBookDao.class, BookDao.class);
        config = new JavaConfig("com/senla/dao");
    }
    public static ObjectFactory getInstance(){
        return ourInstance;
    }

    public <T> T createObject(Class<T> type){
        Class<? extends T> implClass = type;
        if (type.isInterface()){
            implClass = config.getImplClass(type);
        }
        try {
            return implClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;

    }
}
