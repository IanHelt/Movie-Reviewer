package io.ian.moviereviewer.repositories;

import io.ian.moviereviewer.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{
}
