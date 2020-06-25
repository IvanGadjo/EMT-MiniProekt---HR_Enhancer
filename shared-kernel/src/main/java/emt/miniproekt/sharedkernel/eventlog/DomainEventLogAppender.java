package emt.miniproekt.sharedkernel.eventlog;

import emt.miniproekt.sharedkernel.domain.base.DomainEvent;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

public class DomainEventLogAppender {

    private DomainEventLogService domainEventLogService;

    public DomainEventLogAppender(DomainEventLogService domainEventLogService){
        this.domainEventLogService = domainEventLogService;
    }

    public DomainEventLogAppender() {

    }

    // slusa za event, go predava na domainEventLogService
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onDomainEvent(DomainEvent domainEvent){
        domainEventLogService.append(domainEvent);
    }
}
