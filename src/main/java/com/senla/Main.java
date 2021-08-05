package com.senla;

import com.senla.dao.jpa.HibernateSessionFactory;
import com.senla.dao.jpa.JpaBookDao;
import com.senla.dao.jpa.JpaOrderDao;
import com.senla.dao.jpa.JpaRequestDao;
import com.senla.model.*;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JpaRequestDao jpaRequestDao = new JpaRequestDao();
        List<Request> requests = jpaRequestDao.getAll();
        requests.forEach(System.out::println);
    }
}