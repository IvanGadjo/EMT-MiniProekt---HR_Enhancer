package emt.miniproekt.userrequest.domain.repository;

import emt.miniproekt.userrequest.domain.model.PositionChangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionChangeRequestRepoJPA extends JpaRepository<PositionChangeRequest, Integer> {
}
