package com.nick.gvent.dao.role;


import com.nick.gvent.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Long> {

    Role findByName(String name);

//    Role save(Role role);
//
//    Role getById(Long id);
}
