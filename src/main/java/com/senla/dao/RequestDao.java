package com.senla.dao;

import com.senla.api.dao.IRequestDao;
import com.senla.model.Request;

public class RequestDao extends AbstractDao<Request> implements IRequestDao {

//    @Override
//    protected String query() {
//        return "from Request";
//    }



    @Override
    protected Class<Request> aClass() {
        return Request.class;
    }
}
