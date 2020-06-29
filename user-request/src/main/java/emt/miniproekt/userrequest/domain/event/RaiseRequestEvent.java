package emt.miniproekt.userrequest.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import emt.miniproekt.sharedkernel.domain.base.DomainEvent;
import lombok.Getter;

import java.time.Instant;

@Getter

public class RaiseRequestEvent implements DomainEvent {

    @JsonProperty("raiseId")
    private final int raiseId;

    @JsonProperty("employeeID")
    private final int employeeId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonProperty("description")
    private final String description;

    @JsonProperty("newSalary")
    private final double newSalary;

    @JsonCreator
    public RaiseRequestEvent(@JsonProperty("raiseId") int raiseId,
                             @JsonProperty("employeeId") int employeeId,
                             @JsonProperty("occurredOn") Instant occurredOn,
                             @JsonProperty("description") String description,
                             @JsonProperty("newSalary") double newSalary){
        this.raiseId = raiseId;
        this.employeeId = employeeId;
        this.occurredOn = occurredOn;
        this.description = description;
        this.newSalary = newSalary;
    }

    public Instant occurredOn() {
        return occurredOn;
    }

    public String getDescription() {
        return description;
    }

}
