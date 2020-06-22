package emt.miniproekt.userrequest.domain.repository;

import emt.miniproekt.userrequest.domain.model.RestDaysRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestDaysRequestRepoJPA extends JpaRepository<RestDaysRequest, Integer> {
}
