package emt.miniproekt.userrequest.domain.repository;

import emt.miniproekt.userrequest.domain.model.ComplaintRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRequestRepoJPA extends JpaRepository<ComplaintRequest, Integer> {
}
