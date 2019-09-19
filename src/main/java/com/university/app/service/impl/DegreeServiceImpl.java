package com.university.app.service.impl;

import com.university.app.Factory;
import com.university.app.dao.DegreeDao;
import com.university.app.dao.impl.DegreeDaoImpl;
import com.university.app.dao.impl.LectorDaoImpl;
import com.university.app.entity.Degree;
import com.university.app.service.DegreeService;

public class DegreeServiceImpl implements DegreeService {

    public DegreeDao degreeDao = Factory.getDegreeDAO();

    @Override
    public void add(Degree degree) {
        degreeDao.add(degree);
    }
}
