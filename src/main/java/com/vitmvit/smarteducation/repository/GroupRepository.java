package com.vitmvit.smarteducation.repository;

import com.vitmvit.smarteducation.model.constant.GroupStatus;
import com.vitmvit.smarteducation.model.entity.SGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<SGroup, Long> {

    @Query("SELECT COUNT(g.id) FROM SGroup g WHERE CONCAT(g.name, g.number) LIKE :name")
    boolean existsByName(String name);

    @Query("SELECT g FROM SGroup g WHERE CONCAT(g.name, g.number) LIKE :name")
    Optional<SGroup> findOneByName(String name);

    List<SGroup> findAllByGroupStatus(GroupStatus groupStatus);
}
