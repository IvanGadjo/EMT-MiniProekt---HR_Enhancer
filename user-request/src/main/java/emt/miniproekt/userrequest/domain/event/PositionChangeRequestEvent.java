package emt.miniproekt.userrequest.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import emt.miniproekt.sharedkernel.domain.base.DomainEvent;
import lombok.Getter;

import java.time.Instant;

@Getter

public class PositionChangeRequestEvent implements DomainEvent {

    @JsonProperty("positionChangeId")
    private final int positionChangeId;

    @JsonProperty("employeeID")
    private final int employeeId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonProperty("description")
    private final String description;

    @JsonProperty("newPosition")
    private final String newPosition;

    @JsonCreator
    public PositionChangeRequestEvent(@JsonProperty("positionChangeId") int positionChangeId,
                                      @JsonProperty("employeeId") int employeeId,
                                      @JsonProperty("occurredOn") Instant occurredOn,
                                      @JsonProperty("description") String description,
                                      @JsonProperty("newPosition") String newPosition){
        this.positionChangeId = positionChangeId;
        this.employeeId = employeeId;
        this.occurredOn = occurredOn;
        this.description = description;
        this.newPosition = newPosition;
    }


    public Instant occurredOn() {
        return occurredOn;
    }

    public String getDescription() {
        return description;
    }

}
