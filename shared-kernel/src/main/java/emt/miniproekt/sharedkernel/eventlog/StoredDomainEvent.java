package emt.miniproekt.sharedkernel.eventlog;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import emt.miniproekt.sharedkernel.domain.base.DomainEvent;
import emt.miniproekt.sharedkernel.jackson.RawJsonDeserializer;
import lombok.Getter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Table(name = "domain_events")
public class StoredDomainEvent {

    private static final int MAX_JSON_BODY_LENGTH = 1024;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;

    @JsonProperty("occurredOn")
    private Instant occurredOn;


    private String description;

    @Column(name = "domain_event_class_name")
    @JsonProperty("domainEventClass")
    private String domainEventClassName;

    @Column(name = "domain_event_body", length = MAX_JSON_BODY_LENGTH)
    @JsonProperty("domainEventBody")
    @JsonRawValue
    @JsonDeserialize(using = RawJsonDeserializer.class)
    private String domainEventBody;

//    @Transient
//    private Class<? extends DomainEvent> domainEventClass;

    @SuppressWarnings("unused")
    public StoredDomainEvent(){}

    public StoredDomainEvent(DomainEvent domainEvent, ObjectMapper objectMapper){
        this.occurredOn = domainEvent.occurredOn();
        this.description = domainEvent.getDescription();
    }
}
