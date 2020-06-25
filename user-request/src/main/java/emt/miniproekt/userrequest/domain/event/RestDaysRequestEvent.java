package emt.miniproekt.userrequest.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import emt.miniproekt.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

public class RestDaysRequestEvent implements DomainEvent {

    @JsonProperty("employeeID")
    private final int employeeId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonProperty("description")
    private final String description;

    @JsonProperty("numDays")
    private final int numDays;


    @JsonCreator
    public RestDaysRequestEvent(@JsonProperty("employeeId") int employeeId,
                                @JsonProperty("occurredOn") Instant occurredOn,
                                @JsonProperty("description") String description,
                                @JsonProperty("numDays") int numDays){
        this.employeeId = employeeId;
        this.occurredOn = occurredOn;
        this.description = description;
        this.numDays = numDays;
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

    public int getNumDays() {
        return numDays;
    }
}
