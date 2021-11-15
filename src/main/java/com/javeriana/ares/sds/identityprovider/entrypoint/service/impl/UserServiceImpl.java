package com.javeriana.ares.sds.identityprovider.entrypoint.service.impl;

import com.javeriana.ares.sds.identityprovider.dataprovider.db.repository.UserRepository;
import com.javeriana.ares.sds.identityprovider.entrypoint.service.UserService;
import com.javeriana.ares.sds.identityprovider.model.domain.UserDO;
import com.javeriana.ares.sds.identityprovider.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getByUsername(username).map(
                u -> new org.springframework.security.core.userdetails.User
                        (u.getUsername(), u.getPassword(), Collections.emptyList())
        ).switchIfEmpty(
                Mono.just(new org.springframework.security.core.userdetails.User
                        (username, "null", Collections.emptyList()))
        ).block();
    }

    @Override
    public Mono<UserDO> saveUser(UserDO userDO) {
        return userRepository.save(
                UserMapper.MAPPER.mapSave(userDO, passwordEncoder)
        ).map(UserMapper.MAPPER::map);
    }

    @Override
    public Mono<UserDO> getUserByUsername(String username) {
        return userRepository.getByUsername(username)
                .map(UserMapper.MAPPER::map);
    }

    @Override
    public Mono<UserDO> updateUser(UserDO userDO) {
        return userRepository.findById(userDO.getUserId())
                .map(u -> UserMapper.MAPPER.mapUpdate(u, userDO))
                .map(UserMapper.MAPPER::map);
    }

}
