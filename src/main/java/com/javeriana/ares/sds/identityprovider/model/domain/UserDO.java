package com.javeriana.ares.sds.identityprovider.model.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDO {

    @Setter
    private String userId;
    private String username;
    private String name;
    private String lastName;
    private String password;
    private String email;
    private String status;
    private String rol;

}
