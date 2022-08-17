package com.yanpgabriel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.RolesValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "tb_role")
public class RoleEntity  extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "role_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "role_seq")
    private Long id;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    public List<UserEntity> users;

    @RolesValue
    @Column
    public String role;
}
