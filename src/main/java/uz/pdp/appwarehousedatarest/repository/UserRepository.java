package uz.pdp.appwarehousedatarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appwarehousedatarest.entity.User;
import uz.pdp.appwarehousedatarest.projection.CustomUser;

import java.util.Optional;

@RepositoryRestResource(path = "user",collectionResourceRel = "UsersList",excerptProjection = CustomUser.class)
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByPhoneNumber(String phoneNumber);
}
