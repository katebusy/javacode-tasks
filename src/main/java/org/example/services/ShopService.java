package org.example.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.models.User;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ShopService {
    private UserRepository repository;
    @Autowired
    public ShopService( UserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUser(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no user with id: " + id));
    }

    @Transactional
    public User createUser(User user) {
        return repository.saveAndFlush(user);
    }

    @Transactional
    public User updateUser(UUID id, User user) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("There is no user with id: " + id);
        }
        user.setId(id);
        return repository.saveAndFlush(user);
    }

    @Transactional
    public void deleteUser(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("There is no user with id: " + id);
        }
        repository.deleteById(id);
    }
}
