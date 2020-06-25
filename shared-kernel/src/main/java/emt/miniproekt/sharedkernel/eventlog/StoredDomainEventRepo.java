package emt.miniproekt.sharedkernel.eventlog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoredDomainEventRepo extends JpaRepository<StoredDomainEvent, Integer> {
}
