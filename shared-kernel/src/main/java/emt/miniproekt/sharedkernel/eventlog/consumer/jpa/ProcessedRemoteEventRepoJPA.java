package emt.miniproekt.sharedkernel.eventlog.consumer.jpa;

import emt.miniproekt.sharedkernel.eventlog.consumer.ProcessedRemoteEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProcessedRemoteEventRepoJPA extends JpaRepository<ProcessedRemoteEvent, Integer> {

    @Query("select p from processed_remote_events p where p.source like :source")
    ProcessedRemoteEvent findProcessedRemoteEventWithSource(@Param("source") String source);
}
