package emt.miniproekt.sharedkernel.eventlog;

import emt.miniproekt.sharedkernel.domain.base.DomainEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DomainEventLogService {

    StoredDomainEventRepo storedDomainEventRepo;

    public DomainEventLogService(StoredDomainEventRepo storedDomainEventRepo){
        this.storedDomainEventRepo = storedDomainEventRepo;
    }

    public DomainEventLogService() {

    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void append(DomainEvent domainEvent){
        StoredDomainEvent storedDomainEvent = new StoredDomainEvent(domainEvent);
        storedDomainEventRepo.saveAndFlush(storedDomainEvent);
    }

    // TODO:
//    @NonNull
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
//    public List<StoredDomainEvent> retrieveLog(long lastProcessedEventId) {
//
//    }


}
