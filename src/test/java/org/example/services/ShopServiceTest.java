package org.example.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.TestUtils;
import org.example.models.User;
import org.example.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringJUnitConfig({
        ShopService.class
})
@MockBean(UserRepository.class)
class ShopServiceTest {
    @Autowired
    private UserRepository repository;
    @Autowired
    private ShopService service;

    @Test
    void getAllUsers() {
        User user1 = TestUtils.buildUser();
        User user2 = TestUtils.buildUser();
        User user3 = TestUtils.buildUser();
        List<User> expectedList = List.of(user1, user2, user3);

        when(repository.findAll()).thenReturn(expectedList);

        List<User> actualList = service.getAllUsers();
        assertEquals(expectedList, actualList);

        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void getExcitingUser() {
        UUID requestId = UUID.randomUUID();
        User expectedUser = TestUtils.buildUser();
        expectedUser.setId(requestId);
        when(repository.findById(any())).thenReturn(Optional.of(expectedUser));

        User actualUser = service.getUser(requestId);

        assertEquals(expectedUser, actualUser);

        verify(repository).findById(requestId);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void getUnexcitingUser() {
        UUID requestId = UUID.randomUUID();

        when(repository.findById(any())).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            service.getUser(requestId);
        });
        String expectedMessage = String.format("There is no user with id: %s", requestId);
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(repository).findById(requestId);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void createUser() {
        User expectedUser = TestUtils.buildUser();

        when(repository.saveAndFlush(any())).thenAnswer(invocation -> {
            User user = invocation.getArgument(0, User.class);
            return user;
        });

        User actualUser = service.createUser(expectedUser);

        assertEquals(expectedUser, actualUser);
        verify(repository).saveAndFlush(expectedUser);
        verifyNoMoreInteractions(repository);

    }

    @Test
    void updateExcitingUser() {
        UUID requestId = UUID.randomUUID();
        User expectedUser = TestUtils.buildUser();

        when(repository.existsById(any())).thenReturn(true);
        when(repository.saveAndFlush(any())).thenAnswer(invocation -> {
            User user = invocation.getArgument(0, User.class);
            return user;
        });

        User actualUser = service.updateUser(requestId, expectedUser);

        assertEquals(expectedUser, actualUser);
        verify(repository).existsById(requestId);
        verify(repository).saveAndFlush(expectedUser);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void updateUnexcitingUser() {
        UUID requestId = UUID.randomUUID();
        User requestUser = TestUtils.buildUser();

        when(repository.existsById(any())).thenReturn(false);

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            service.updateUser(requestId, requestUser);
        });
        String expectedMessage = String.format("There is no user with id: %s", requestId);
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(repository).existsById(requestId);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteExcitingUser() {
        UUID requestId = UUID.randomUUID();

        when(repository.existsById(any())).thenReturn(true);

        service.deleteUser(requestId);


        verify(repository).existsById(requestId);
        verify(repository).deleteById(requestId);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteUnexcitingUser() {
        UUID requestId = UUID.randomUUID();

        when(repository.existsById(any())).thenReturn(false);

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            service.deleteUser(requestId);
        });
        String expectedMessage = String.format("There is no user with id: %s", requestId);
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(repository).existsById(requestId);
        verifyNoMoreInteractions(repository);
    }
}