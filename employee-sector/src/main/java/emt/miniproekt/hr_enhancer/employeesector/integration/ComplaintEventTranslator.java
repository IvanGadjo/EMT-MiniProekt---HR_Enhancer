package emt.miniproekt.hr_enhancer.employeesector.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import emt.miniproekt.sharedkernel.domain.base.DomainEvent;
import emt.miniproekt.sharedkernel.eventlog.consumer.RemoteEventTranslator;
import emt.miniproekt.sharedkernel.eventlog.producer.StoredDomainEvent;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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


    // TODO: Tuka ne go deserijalizira kako sto treba JSON-ot, ne zema employeeId i requestId od body na JSON objektot
    @Override
    public Optional<DomainEvent> translate(StoredDomainEvent remoteEvent) {
        DomainEvent de =Optional.of(remoteEvent.toDomainEvent(objectMapper, ComplaintEvent.class)).orElseThrow(IllegalArgumentException::new);
        System.out.println("de");
        return Optional.of(remoteEvent.toDomainEvent(objectMapper, ComplaintEvent.class));
    }
}
