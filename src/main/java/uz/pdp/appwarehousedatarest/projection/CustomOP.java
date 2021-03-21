package uz.pdp.appwarehousedatarest.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appwarehousedatarest.entity.Output;
import uz.pdp.appwarehousedatarest.entity.OutputProduct;
import uz.pdp.appwarehousedatarest.entity.Product;

@Projection(types = OutputProduct.class)
public interface CustomOP {
    Integer getId();
    Product getProduct();
    Double getAmount();
    Double getPrice();
    Output getOutput();
}
