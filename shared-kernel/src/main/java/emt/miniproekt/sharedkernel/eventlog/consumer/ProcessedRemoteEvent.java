package emt.miniproekt.sharedkernel.eventlog.consumer;


import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "processed_remote_events")
@Getter
public class ProcessedRemoteEvent {

//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;

    @Id
    @Column(name = "source")    // source App from which the event occurred
    private String source;

    @Column(name = "last_processed_event_id")
    private int lastProcessedEventId;

    @SuppressWarnings("unused")
    private ProcessedRemoteEvent(){}

    public ProcessedRemoteEvent(String source, int lastProcessedEventId){
        this.source = source;
        this.lastProcessedEventId = lastProcessedEventId;
    }
}
