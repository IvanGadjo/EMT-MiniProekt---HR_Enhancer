package emt.miniproekt.sharedkernel.eventlog.producer;

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


    @Transactional(propagation = Propagation.MANDATORY)
    public void append(DomainEvent domainEvent){
        StoredDomainEvent storedDomainEvent = new StoredDomainEvent(domainEvent, objectMapper);
        storedDomainEventRepo.saveAndFlush(storedDomainEvent);
    }

    // propagation = Propagation.REQUIRED ==>>  Support a current transaction, create a new one if none exists.
    @NonNull
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<StoredDomainEvent> retrieveLog(int lastReadEventId) {
        Integer lastDomainEventId = storedDomainEventRepo.findLastDomainEventId();  // mora Integer, ne int => za da moze da e null ako ne postoi vo DB
        if (lastDomainEventId == null)
            lastDomainEventId = 0;
        return storedDomainEventRepo.findEventsBetween(lastDomainEventId, lastReadEventId);
    }


}
