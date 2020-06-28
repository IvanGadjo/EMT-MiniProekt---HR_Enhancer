package emt.miniproekt.sharedkernel.eventlog.consumer;

public interface RemoteEventLogService {

    String getSource();

    RemoteEventLog getCurrentLog(int lastProcessedId);
}
