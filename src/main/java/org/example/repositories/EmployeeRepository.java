package org.example.repositories;

import org.example.entities.Employee;
import org.example.projections.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, UUID> {
    @Query("SELECT e FROM Employee e")
    List<EmployeeProjection> findAllProjectedBy();
    @Query("SELECT e FROM Employee e WHERE e.id = :id")
    Optional<EmployeeProjection> findProjectedById(UUID id);
}