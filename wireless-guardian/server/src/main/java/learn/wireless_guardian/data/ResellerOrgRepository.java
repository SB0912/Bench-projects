package learn.wireless_guardian.data;

import learn.wireless_guardian.models.ResellerOrg;
import learn.wireless_guardian.models.Site;

import java.util.List;

public interface ResellerOrgRepository {

    List<ResellerOrg> findAll();

    ResellerOrg findById(int resellerOrgId);

    ResellerOrg add (ResellerOrg resellerOrg);

    boolean update(ResellerOrg resellerOrg);

    boolean deleteById(int resellerOrgId);
}
