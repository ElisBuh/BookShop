package com.senla.ui.actions;

import com.senla.dao.HibernateSessionFactory;
import org.springframework.stereotype.Component;

@Component
public class Exit implements IAction{
    @Override
    public void execute() {
        HibernateSessionFactory.shutdown();
        System.out.println("Good Buy");
    }
}
