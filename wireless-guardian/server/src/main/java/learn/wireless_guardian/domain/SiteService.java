package learn.wireless_guardian.domain;

import learn.wireless_guardian.data.SiteRepository;
import learn.wireless_guardian.models.Business;
import learn.wireless_guardian.models.SalesRep;
import learn.wireless_guardian.models.Site;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteService {

    private final SiteRepository siteRepository;

    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public List<Site> findAll() {
        return siteRepository.findAll();
    }

    public Site findById(int siteId) {
        return siteRepository.findById(siteId);
    }

    public List<Site> findByBusinessId(int businessId) {
        return siteRepository.findByBusinessId(businessId);
    }

    public List<Site> findByResellerOrgId(int resellerOrgId) {
        return siteRepository.findByResellerOrgId(resellerOrgId);
    }

    public Result<Site> add(Site site) {
        Result<Site> result = validate(site);
        if (!result.isSuccess()) {
            return result;
        }

        if (site.getBusinessId() == 0) {
            result.addMessage("site cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        if (site.getResellerOrgId() == 0) {
            result.addMessage("site cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        site = siteRepository.add(site);
        result.setPayload(site);
        return result;
    }

    public Result<Site> update(Site site) {
        Result<Site> result = validate(site);
        if (!result.isSuccess()) {
            return result;
        }

        if (site.getSiteId() <= 0) {
            result.addMessage("siteId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!siteRepository.update(site)) {
            String msg = String.format("siteId: %s, not found", site.getSiteId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int siteId) {
        return siteRepository.deleteById(siteId);
    }

    private Result<Site> validate(Site site) {
        Result<Site> result = new Result<>();

        if (site == null) {
            result.addMessage("site cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(site.getBusinessId())) {
            result.addMessage("business is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(site.getLatitude())) {
            result.addMessage("latitude is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(site.getLongitude())) {
            result.addMessage("longitude is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(site.getServicesSold())) {
            result.addMessage("service sold is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(site.isMultipleSites())) {
            result.addMessage("multiple site definition is required", ResultType.INVALID);
        }

        return result;
    }
}
