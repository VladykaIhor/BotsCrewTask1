package com.university.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "lectors")
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LECTOR_ID")
    private Long lectorId;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "salary")
    private Double salary;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "degree_id")
    private Degree degree;

    //department he is head of
    @OneToOne(mappedBy = "headOfDepartment")
    private Department department;

    //list of departments he is working at
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "LECTOR_DEPARTMENTS",
            joinColumns = {@JoinColumn(name = "LECTOR_ID")},
            inverseJoinColumns = {@JoinColumn(name = "DEPARTMENT_ID")})
    List<Department> departments;

    public Lector() {
    }

    public Lector(String name, String lastName, Degree degree, List<Department> departments) {
        this.name = name;
        this.lastName = lastName;
        this.degree = degree;
        this.departments = departments;
    }

    public Long getLectorId() {
        return lectorId;
    }

    public void setLectorId(Long lectorId) {
        this.lectorId = lectorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

}
