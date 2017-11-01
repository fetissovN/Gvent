package com.nick.gvent.dao;

import com.nick.gvent.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemindRepository extends JpaRepository<Quiz, Long> {

}
