package emt.miniproekt.sharedkernel.eventlog.consumer.jpa;

import emt.miniproekt.sharedkernel.eventlog.consumer.ProcessedRemoteEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedRemoteEventRepoJPA extends JpaRepository<ProcessedRemoteEvent, Integer> {
}
