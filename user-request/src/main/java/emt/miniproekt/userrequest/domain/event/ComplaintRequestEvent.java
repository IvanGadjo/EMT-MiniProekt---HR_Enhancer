package emt.miniproekt.userrequest.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import emt.miniproekt.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

public class ComplaintRequestEvent implements DomainEvent {

    @JsonProperty("employeeID")
    private final int employeeId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonProperty("description")
    private final String description;



    @JsonCreator
    public ComplaintRequestEvent(@JsonProperty("employeeId") int employeeId,
                                 @JsonProperty("occurredOn") Instant occurredOn,
                                 @JsonProperty("description") String description){
        this.employeeId = employeeId;
        this.occurredOn = occurredOn;
        this.description = description;
    }

    public int employeeId() {
        return employeeId;
    }

    public Instant occurredOn() {
        return occurredOn;
    }

    public String getDescription() {
        return description;
    }
}
