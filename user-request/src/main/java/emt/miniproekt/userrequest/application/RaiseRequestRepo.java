package emt.miniproekt.userrequest.application;

import emt.miniproekt.userrequest.domain.model.RaiseRequest;
import emt.miniproekt.userrequest.domain.repository.RaiseRequestRepoJPA;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class RaiseRequestRepo {
    RaiseRequestRepoJPA raiseRequestRepoJPA;

    public RaiseRequestRepo(RaiseRequestRepoJPA raiseRequestRepoJPA) {
        this.raiseRequestRepoJPA = raiseRequestRepoJPA;
    }

    public List<RaiseRequest> findAll() {
        return raiseRequestRepoJPA.findAll();
    }

    public RaiseRequest findById(int id) {
        return raiseRequestRepoJPA.findById(id).orElseThrow(RuntimeException::new);
    }

    public RaiseRequest addNewRaiseRequest(RaiseRequest raiseRequest) {
        return raiseRequestRepoJPA.save(raiseRequest);
    }

    public void deleteById(int id) {
        raiseRequestRepoJPA.deleteById(id);
    }

}
