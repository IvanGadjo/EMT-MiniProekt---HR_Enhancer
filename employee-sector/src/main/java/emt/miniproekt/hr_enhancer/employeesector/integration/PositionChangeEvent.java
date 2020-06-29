package emt.miniproekt.hr_enhancer.employeesector.integration;

import emt.miniproekt.sharedkernel.domain.base.DomainEvent;
import lombok.Getter;

import java.time.Instant;

@Getter

public class PositionChangeEvent implements DomainEvent {

    private final int positionChangeId;

    private final int employeeId;

    private final String description;

    private final Instant occurredOn;

    private final String newPosition;

    public PositionChangeEvent(int positionChangeId, int employeeId, Instant occurredOn, String description, String newPosition) {
        this.positionChangeId = positionChangeId;
        this.employeeId = employeeId;
        this.occurredOn = occurredOn;
        this.description = description;
        this.newPosition = newPosition;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
