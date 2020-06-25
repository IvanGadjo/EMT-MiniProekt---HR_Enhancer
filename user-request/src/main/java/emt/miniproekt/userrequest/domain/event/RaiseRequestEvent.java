package emt.miniproekt.userrequest.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import emt.miniproekt.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

public class RaiseRequestEvent implements DomainEvent {

    @JsonProperty("employeeID")
    private final int employeeId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonProperty("description")
    private final String description;

    @JsonProperty("newSalary")
    private final double newSalary;

    @JsonCreator
    public RaiseRequestEvent(@JsonProperty("employeeId") int employeeId,
                             @JsonProperty("occurredOn") Instant occurredOn,
                             @JsonProperty("description") String description,
                             @JsonProperty("newSalary") double newSalary){
        this.employeeId = employeeId;
        this.occurredOn = occurredOn;
        this.description = description;
        this.newSalary = newSalary;
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

    public double getNewSalary() {
        return newSalary;
    }

}
