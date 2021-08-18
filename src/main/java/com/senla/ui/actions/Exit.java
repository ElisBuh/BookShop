package com.senla.ui.actions;

import com.senla.dao.HibernateSessionFactory;
import com.senla.util.annotation.InjectByType;
import com.senla.util.serialization.Serialization;

public class Exit implements IAction{
        @InjectByType
        private Serialization serialization;
    @Override
    public void execute() {
//        Serialization serialization = new Serialization();
//        serialization.serialize();
//        connectPostgreSQL.connectionClose();
        HibernateSessionFactory.shutdown();
        System.out.println("Good Buy");
    }
}
