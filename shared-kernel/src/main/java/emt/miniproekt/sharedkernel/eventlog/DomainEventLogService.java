package emt.miniproekt.sharedkernel.eventlog;

import com.fasterxml.jackson.databind.ObjectMapper;
import emt.miniproekt.sharedkernel.domain.base.DomainEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DomainEventLogService {

    StoredDomainEventRepo storedDomainEventRepo;
    ObjectMapper objectMapper;

    public DomainEventLogService(StoredDomainEventRepo storedDomainEventRepo, ObjectMapper objectMapper){
        this.storedDomainEventRepo = storedDomainEventRepo;
        this.objectMapper = objectMapper;
    }

    public DomainEventLogService() {

    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void append(DomainEvent domainEvent){
        StoredDomainEvent storedDomainEvent = new StoredDomainEvent(domainEvent, objectMapper);
        storedDomainEventRepo.saveAndFlush(storedDomainEvent);
    }

    // TODO:
//    @NonNull
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
//    public List<StoredDomainEvent> retrieveLog(long lastProcessedEventId) {
//
//    }


}
