package com.senla.ui.actions;

import com.senla.dao.jdbc.ConnectPostgreSQL;
import com.senla.dao.jpa.HibernateSessionFactory;
import com.senla.util.annotation.InjectByType;
import com.senla.util.serialization.Serialization;

public class Exit implements IAction{
        @InjectByType
        private Serialization serialization;
        @InjectByType
        private ConnectPostgreSQL connectPostgreSQL;
    @Override
    public void execute() {
//        Serialization serialization = new Serialization();
//        serialization.serialize();
//        connectPostgreSQL.connectionClose();
        HibernateSessionFactory.shutdown();
        System.out.println("Good Buy");
    }
}
