package emt.miniproekt.hr_enhancer.employeesector.integration;

import emt.miniproekt.sharedkernel.domain.base.DomainEvent;
import lombok.Getter;

import java.time.Instant;

@Getter

public class ComplaintEvent implements DomainEvent {

    private final int employeeId;

    private final Instant occurredOn;

    private final String description;

    public ComplaintEvent(int employeeId, Instant occurredOn, String description) {
        this.employeeId = employeeId;
        this.occurredOn = occurredOn;
        this.description = description;
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
