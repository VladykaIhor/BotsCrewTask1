package com.university.app.service;

import com.university.app.entity.Department;
import com.university.app.entity.Lector;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

public interface DepartmentService {

    void addDepartment(Department department);

    List<Department> getAllDepartments();

    Optional<Lector> getHeadOfDepartment(Department department);

    Optional<Double> showAverageSalaryForDepartment(Department department);

    Optional<Integer> showEmployeeCountForDepartment(Department department);

    Optional<Department> getDepartmentByName(String name);

    Map<String, Integer> showDepartmentStatistics(Department department);

    List<Lector> searchBy(String searchCriteria);
}
