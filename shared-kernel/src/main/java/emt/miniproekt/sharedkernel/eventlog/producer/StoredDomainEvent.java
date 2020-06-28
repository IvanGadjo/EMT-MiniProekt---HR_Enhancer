package emt.miniproekt.sharedkernel.eventlog.producer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import emt.miniproekt.sharedkernel.domain.base.DomainEvent;
import emt.miniproekt.sharedkernel.jackson.RawJsonDeserializer;
import lombok.Getter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.IOException;
import java.time.Instant;
import java.util.Objects;

@Entity
@Getter
@Table(name = "domain_events")
public class StoredDomainEvent {

    private static final int MAX_JSON_BODY_LENGTH = 1024;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;

    @JsonProperty("occurredOn")
    @Column(name = "occurredOn")
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
        this.domainEventClassName = domainEvent.getClass().getName();
        try {
            domainEventBody = objectMapper.writeValueAsString(domainEvent);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("JSON serialization failed", e);
        }

        if (domainEventBody.length() > MAX_JSON_BODY_LENGTH){
            throw new IllegalArgumentException("Too long event body to serialize as JSON");
        }
    }



    @NonNull
    public String toJsonString() {
        return domainEventBody;
    }


    @NonNull
    public JsonNode toJsonNode(@NonNull ObjectMapper objectMapper) {
        Objects.requireNonNull(objectMapper, "objectMapper must not be null");
        try {
            return objectMapper.readTree(domainEventBody);
        } catch (IOException ex) {
            throw new IllegalStateException("Could not deserialize domain event from JSON", ex);
        }
    }

    @NonNull
    public String getDomainEventClassName(){
        return domainEventClassName;
    }


    @NonNull
    public <T extends DomainEvent> T toDomainEvent(@NonNull ObjectMapper objectMapper,
                                                   @NonNull Class<T> domainEventClass) {
        Objects.requireNonNull(objectMapper, "objectMapper must not be null");
        Objects.requireNonNull(domainEventClass, "domainEventClass must not be null");
        try {
            return objectMapper.readValue(domainEventBody, domainEventClass);
        } catch (IOException ex) {
            throw new IllegalStateException("Could not deserialize domain event from JSON", ex);
        }
    }

    // FIXME: Mozebi radi deserializerot ke treba da se implementira
    // toJsonString()
    // toJsonNode()
    // domainEventClass()
    // lookupDomainEventClass()
    // occurredOn()
    // toString()
}
