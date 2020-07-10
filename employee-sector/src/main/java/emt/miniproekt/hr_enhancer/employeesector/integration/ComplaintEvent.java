package emt.miniproekt.hr_enhancer.employeesector.integration;

import emt.miniproekt.sharedkernel.domain.base.DomainEvent;
import lombok.Getter;

import java.time.Instant;

@Getter

public class ComplaintEvent implements DomainEvent {

    private final int complaintId;

    private final int employeeId;

    private final Instant occurredOn;

    private final String description;

    public ComplaintEvent(int complaintId, int employeeId, Instant occurredOn, String description) {
        this.complaintId = complaintId;
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

    public int getComplaintId(){
        return complaintId;
    }

    public int getEmployeeId(){
        return employeeId;
    }
}
