package emt.miniproekt.sharedkernel.eventlog;

import emt.miniproekt.sharedkernel.domain.base.DomainEvent;
import lombok.Getter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Table(name = "domain_events")
public class StoredDomainEvent {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Instant occuredOn;

    private String description;

    @SuppressWarnings("unused")
    public StoredDomainEvent(){}

    public StoredDomainEvent(DomainEvent domainEvent){
        this.occuredOn = domainEvent.occuredOn();
        this.description = domainEvent.getDesription();
    }
}
