package emt.miniproekt.sharedkernel.eventlog.consumer;

import emt.miniproekt.sharedkernel.eventlog.consumer.jpa.ProcessedRemoteEventRepoJPA;
import emt.miniproekt.sharedkernel.eventlog.producer.StoredDomainEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
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

    @Scheduled(fixedDelay = 20000)
    public void processEvents() {
        remoteEventLogServices.values().forEach(remEvlogServ -> {
            RemoteEventLog remoteEventLog = remEvlogServ.getCurrentLog(getLastProcessedId(remEvlogServ));


            List<StoredDomainEvent> storedDomainEvents = remoteEventLog.events();


            for(StoredDomainEvent s: storedDomainEvents){
                System.out.println(s);
            }

            // rabota so transaction template (line 55 funk)
            storedDomainEvents.forEach(StDomEvent ->{
                transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                        publishEvent(StDomEvent);
                        setLastProcessedId(remEvlogServ, StDomEvent.getId());
                    }
                });
            });
        });
    }

    public int getLastProcessedId(@NonNull RemoteEventLogService remoteEventLogService){
        //  Integer id = processedRemoteEventRepoJPA.findProcessedRemoteEventWithSource(remoteEventLogService.getSource()).getId();
        Integer id = processedRemoteEventRepoJPA.findById(remoteEventLogService.getSource())
                .map(ProcessedRemoteEvent::getLastProcessedEventId)
                .orElse(0);

        return id;
//        if(id == null)
//            return 0;
//        return id;
    }

    public void setLastProcessedId(@NonNull RemoteEventLogService remoteEventLogService, int eventId){
        ProcessedRemoteEvent processedRemoteEvent = new ProcessedRemoteEvent(remoteEventLogService.getSource(), eventId);
        processedRemoteEventRepoJPA.saveAndFlush(processedRemoteEvent);
    }

    public void publishEvent(@NonNull StoredDomainEvent storedDomainEvent){
        remoteEventTranslators.values().stream()
                .filter(translator -> translator.supports(storedDomainEvent))
                .findFirst()
                .flatMap(translator -> translator.translate(storedDomainEvent))
                .ifPresent(applicationEventPublisher::publishEvent);
    }
}
