package com.nick.gvent.dao.role;


import com.nick.gvent.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RoleDao extends JpaRepository<Role,Long> {

    Role findByName(String name);
//    @Modifying
//    @Query(value = "insert into Logger (redirect,user_id) VALUES (:insertLink,:id)", nativeQuery = true)
//    @Transactional
//    void logURI(@Param("insertLink") String insertLink, @Param("id") Long id);
//    @Modifying
//    @Query(value = "INSERT into Role (id,name) VALUES (:id,:name)")
//    @Transactional
//    void persist(@Param("id") Long id,@Param("name") String name);

//    Role save(Role role);
//
//    Role getById(Long id);
}
