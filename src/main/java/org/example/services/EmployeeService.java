package org.example.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.entities.Employee;
import org.example.projections.EmployeeProjection;
import org.example.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }
    @Transactional(readOnly = true)
    public List<EmployeeProjection> getAllEmployees() {
        return repository.findAllProjectedBy();
    }
    @Transactional(readOnly = true)
    public EmployeeProjection getEmployee(UUID id) {
        return repository.findProjectedById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no employee with this id: " + id));
    }
    @Transactional
    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }
    @Transactional
    public Employee updateEmployee(UUID id, Employee employee) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("There is no employee with this id: " + id);
        }
        employee.setId(id);
        return repository.save(employee);
    }
    @Transactional
    public void deleteEmployee(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("There is no employee with this id: " + id);
        }
        repository.deleteById(id);
    }
}
