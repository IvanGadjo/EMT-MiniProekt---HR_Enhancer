package emt.miniproekt.userrequest.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import emt.miniproekt.sharedkernel.domain.base.DomainEvent;
import lombok.Getter;

import java.time.Instant;

@Getter

public class RestDaysRequestEvent implements DomainEvent {

    @JsonProperty("restDaysId")
    private final int restDaysId;

    @JsonProperty("employeeID")
    private final int employeeId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonProperty("description")
    private final String description;

    @JsonProperty("numDays")
    private final int numDays;


    @JsonCreator
    public RestDaysRequestEvent(@JsonProperty("restDaysId") int restDaysId,
                                @JsonProperty("employeeId") int employeeId,
                                @JsonProperty("occurredOn") Instant occurredOn,
                                @JsonProperty("description") String description,
                                @JsonProperty("numDays") int numDays){
        this.restDaysId = restDaysId;
        this.employeeId = employeeId;
        this.occurredOn = occurredOn;
        this.description = description;
        this.numDays = numDays;
    }

    public Instant occurredOn() {
        return occurredOn;
    }

    public String getDescription() {
        return description;
    }

}
