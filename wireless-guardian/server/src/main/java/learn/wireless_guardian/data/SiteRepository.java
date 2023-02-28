package learn.wireless_guardian.data;

import learn.wireless_guardian.models.Site;

import java.util.List;

public interface SiteRepository {
    List<Site> findAll();

    Site findById(int siteId);

    List<Site> findByBusinessId(int businessId);

    List<Site> findByResellerOrgId(int resellerOrgId);

    Site add (Site site);

    boolean update(Site site);

    boolean deleteById(int siteId);
}
