package emt.miniproekt.hr_enhancer.employeesector.port;

import emt.miniproekt.sharedkernel.eventlog.consumer.RemoteEventLog;
import emt.miniproekt.sharedkernel.eventlog.consumer.RemoteEventLogImpl;
import emt.miniproekt.sharedkernel.eventlog.consumer.RemoteEventLogService;
import emt.miniproekt.sharedkernel.eventlog.producer.StoredDomainEvent;
import lombok.NonNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;


@Service
public class EmployeeSectorEventsClient implements RemoteEventLogService {

    private String source;
    private String serverURL;
    private RestTemplate restTemplate;  // rest template koj se koristi za povicite kon apito


    // mora da imas timeout vrednosti
//    public EmployeeSectorEventsClient(String serverURL, int connectionTimeout, int readTimeout){
//        this.source = Objects.requireNonNull(serverURL, "Server URL must not be null");
//        this.serverURL = serverURL;
//        this.restTemplate = new RestTemplate();
//
//        // nikogas ne smees da pravis povici bez timeouts
//        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//        requestFactory.setConnectTimeout(connectionTimeout);
//        requestFactory.setReadTimeout(readTimeout);
//
//        restTemplate.setRequestFactory(requestFactory);
//
//    }
    public EmployeeSectorEventsClient(){
        // this.source = Objects.requireNonNull(serverURL, "Server URL must not be null");
        this.serverURL = "http://localhost:8081";
        this.restTemplate = new RestTemplate();

        // nikogas ne smees da pravis povici bez timeouts
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(10000);

        restTemplate.setRequestFactory(requestFactory);

    }

    @Override
    public String getSource() {
        // return source;
        return this.serverURL;
    }

    // builds the URL and calls the api with it
    @Override
    public RemoteEventLog getCurrentLog(int lastProcessedId) {
        URI eventLogURI = UriComponentsBuilder.fromUriString(serverURL)
                .path("/api/eventLogs/"+lastProcessedId).build().toUri();
        return retrieveCurrentLog(eventLogURI);
    }

    // calls the api
    // TODO: Proveri ova dali raboti okej
    public RemoteEventLog retrieveCurrentLog(@NonNull URI eventLogUri){
        // api call
        ResponseEntity<List<StoredDomainEvent>> resp = restTemplate.exchange(eventLogUri, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<StoredDomainEvent>>() {
                });

        if (resp.getStatusCode() != HttpStatus.OK){
            throw new IllegalArgumentException("Failed at getting eventLog from api");
        }

        return new RemoteEventLogImpl(resp);
        //return null;
    }
}