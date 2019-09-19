package com.university.app.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "degree")
public class Degree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "degree_id")
    private Long degreeId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "degree", fetch = FetchType.EAGER)
    private List<Lector> degreeOwners;

    public Degree() {
    }

    public Degree(String name) {
        this.name = name;
    }

    public Long getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(Long degree_id) {
        this.degreeId = degree_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lector> getDegreeOwners() {
        return degreeOwners;
    }

    public void setDegreeOwners(List<Lector> degreeOwners) {
        this.degreeOwners = degreeOwners;
    }
}
