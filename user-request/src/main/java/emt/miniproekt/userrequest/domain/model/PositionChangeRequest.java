package emt.miniproekt.userrequest.domain.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "positionChangeRequests")
@Getter

public class PositionChangeRequest {

    @Version
    private long Version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "employeeId")
    private int employeeId;

    @Column(name = "description")
    private String description;

    @Column(name = "newPosition")
    private String newPosition;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @SuppressWarnings("unused")
    private PositionChangeRequest() {

    }

    public PositionChangeRequest(int employeeId, String description, String newPosition, User user) {
        this.employeeId = employeeId;
        this.description = description;
        this.newPosition = newPosition;
        this.user = user;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNewPosition(String newPosition) {
        this.newPosition = newPosition;
    }
}
