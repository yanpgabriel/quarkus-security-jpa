package com.yanpgabriel.resources;

import com.yanpgabriel.entities.UserEntity;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

public interface UserResource extends PanacheEntityResource<UserEntity, Long> {
}
