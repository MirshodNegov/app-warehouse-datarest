package uz.pdp.appwarehousedatarest.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehousedatarest.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}
