package emt.miniproekt.userrequest.domain.repository;

import emt.miniproekt.userrequest.domain.model.RaiseRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaiseRequestRepoJPA extends JpaRepository<RaiseRequest, Integer> {
}
