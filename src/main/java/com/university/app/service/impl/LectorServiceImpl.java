package com.university.app.service.impl;

import com.university.app.Factory;
import com.university.app.dao.LectorDAO;
import com.university.app.dao.impl.LectorDaoImpl;
import com.university.app.entity.Lector;
import com.university.app.service.LectorService;


public class LectorServiceImpl implements LectorService {

    LectorDAO lectorDAO = Factory.getLectorDAO();

    @Override
    public void add(Lector lector) {
        lectorDAO.addLector(lector);
    }
}
