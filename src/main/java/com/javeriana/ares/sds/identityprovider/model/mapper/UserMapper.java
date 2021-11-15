package com.javeriana.ares.sds.identityprovider.model.mapper;

import com.javeriana.ares.sds.identityprovider.dataprovider.db.entity.User;
import com.javeriana.ares.sds.identityprovider.model.domain.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;
import java.util.UUID;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    UserDO map(User user);

    User map(UserDO userDO);

    default User mapSave(UserDO userDO, PasswordEncoder passwordEncoder) {
        User user = map(userDO);
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(userDO.getPassword()));
        user.setAsNew();
        return user;
    }

    default User mapUpdate(User user, UserDO userDO) {
        user.setUsername(Objects.nonNull(userDO.getUsername()) ? user.getUsername() : user.getUsername());
        user.setName(Objects.nonNull(userDO.getName()) ? userDO.getName() : user.getName());
        user.setLastName(Objects.nonNull(userDO.getLastName()) ? userDO.getLastName() : user.getLastName());
        user.setEmail(Objects.nonNull(userDO.getEmail()) ? userDO.getEmail() : user.getEmail());
        user.setStatus(Objects.nonNull(userDO.getStatus()) ? userDO.getStatus() : user.getStatus());
        return user;
    }
}
