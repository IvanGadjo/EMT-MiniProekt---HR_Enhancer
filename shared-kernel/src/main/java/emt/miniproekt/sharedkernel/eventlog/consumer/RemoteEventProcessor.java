package emt.miniproekt.sharedkernel.eventlog.consumer;

import emt.miniproekt.sharedkernel.eventlog.consumer.jpa.ProcessedRemoteEventRepoJPA;
import emt.miniproekt.sharedkernel.eventlog.producer.StoredDomainEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;

@Service
public class RemoteEventProcessor {

    private final ProcessedRemoteEventRepoJPA processedRemoteEventRepoJPA;
    private final Map<String, RemoteEventLogService> remoteEventLogServices;
    private final Map<String, RemoteEventTranslator> remoteEventTranslators;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final TransactionTemplate transactionTemplate;

    public RemoteEventProcessor(ProcessedRemoteEventRepoJPA processedRemoteEventRepoJPA,
                                Map<String, RemoteEventLogService> remoteEventLogServices,
                                Map<String, RemoteEventTranslator> remoteEventTranslators,
                                ApplicationEventPublisher applicationEventPublisher,
                                TransactionTemplate transactionTemplate){
        this.processedRemoteEventRepoJPA = processedRemoteEventRepoJPA;
        this.remoteEventLogServices = remoteEventLogServices;
        this.remoteEventTranslators = remoteEventTranslators;
        this.applicationEventPublisher = applicationEventPublisher;
        this.transactionTemplate = transactionTemplate;
    }

    // @Scheduled(fixedDelay = 20000)
    public void processEvents() {
        remoteEventLogServices.values().forEach(remEvlogServ -> {
            RemoteEventLog remoteEventLog = remEvlogServ.getCurrentLog(getLastProcessedId(remEvlogServ));
            List<StoredDomainEvent> storedDomainEvents = remoteEventLog.events();


            // rabota so transaction template (line 55 funk)
        });
    }

    public int getLastProcessedId(@NonNull RemoteEventLogService remoteEventLogService){
        Integer id = processedRemoteEventRepoJPA.findProcessedRemoteEventWithSource(remoteEventLogService.getSource()).getId();

        if(id == null)
            return 0;
        return id;
    }
}
