package com.vitmvit.smarteducation.repository;

import com.vitmvit.smarteducation.model.entity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, String> {

    Optional<Avatar> findByGeneratedName(String uuid);

    void deleteByGeneratedName(String uuid);
}
