package com.senla.dao;

import com.senla.api.dao.IRequestDao;
import com.senla.model.Request;
import org.springframework.stereotype.Repository;

@Repository
public class RequestDao extends AbstractDao<Request> implements IRequestDao {

    @Override
    protected Class<Request> aClass() {
        return Request.class;
    }
}
