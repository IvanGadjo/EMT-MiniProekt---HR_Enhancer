package emt.miniproekt.sharedkernel.port.client;

import emt.miniproekt.sharedkernel.eventlog.consumer.RemoteEventLog;
import emt.miniproekt.sharedkernel.eventlog.consumer.RemoteEventLogService;
import emt.miniproekt.sharedkernel.eventlog.producer.StoredDomainEvent;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

public class RemoteEventLogServiceClient implements RemoteEventLogService {

    private String source;
    private String serverURL;
    private RestTemplate restTemplate;  // rest template koj se koristi za povicite kon apito


    // mora da imas timeout vrednosti
    public RemoteEventLogServiceClient(String serverURL, int connectionTimeout, int readTimeout){
        this.source = Objects.requireNonNull(serverURL, "Server URL must not be null");
        this.serverURL = serverURL;
        this.restTemplate = new RestTemplate();

        // nikogas ne smees da pravis povici bez timeouts
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectionTimeout);
        requestFactory.setReadTimeout(readTimeout);

        restTemplate.setRequestFactory(requestFactory);

    }

    @Override
    public String getSource() {
        return source;
    }

    // builds the URL and calls the api with it
    @Override
    public RemoteEventLog getCurrentLog(int lastProcessedId) {
        URI eventLogURI = UriComponentsBuilder.fromUriString(serverURL)
                .path("/api/eventLogs/"+lastProcessedId).build().toUri();
        return retrieveCurrentLog(eventLogURI);
    }

    // calls the api
    public RemoteEventLog retrieveCurrentLog(@NonNull URI eventLogUri){
        // api call
        ResponseEntity<List<StoredDomainEvent>> resp = restTemplate.exchange(eventLogUri, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<StoredDomainEvent>>() {
        });

        if (resp.getStatusCode() != HttpStatus.OK){
            throw new IllegalArgumentException("Failed at getting eventLog from api");
        }

        //return new RemoteEventLogImpl(resp);
        return null;
    }
}
