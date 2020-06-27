package emt.miniproekt.sharedkernel.eventlog.consumer;

import emt.miniproekt.sharedkernel.eventlog.consumer.jpa.ProcessedRemoteEventRepoJPA;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

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
}
