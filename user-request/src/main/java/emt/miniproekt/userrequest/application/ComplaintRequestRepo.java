package emt.miniproekt.userrequest.application;

import emt.miniproekt.userrequest.domain.model.ComplaintRequest;
import emt.miniproekt.userrequest.domain.repository.ComplaintRequestRepoJPA;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class ComplaintRequestRepo {
    ComplaintRequestRepoJPA complaintRequestRepoJPA;

    public ComplaintRequestRepo(ComplaintRequestRepoJPA complaintRequestRepoJPA) {
        this.complaintRequestRepoJPA = complaintRequestRepoJPA;
    }

    public List<ComplaintRequest> findAll() {
        return complaintRequestRepoJPA.findAll();
    }

    public ComplaintRequest findById(int id) {
        return complaintRequestRepoJPA.findById(id).orElseThrow(RuntimeException::new);
    }

    public ComplaintRequest saveNewComplaintRequest(ComplaintRequest complaintRequest) {
        return complaintRequestRepoJPA.save(complaintRequest);
    }

    public void deleteById(int id) {
        complaintRequestRepoJPA.deleteById(id);
    }

}
