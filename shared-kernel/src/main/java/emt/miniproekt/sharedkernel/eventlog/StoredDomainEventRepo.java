package emt.miniproekt.sharedkernel.eventlog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoredDomainEventRepo extends JpaRepository<StoredDomainEvent, Integer> {

    @Query(value = "select max(id) from domain_events", nativeQuery = true)
    int findLastDomainEventId();

    @Query(value = "select * from domain_events" +
            "where id<= :newestId and id> :lastReadId ", nativeQuery = true)
    List<StoredDomainEvent> findEventsBetween(@Param("newestId") int newestId,
                                              @Param("lastReadId") int lastReadId);
}
