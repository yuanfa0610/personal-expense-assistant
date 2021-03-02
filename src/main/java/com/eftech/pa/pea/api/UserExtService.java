package com.eftech.pa.pea.api;

import com.eftech.pa.pea.dto.UserDTO;
import com.eftech.pa.pea.exception.ApiException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface UserExtService {

    /**
     * Retrieve all users
     * @return a list of user DTOs
     */
    @RequestMapping(path = "/all", method = RequestMethod.GET)
    List<UserDTO> getUsers();

    /**
     * Create a new user
     * @param userDTO DTO of the user to create
     * @return DTO of the created user
     * @throws ApiException exception to throw
     */
    @RequestMapping(method = RequestMethod.POST)
    UserDTO createUser(@RequestBody UserDTO userDTO) throws ApiException;

    /**
     * Get user with specified id
     * @param userId id of the target user
     * @return DTO of the requested user
     * @throws ApiException exception to throw
     */
    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    UserDTO getUser(@PathVariable("userId") String userId) throws ApiException;

    /**
     * Update an existing user
     * @param userId id of the target user
     * @param userDTO DTO of the user to update
     * @return DTO of the updated user
     * @throws ApiException exception to throw
     */
    @RequestMapping(path = "/{userId}", method = RequestMethod.PUT)
    UserDTO updateUser(@PathVariable("userId") String userId, @RequestBody UserDTO userDTO) throws ApiException;

    /**
     * Delete user with specified id
     * @param userId id of the target user
     * @throws ApiException exception to throw
     */
    @RequestMapping(path = "/{userId}", method = RequestMethod.DELETE)
    void deleteUser(@PathVariable("userId") String userId) throws ApiException;
}
