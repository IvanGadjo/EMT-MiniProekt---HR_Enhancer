package emt.miniproekt.hr_enhancer.employeesector.integration;

import emt.miniproekt.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

public class PositionChangeEvent implements DomainEvent {
    @Override
    public Instant occurredOn() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
