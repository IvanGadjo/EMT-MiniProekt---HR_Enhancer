package emt.miniproekt.hr_enhancer.employeesector.model;


import lombok.Generated;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sectors")
@Getter
public class Sector{

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "sectorId", fetch = FetchType.EAGER)
    private List<Employee> employees;

    @SuppressWarnings("unused")
    private Sector(){}

    public Sector(String name){
        this.name = name;
        employees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Employee> addEmployee(Employee employee){
        employees.add(employee);
        return employees;
    }
}
