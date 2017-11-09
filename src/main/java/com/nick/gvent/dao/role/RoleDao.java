package com.nick.gvent.dao.role;


import com.nick.gvent.entity.Role;

public interface RoleDao {

    void save(Role role);

    Role getById(Long id);
}
