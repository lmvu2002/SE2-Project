package com.project.se2project.repository;

import com.project.se2project.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByName(String Name);
    Admin findById(long id);

}
