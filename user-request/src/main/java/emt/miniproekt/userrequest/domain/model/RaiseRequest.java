package emt.miniproekt.userrequest.domain.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "raiseRequests")
@Getter

public class RaiseRequest {

    @Version
    private long Version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "employeeId")
    private int employeeId;

    @Column(name = "description")
    private String description;

    @Column(name = "newSalary")
    private double newSalary;

    @SuppressWarnings("unused")
    private RaiseRequest() {

    }

    public RaiseRequest(int employeeId, String description, double newSalary){
        this.employeeId = employeeId;
        this.description = description;
        this.newSalary = newSalary;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNewSalary(double newSalary) {
        this.newSalary = newSalary;
    }
}
