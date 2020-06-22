package emt.miniproekt.userrequest.domain.repository;

import emt.miniproekt.userrequest.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepoJPA extends JpaRepository<User, Integer> {
}
