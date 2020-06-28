package emt.miniproekt.sharedkernel.eventlog.consumer;

import emt.miniproekt.sharedkernel.eventlog.producer.StoredDomainEvent;

import java.util.List;

public interface RemoteEventLog {

    List<StoredDomainEvent> events();
}
