package com.university.app.dao;

import com.university.app.entity.Department;
import com.university.app.entity.Lector;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DepartmentDAO {

    void addDepartment(Department department);

    Optional<Department> getDepartmentByName(String name);

    Optional<Lector> getHeadOfDepartment(Department department);

    List<Department> getAllDepartments();

    Optional<Double> showAverageSalaryForDepartment(Department department);

    Optional<Integer> showEmployeeCountForDepartment(Department department);

    Map<String, Integer> showDepartmentStatistics(Department department);

    List<Lector> searchBy(String searchCriteria);
}
