package com.nick.gvent.dto;

import com.nick.gvent.entity.Event;
import com.nick.gvent.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/** Simple POJO class for User
 * @autor Fetissov Mikalai
 * @version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    private Set<Role> authorities;

    private List<Event> events;

    private String username;

    private String firstName;

    private String lastName;

    private Integer age;

    private String gender;

    private String email;

    private String password;

    private String passwordCheck;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;
}
