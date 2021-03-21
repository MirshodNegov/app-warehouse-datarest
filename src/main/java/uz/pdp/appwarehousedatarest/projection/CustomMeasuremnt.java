package uz.pdp.appwarehousedatarest.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appwarehousedatarest.entity.Measurement;

@Projection(types = Measurement.class)
public interface CustomMeasuremnt {
    Integer getId();
    String getName();
    boolean getActive();
}
