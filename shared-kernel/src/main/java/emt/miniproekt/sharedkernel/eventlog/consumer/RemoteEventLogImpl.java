package emt.miniproekt.sharedkernel.eventlog.consumer;

import emt.miniproekt.sharedkernel.eventlog.producer.StoredDomainEvent;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class RemoteEventLogImpl implements RemoteEventLog{

    private List<StoredDomainEvent> storedDomainEvents;

    public RemoteEventLogImpl(ResponseEntity<List<StoredDomainEvent>> responseEntity){
        this.storedDomainEvents = responseEntity.getBody();
    }

    @Override
    public List<StoredDomainEvent> events() {
        return storedDomainEvents;
    }
}
