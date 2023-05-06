package com.vitmvit.smarteducation.model.entity;

import com.vitmvit.smarteducation.model.entity.parent.IdNameLogEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class User extends IdNameLogEntity implements UserDetails {

    @Basic
    @Column(name = "cabinet_teacher_id", insertable = false, updatable = false)
    private Long cabinetTeacherId;

    @Basic
    @Column(name = "cabinet_student_id", insertable = false, updatable = false)
    private Long cabinetStudentId;

    private String avatarUuid;

    @Column(nullable = false)
    private String login;

    @Column(name = "password_hash", nullable = false)
    private String password;

    private String lastName;
    private String middleName;
    private String phoneNumber;
    private Integer studentNumber;

    // 0 - зарегестрирован
    // 1 - активен
    // 2 - не активен
    private Integer active;
    private String description;

    //------------------------------------------------------------------------------------------------------------------

    @ManyToMany
    @JoinTable(
            name = "link_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            foreignKey = @ForeignKey(name = "fk_user_to_role")
    )
    private List<Role> roleList;

    @OneToOne
    @JoinColumn(name = "cabinet_teacher_id", referencedColumnName = "id")
    private CabinetTeacher cabinetTeacher;

    @OneToOne
    @JoinColumn(name = "cabinet_student_id", referencedColumnName = "id")
    private CabinetStudent cabinetStudent;

    //------------------------------------------------------------------------------------------------------------------

    public boolean notEquals(User user) {
        return !equals(user);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        if (!Objects.equals(getName(), user.getName())) return false;
        if (!Objects.equals(login, user.login)) return false;
        if (!Objects.equals(lastName, user.lastName)) return false;
        return Objects.equals(middleName, user.middleName);
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        return result;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoleList();
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
