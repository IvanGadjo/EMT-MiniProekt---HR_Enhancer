package emt.miniproekt.userrequest.domain.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "complaintRequests")
@Getter

public class ComplaintRequest {
    @Version
    private long Version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "employeeId")
    private int employeeId;

    @SuppressWarnings("unused")
    private ComplaintRequest() {

    }

    public ComplaintRequest(int employeeId, String description) {
        this.employeeId  = employeeId;
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }


}
