package com.senla.dao;

import com.senla.api.dao.IRequestDao;
import com.senla.model.Request;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = "singleton")
public class RequestDao extends AbstractDao<Request> implements IRequestDao {

    @Override
    protected Class<Request> aClass() {
        return Request.class;
    }
}
