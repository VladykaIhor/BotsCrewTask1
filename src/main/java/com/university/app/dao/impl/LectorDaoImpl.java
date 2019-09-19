package com.university.app.dao.impl;

import com.university.app.utils.HibernateUtil;
import com.university.app.dao.LectorDAO;
import com.university.app.entity.Lector;
import org.hibernate.Session;

public class LectorDaoImpl implements LectorDAO {

    private Session session;

    @Override
    public void addLector(Lector lector) {
        session.beginTransaction();
        session.save(lector);
        session.getTransaction().commit();
    }
}
