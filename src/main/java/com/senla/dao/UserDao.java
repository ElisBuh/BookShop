package com.senla.dao;

import com.senla.api.dao.IUserDao;
import com.senla.model.User;
import com.senla.model.metamodel.User_;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


@Repository
public class UserDao extends AbstractDao<User> implements IUserDao {


    @Override
    public User findByUserLogin(String login) {
//        log.info("findByUserLogin {}", login);
//        Session session = sessionFactory.openSession();
//        return (User) session.getNamedQuery("findByUserLogin")
//                .setParameter("login",login)
//                .getSingleResult();
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        userRoot.fetch(User_.ROLE, JoinType.LEFT);

        criteriaQuery.select(userRoot).distinct(true);

        Predicate predicate = criteriaBuilder.conjunction();
        if (login !=null){
            Predicate p = criteriaBuilder.equal(userRoot.get(User_.LOGIN), login);
            predicate = criteriaBuilder.and(predicate, p);
        }
        criteriaQuery.where(predicate);
        return session.createQuery(criteriaQuery).getSingleResult();
    }


    @Override
    protected Class<User> aClass() {
        return User.class;
    }
}
