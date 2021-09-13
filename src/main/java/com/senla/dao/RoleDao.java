package com.senla.dao;

import com.senla.api.dao.IRoleDao;
import com.senla.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao extends AbstractDao<Role> implements IRoleDao {

    @Override
    protected Class<Role> aClass() {
        return Role.class;
    }
}
