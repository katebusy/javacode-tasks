package org.example.controllers;

import lombok.SneakyThrows;
import org.example.TestUtils;
import org.example.models.User;
import org.example.services.ShopService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig({
        UserController.class
})
@MockBean(ShopService.class)
@WebMvcTest
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ShopService service;

    @Test
    @SneakyThrows
    void getAllUsers() {
        User user1 = TestUtils.buildUser();
        User user2 = TestUtils.buildUser();
        User user3 = TestUtils.buildUser();
        List<User> responseList = List.of(user1, user2, user3);
        when(service.getAllUsers()).thenReturn(responseList);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$[0].name", Matchers.is(user1.getName())),
                        jsonPath("$[0].email", Matchers.is(user1.getEmail())),
                        jsonPath("$[1].name", Matchers.is(user2.getName())),
                        jsonPath("$[1].email", Matchers.is(user2.getEmail())),
                        jsonPath("$[2].name", Matchers.is(user3.getName())),
                        jsonPath("$[2].email", Matchers.is(user3.getEmail()))
                );

        verify(service).getAllUsers();
        verifyNoMoreInteractions(service);
    }

    @Test
    @SneakyThrows
    void getUser() {
        User responseUser = TestUtils.buildUser();
        UUID requestId = responseUser.getId();

        when(service.getUser(any())).thenReturn(responseUser);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/" + requestId)
                        .param("id", requestId.toString()))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.name",
                                Matchers.is(responseUser.getName())),
                        jsonPath("$.email",
                                Matchers.is(responseUser.getEmail())),
                        jsonPath("$.orders[0].products",
                                Matchers.is(responseUser.getOrders().get(0).getProducts())),
                        jsonPath("$.orders[0].status",
                                Matchers.is(responseUser.getOrders().get(0).getStatus())),
                        jsonPath("$.orders[0].sum",
                                Matchers.comparesEqualTo(responseUser.getOrders().get(0).getSum().intValue())),
                        jsonPath("$.orders[1].products",
                                Matchers.is(responseUser.getOrders().get(1).getProducts())),
                        jsonPath("$.orders[1].status",
                                Matchers.is(responseUser.getOrders().get(1).getStatus())),
                        jsonPath("$.orders[1].sum",
                                Matchers.comparesEqualTo(responseUser.getOrders().get(1).getSum().intValue())),
                        jsonPath("$.orders[2].products",
                                Matchers.is(responseUser.getOrders().get(2).getProducts())),
                        jsonPath("$.orders[2].status",
                                Matchers.is(responseUser.getOrders().get(2).getStatus())),
                        jsonPath("$.orders[2].sum",
                                Matchers.comparesEqualTo(responseUser.getOrders().get(2).getSum().intValue()))

                );

        verify(service).getUser(requestId);
        verifyNoMoreInteractions(service);
    }

    @Test
    @SneakyThrows
    void createUser() {
        User request = TestUtils.buildUser();
        User response = request;

        when(service.createUser(any())).thenReturn(response);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.jsonStringify(request)))
                .andExpect(status().isCreated())
                .andExpectAll(
                        jsonPath("$.name", Matchers.is(response.getName())),
                        jsonPath("$.email", Matchers.is(response.getEmail()))
                );

        verify(service).createUser(request);
        verifyNoMoreInteractions(service);
    }

    @Test
    @SneakyThrows
    void updateUser() {
        UUID requestUUID = UUID.randomUUID();
        User requestUser = TestUtils.buildUser();
        User responseUser = requestUser;
        responseUser.setId(requestUUID);

        when(service.updateUser(any(), any())).thenReturn(responseUser);

        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/" + requestUUID)
                        .param("id", requestUUID.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.jsonStringify(requestUser)))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.name", Matchers.is(responseUser.getName())),
                        jsonPath("$.email", Matchers.is(responseUser.getEmail()))
                );

        verify(service).updateUser(requestUUID, requestUser);
        verifyNoMoreInteractions(service);
    }

    @Test
    @SneakyThrows
    void deleteUser() {
        UUID requestUUID = UUID.randomUUID();

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/" + requestUUID)
                        .param("id", requestUUID.toString()))
                .andExpect(status().isOk());

        verify(service).deleteUser(requestUUID);
        verifyNoMoreInteractions(service);
    }
}