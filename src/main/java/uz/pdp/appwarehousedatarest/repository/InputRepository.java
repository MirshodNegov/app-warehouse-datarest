package uz.pdp.appwarehousedatarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appwarehousedatarest.entity.Input;
import uz.pdp.appwarehousedatarest.projection.CustomInput;

@RepositoryRestResource(path = "input",collectionResourceRel = "InputList",excerptProjection = CustomInput.class)
public interface InputRepository extends JpaRepository<Input, Integer> {
}
