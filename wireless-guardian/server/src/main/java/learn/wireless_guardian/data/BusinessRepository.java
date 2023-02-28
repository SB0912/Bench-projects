package learn.wireless_guardian.data;

import learn.wireless_guardian.models.Business;
import learn.wireless_guardian.models.Site;

import java.util.List;

public interface BusinessRepository {

    List<Business> findAll();

    Business findById(int businessId);

    Business add (Business business);

    boolean update(Business business);

    boolean deleteById(int businessId);
}
