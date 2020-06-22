package emt.miniproekt.userrequest.application;

import emt.miniproekt.userrequest.domain.model.PositionChangeRequest;
import emt.miniproekt.userrequest.domain.repository.PositionChangeRequestRepoJPA;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class PositionChangeRequestRepo {
    PositionChangeRequestRepoJPA positionChangeRequestRepoJPA;

    public PositionChangeRequestRepo(PositionChangeRequestRepoJPA positionChangeRequestRepoJPA) {
        this.positionChangeRequestRepoJPA = positionChangeRequestRepoJPA;
    }

    public List<PositionChangeRequest> findAll() {
        return positionChangeRequestRepoJPA.findAll();
    }

    public PositionChangeRequest findById(int id) {
        return positionChangeRequestRepoJPA.findById(id).orElseThrow(RuntimeException::new);
    }

    public PositionChangeRequest saveNewPositionChangeRequest(PositionChangeRequest positionChangeRequest) {
        return positionChangeRequestRepoJPA.save(positionChangeRequest);
    }

    public void deleteById(int id) {
        positionChangeRequestRepoJPA.deleteById(id);
    }

}
