package com.nick.gvent.dao.user;

import com.nick.gvent.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User,Long>{

    User findByUsername(String username);

    User save(@NonNull User user);

}
