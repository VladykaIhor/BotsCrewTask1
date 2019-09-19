package com.university.app.dao.impl;

import com.university.app.utils.HibernateUtil;
import com.university.app.dao.DegreeDao;
import com.university.app.entity.Degree;
import com.university.app.entity.Lector;
import org.apache.log4j.Logger;
import org.hibernate.Session;

public class DegreeDaoImpl implements DegreeDao {

    private static final Logger logger = Logger.getLogger(DegreeDaoImpl.class);

    @Override
    public void add(Degree degree) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(degree);
            session.close();
            logger.info("Department" + degree.getName() + "was added to DB");
        } catch (Exception e) {
            logger.error("Failed to create department", e);
        }
    }
}
