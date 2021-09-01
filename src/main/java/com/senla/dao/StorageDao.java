package com.senla.dao;

import com.senla.api.dao.IStorageDao;
import com.senla.model.Book;
import com.senla.model.Storage;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class StorageDao extends AbstractDao<Storage> implements IStorageDao {
    @Override
    public Storage findStorageOnBook(Book book){
        log.info("Get_{}_By_: {}", Storage.class, book);
        Session session = sessionFactory.openSession();
        return (Storage) session.getNamedQuery("findStorageOnBook")
                .setParameter("id",book.getId())
                .getSingleResult();
    }

    @Override
    protected Class<Storage> aClass() {
        return Storage.class;
    }
}
