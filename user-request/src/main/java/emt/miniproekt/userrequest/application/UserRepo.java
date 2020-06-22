package emt.miniproekt.userrequest.application;

import emt.miniproekt.userrequest.domain.model.User;
import emt.miniproekt.userrequest.domain.repository.UserRepoJPA;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class UserRepo {
    UserRepoJPA userRepoJPA;

    public UserRepo(UserRepoJPA userRepoJPA) {
        this.userRepoJPA = userRepoJPA;
    }

    public List<User> findAll() {
        return userRepoJPA.findAll();
    }

    public User findById(int id) {
        return userRepoJPA.findById(id).orElseThrow(RuntimeException::new);
    }

    public User saveNewUser(User user) {
        return userRepoJPA.save(user);
    }

    public void deleteById(int id) {
        userRepoJPA.deleteById(id);
    }

}

