package com.university.app;

import com.university.app.dao.DegreeDao;
import com.university.app.dao.DepartmentDAO;
import com.university.app.dao.LectorDAO;
import com.university.app.dao.impl.DegreeDaoImpl;
import com.university.app.dao.impl.DepartmentDaoImpl;
import com.university.app.dao.impl.LectorDaoImpl;

public class Factory {

    private static LectorDAO lectorDAO = null;
    private static DepartmentDAO departmentDAO = null;
    private static DegreeDao degreeDAO = null;

    public static LectorDAO getLectorDAO() {
        if (lectorDAO == null) {
            lectorDAO = new LectorDaoImpl();
        }
        return lectorDAO;
    }

    public static DepartmentDAO getDepartmentDAO() {
        if (departmentDAO == null) {
            departmentDAO = new DepartmentDaoImpl();
        }
        return departmentDAO;
    }

    public static DegreeDao getDegreeDAO() {
        if (degreeDAO == null) {
            degreeDAO = new DegreeDaoImpl();
        }
        return degreeDAO;
    }
}
