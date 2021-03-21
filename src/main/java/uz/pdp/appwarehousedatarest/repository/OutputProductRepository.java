package uz.pdp.appwarehousedatarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appwarehousedatarest.entity.OutputProduct;
import uz.pdp.appwarehousedatarest.projection.CustomOP;

@RepositoryRestResource(path = "outputProduct",collectionResourceRel = "OutputProductList",excerptProjection = CustomOP.class)
public interface OutputProductRepository extends JpaRepository<OutputProduct, Integer> {
}
