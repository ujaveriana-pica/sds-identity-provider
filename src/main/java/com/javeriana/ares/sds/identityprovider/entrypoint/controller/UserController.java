package com.javeriana.ares.sds.identityprovider.entrypoint.controller;

import com.javeriana.ares.sds.identityprovider.crosscutting.constants.Constants;
import com.javeriana.ares.sds.identityprovider.crosscutting.constants.ResourceEndpoint;
import com.javeriana.ares.sds.identityprovider.entrypoint.service.UserService;
import com.javeriana.ares.sds.identityprovider.model.domain.UserDO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ResourceEndpoint.USER_RESOURCE)
@RequiredArgsConstructor
@CrossOrigin(ResourceEndpoint.ALL_ORIGIN)
public class UserController {

    private final UserService userService;

    @PostMapping(value = ResourceEndpoint.SIGN_UP_RESOURCE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<UserDO>> saveUser(@RequestBody UserDO userDO) {
        return userService.saveUser(userDO).map(ResponseEntity::ok);
    }

    @PatchMapping(value = ResourceEndpoint.UPDATE_USER_RESOURCE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<UserDO>> updateUser(@PathVariable(value = Constants.USER_ID) String userId, @RequestBody UserDO userDO) {
        userDO.setUserId(userId);
        return userService.updateUser(userDO).map(ResponseEntity::ok);
    }


    @GetMapping(value = ResourceEndpoint.GET_USER_RESOURCE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<UserDO>> getUserByUsername(@PathVariable(value = Constants.USERNAME) String username) {
        return userService.getUserByUsername(username).map(ResponseEntity::ok);
    }
}
