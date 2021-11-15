package com.javeriana.ares.sds.identityprovider.entrypoint.service;

import com.javeriana.ares.sds.identityprovider.model.domain.UserDO;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<UserDO> saveUser(UserDO userDO);
    Mono<UserDO> getUserByUsername(String username);
    Mono<UserDO> updateUser(UserDO userDO);


}
