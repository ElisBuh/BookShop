package com.senla.ui.actions;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Exit implements IAction{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void execute() {
        sessionFactory.close();
        System.out.println("Good Buy");
    }
}
