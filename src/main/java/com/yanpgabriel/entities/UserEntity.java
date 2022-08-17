package com.yanpgabriel.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yanpgabriel.providers.CustomPasswordProvider;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@UserDefinition
@Entity
@Table(name = "tb_user")
public class UserEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "user_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_seq")
    private Long id;

    @Username
    @Column(nullable = false, unique = true)
    private String username;

    @Password(value = PasswordType.CUSTOM, provider = CustomPasswordProvider.class)
    @Column(nullable = false)
    public String password;
    
    @Column
    private String fullname;

    @Column
    private String email;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dtcreation;

    @Roles
    @ManyToMany(cascade = CascadeType.ALL)
    public List<RoleEntity> roles = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.dtcreation = LocalDateTime.now();
    }

}

