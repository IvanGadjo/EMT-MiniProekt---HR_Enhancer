package emt.miniproekt.hr_enhancer.employeesector.integration;

import emt.miniproekt.sharedkernel.domain.base.DomainEvent;
import lombok.Getter;

import java.time.Instant;

@Getter

public class RestDaysEvent implements DomainEvent {

    private final int restDaysId;

    private final int employeeId;

    private final Instant occurredOn;

    private final String description;

    private final int numDays;

    public RestDaysEvent(int restDaysId, int employeeId, Instant occurredOn, String description, int numDays) {
        this.restDaysId = restDaysId;
        this.employeeId = employeeId;
        this.occurredOn = occurredOn;
        this.description = description;
        this.numDays = numDays;
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
