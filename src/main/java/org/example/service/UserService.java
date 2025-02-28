package org.example.service;

import org.example.mapper.UserMapper;
import org.example.model.entity.User;
import org.example.repository.UserRepository;
import org.example.model.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserResource> loadAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserResource findUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElse(null);
    }

    public List<UserResource> findUsersByFlight(Long flightId) {
        return userRepository.findDistinctByBookings_Flight_Id(flightId).stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserResource saveUser(UserResource userResource) {
        User user = userMapper.toEntity(userResource);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
