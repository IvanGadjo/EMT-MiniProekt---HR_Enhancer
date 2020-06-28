package emt.miniproekt.userrequest.application;

import emt.miniproekt.userrequest.domain.model.RestDaysRequest;
import emt.miniproekt.userrequest.domain.repository.RestDaysRequestRepoJPA;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class RestDaysRequestRepo {
    RestDaysRequestRepoJPA restDaysRequestRepoJPA;

    public RestDaysRequestRepo (RestDaysRequestRepoJPA restDaysRequestRepoJPA) {
        this.restDaysRequestRepoJPA = restDaysRequestRepoJPA;
    }

    public List<RestDaysRequest> findAll() {
        return restDaysRequestRepoJPA.findAll();
    }

    public RestDaysRequest findById(int id) {
        return restDaysRequestRepoJPA.findById(id).orElseThrow(RuntimeException::new);
    }

    public RestDaysRequest saveNewRestDaysRequest(RestDaysRequest restDaysRequest) {
        return restDaysRequestRepoJPA.save(restDaysRequest);
    }

    public void deleteById(int id) {
        restDaysRequestRepoJPA.deleteById(id);
    }
}
