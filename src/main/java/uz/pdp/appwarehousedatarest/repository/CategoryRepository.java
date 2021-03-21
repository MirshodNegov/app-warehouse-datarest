package uz.pdp.appwarehousedatarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appwarehousedatarest.entity.Category;
import uz.pdp.appwarehousedatarest.projection.CustomCategory;

@RepositoryRestResource(path = "category",collectionResourceRel = "CategoryList",excerptProjection = CustomCategory.class)
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
