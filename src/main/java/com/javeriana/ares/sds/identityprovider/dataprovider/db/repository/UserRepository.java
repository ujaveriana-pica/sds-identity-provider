package com.javeriana.ares.sds.identityprovider.dataprovider.db.repository;

import com.javeriana.ares.sds.identityprovider.dataprovider.db.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, String> {

    Mono<User> getByUsername(String username);

}
