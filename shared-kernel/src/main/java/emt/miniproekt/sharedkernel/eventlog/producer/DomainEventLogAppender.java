package emt.miniproekt.sharedkernel.eventlog.producer;

import emt.miniproekt.sharedkernel.domain.base.DomainEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service

public class DomainEventLogAppender {

    private DomainEventLogService domainEventLogService;

    public DomainEventLogAppender(DomainEventLogService domainEventLogService){
        this.domainEventLogService = domainEventLogService;
    }


    // slusa za event, go predava na domainEventLogService
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onDomainEvent(DomainEvent domainEvent){
        domainEventLogService.append(domainEvent);
    }
}
