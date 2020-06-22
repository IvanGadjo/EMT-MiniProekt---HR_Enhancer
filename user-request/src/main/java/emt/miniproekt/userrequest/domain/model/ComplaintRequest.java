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

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @SuppressWarnings("unused")
    private ComplaintRequest() {

    }

    public ComplaintRequest(int employeeId, String description, User user) {
        this.employeeId  = employeeId;
        this.description = description;
        this.user = user;

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }


}
