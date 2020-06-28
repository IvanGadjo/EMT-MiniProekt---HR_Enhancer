package emt.miniproekt.hr_enhancer.employeesector.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import emt.miniproekt.sharedkernel.domain.base.DomainEvent;
import emt.miniproekt.sharedkernel.eventlog.consumer.RemoteEventTranslator;
import emt.miniproekt.sharedkernel.eventlog.producer.StoredDomainEvent;

import java.util.Optional;

public class ComplaintEventTranslator implements RemoteEventTranslator {

    private final ObjectMapper objectMapper;

    public ComplaintEventTranslator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(StoredDomainEvent storedDomainEvent) {
        return storedDomainEvent.getDomainEventClassName().equals(
                "emt.miniproekt.userrequest.domain.event.ComplaintRequestEvent"
        );
    }

    @Override
    public Optional<DomainEvent> translate(StoredDomainEvent remoteEvent) {
        return Optional.of(remoteEvent.toDomainEvent(objectMapper, ComplaintEvent.class));
    }
}
