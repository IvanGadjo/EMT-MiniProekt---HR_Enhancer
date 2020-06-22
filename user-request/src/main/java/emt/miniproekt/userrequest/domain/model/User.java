package emt.miniproekt.userrequest.domain.model;

import lombok.Getter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter

public class User {

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ComplaintRequest> complaintRequests;

    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<RaiseRequest> raiseRequests;

    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<PositionChangeRequest> positionChangeRequests;

    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<RestDaysRequest> restDaysRequests;

    @SuppressWarnings("unused")
    private User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        complaintRequests = new ArrayList<>();
        raiseRequests = new ArrayList<>();
        positionChangeRequests = new ArrayList<>();
        restDaysRequests = new ArrayList<>();

    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
