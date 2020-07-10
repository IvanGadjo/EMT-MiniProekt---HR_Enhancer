package emt.miniproekt.userrequest.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import emt.miniproekt.sharedkernel.domain.base.DomainEvent;
import lombok.Getter;

import java.time.Instant;

@Getter

public class ComplaintRequestEvent implements DomainEvent {

    @JsonProperty("complaintId")
    private final int complaintId;

    @JsonProperty("employeeId")
    private final int employeeId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonProperty("description")
    private final String description;



    @JsonCreator
    public ComplaintRequestEvent(@JsonProperty("complaintId") int complaintId,
                                 @JsonProperty("employeeId") int employeeId,
                                 @JsonProperty("occurredOn") Instant occurredOn,
                                 @JsonProperty("description") String description){
        this.complaintId = complaintId;
        this.employeeId = employeeId;
        this.occurredOn = occurredOn;
        this.description = description;
    }


    public Instant occurredOn() {
        return occurredOn;
    }

    public String getDescription() {
        return description;
    }
}
