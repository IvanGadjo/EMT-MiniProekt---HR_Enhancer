package emt.miniproekt.sharedkernel.port.rest;


import emt.miniproekt.sharedkernel.eventlog.DomainEventLogService;
import emt.miniproekt.sharedkernel.eventlog.StoredDomainEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/eventLogs")
public class EventLogController {

    DomainEventLogService domainEventLogService;

    public EventLogController(DomainEventLogService domainEventLogService){
        this.domainEventLogService = domainEventLogService;
    }

//    @GetMapping(path = "/{lastProcessedId}")
//    public ResponseEntity<List<StoredDomainEvent>> getLastEvents(@PathVariable("lastProcessedId") int lastProcessedId){
//
//    }
}
