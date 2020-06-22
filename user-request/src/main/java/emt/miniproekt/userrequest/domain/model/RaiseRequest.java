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

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @SuppressWarnings("unused")
    private RaiseRequest() {

    }

    public RaiseRequest(int employeeId, String description, double newSalary, User user){
        this.employeeId = employeeId;
        this.description = description;
        this.newSalary = newSalary;
        this.user = user;
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
