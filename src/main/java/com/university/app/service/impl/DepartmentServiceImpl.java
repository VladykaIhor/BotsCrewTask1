package com.university.app.service.impl;

import com.university.app.Factory;
import com.university.app.dao.DepartmentDAO;
import com.university.app.dao.impl.DepartmentDaoImpl;
import com.university.app.entity.Department;
import com.university.app.entity.Lector;
import com.university.app.service.DepartmentService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class DepartmentServiceImpl implements DepartmentService {

    DepartmentDAO departmentDAO = Factory.getDepartmentDAO();

    @Transactional
    @Override
    public void addDepartment(Department department) {
        departmentDAO.addDepartment(department);
    }

    @Transactional
    @Override
    public List<Department> getAllDepartments() {
        return departmentDAO.getAllDepartments();
    }

    @Transactional
    @Override
    public Optional<Lector> getHeadOfDepartment(Department department) {
        return departmentDAO.getHeadOfDepartment(department);
    }

    @Transactional
    @Override
    public Optional<Double> showAverageSalaryForDepartment(Department department) {
        return departmentDAO.showAverageSalaryForDepartment(department);
    }

    @Transactional
    @Override
    public Optional<Integer> showEmployeeCountForDepartment(Department department) {
        return departmentDAO.showEmployeeCountForDepartment(department);
    }

    @Transactional
    @Override
    public Optional<Department> getDepartmentByName(String name) {
        return departmentDAO.getDepartmentByName(name);
    }

    @Override
    public Map<String, Integer> showDepartmentStatistics(Department department) {
        return departmentDAO.showDepartmentStatistics(department);
    }

    @Override
    public List<Lector> searchBy(String searchCriteria) {
        return departmentDAO.searchBy(searchCriteria);
    }
}
