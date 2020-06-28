package emt.miniproekt.hr_enhancer.employeesector.integration;

import emt.miniproekt.sharedkernel.domain.base.DomainEvent;
import lombok.Getter;

import java.time.Instant;

@Getter

public class RaiseEvent implements DomainEvent {

    private final int employeeId;

    private final Instant occurredOn;

    private final String description;

    private final double newSalary;

    public RaiseEvent(int employeeId, Instant occurredOn, String description, double newSalary) {
        this.employeeId = employeeId;
        this.occurredOn = occurredOn;
        this.description = description;
        this.newSalary = newSalary;
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
