package com.vitmvit.smarteducation.model.entity;

import com.vitmvit.smarteducation.model.entity.parent.IdNameEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

import static com.vitmvit.smarteducation.config.constants.Constants.ROLE_PREFIX;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Role extends IdNameEntity implements GrantedAuthority {

    @ManyToMany
    @JoinTable(
            name = "link_user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            foreignKey = @ForeignKey(name = "fk_role_to_user")
    )
    private List<User> userList;

    public Role(String name) {
        setName(name);
    }

    /**
     * @see "com.vitmvit.smarteducation.converter.UserDetailsConverterImpl"
     */
    public String getFullName() {
        return ROLE_PREFIX + getName();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Role role = (Role) object;
        return !getName().equals(role.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
