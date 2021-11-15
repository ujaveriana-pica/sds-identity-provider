package com.javeriana.ares.sds.identityprovider.dataprovider.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("user")
public class User implements Persistable<String> {

    @Id
    @Column("user_id")
    private String userId;

    @Column("username")
    private String username;

    @Column("name")
    private String name;

    @Column("last_name")
    private String lastName;

    @Column("password")
    private String password;

    @Column("email")
    private String email;

    @Column("status")
    private String status;

    @Column("rol")
    private String rol;

    @Transient
    private boolean newUser;

    @Override
    public String getId() {
        return userId;
    }

    @Override
    @Transient
    public boolean isNew() {
        return this.newUser || getId() == null;
    }

    public User setAsNew() {
        this.newUser = true;
        return this;
    }

}
