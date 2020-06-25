package emt.miniproekt.sharedkernel.domain.base;

import org.springframework.lang.NonNull;

import java.time.Instant;

public interface DomainEvent {

    @NonNull
    Instant occurredOn();

    String getDescription();
}
