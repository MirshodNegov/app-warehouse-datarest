package uz.pdp.appwarehousedatarest.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appwarehousedatarest.entity.User;
import uz.pdp.appwarehousedatarest.entity.Warehouse;

import java.util.Set;

@Projection(types = User.class)
public interface CustomUser {
    Integer getId();
    String getFirstName();
    String getLastName();
    String getPhoneNumber();
    String getCode();
    String getPassword();
    String getActive();
    Set<Warehouse> getWarehouses();
}
