package emt.miniproekt.userrequest.domain.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "restDaysRequests")
@Getter

public class RestDaysRequest {

    @Version
    private long Version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "employeeId")
    private int employeeId;

    @Column(name = "description")
    private String description;

    @Column(name = "numDays")
    private int numDays;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @SuppressWarnings("unused")
    private RestDaysRequest() {

    }

    public RestDaysRequest(int employeeId, String description, int numDays, User user) {
        this.employeeId = employeeId;
        this.description = description;
        this.numDays = numDays;
        this.user = user;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }
}
