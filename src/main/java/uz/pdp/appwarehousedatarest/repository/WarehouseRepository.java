package uz.pdp.appwarehousedatarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appwarehousedatarest.entity.Warehouse;
import uz.pdp.appwarehousedatarest.projection.CustomWarehouse;

@RepositoryRestResource(path = "warehouse",collectionResourceRel = "WarehouseList",excerptProjection = CustomWarehouse.class)
public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {
}
