package com.senla.dao.jpa;

import com.senla.api.dao.IStorageDao;
import com.senla.model.Storage;

public class JpaStorageDao extends JpaAbstractDao<Storage> implements IStorageDao {
//    @Override
//    protected String query() {
//        return "from Storage";
//    }

    @Override
    protected Class<Storage> aClass() {
        return Storage.class;
    }
}
