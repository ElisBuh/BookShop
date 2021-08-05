package com.senla.dao.jpa;

import com.senla.api.dao.IRequestDao;
import com.senla.model.Request;

public class JpaRequestDao extends JpaAbstractDao<Request> implements IRequestDao {

    @Override
    protected String query() {
        return "from Request";
    }

    @Override
    protected Class<Request> aClass() {
        return Request.class;
    }
}
