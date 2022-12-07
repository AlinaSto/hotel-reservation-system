package spring.bookingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.bookingApp.model.Role;
import spring.bookingApp.model.RoleType;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleType(RoleType roleType);

}