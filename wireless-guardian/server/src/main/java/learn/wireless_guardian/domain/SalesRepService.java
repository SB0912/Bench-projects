package learn.wireless_guardian.domain;

import learn.wireless_guardian.data.ResellerOrgRepository;
import learn.wireless_guardian.data.SalesRepRepository;
import learn.wireless_guardian.models.Business;
import learn.wireless_guardian.models.ResellerOrg;
import learn.wireless_guardian.models.SalesRep;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesRepService{

    private final SalesRepRepository salesRepRepository;

    public SalesRepService(SalesRepRepository salesRepRepository) {
        this.salesRepRepository = salesRepRepository;
    }


    public List<SalesRep> findAll() {
        return salesRepRepository.findAll();
    }


    public SalesRep findById(int salesRepId) {
        return salesRepRepository.findById(salesRepId);
    }


    public Result<SalesRep> add(SalesRep salesRep) {
        Result<SalesRep> result = validate(salesRep);
        if (!result.isSuccess()) {
            return result;
        }

        if (salesRep.getSalesRepId() != 0) {
            result.addMessage("sales rep Id cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        salesRep = salesRepRepository.add(salesRep);
        result.setPayload(salesRep);
        return result;
    }


    public Result<SalesRep> update(SalesRep salesRep) {
        Result<SalesRep> result = validate(salesRep);
        if (!result.isSuccess()) {
            return result;
        }

        if (salesRep.getSalesRepId() <= 0) {
            result.addMessage("salesRepId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!salesRepRepository.update(salesRep)) {
            String msg = String.format("businessId: %s, not found", salesRep.getSalesRepId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }


    public boolean deleteById(int salesRepId) {
        return salesRepRepository.deleteById(salesRepId);
    }

    private Result<SalesRep> validate(SalesRep salesRep) {
        Result<SalesRep> result = new Result<>();

        if (salesRep == null) {
            result.addMessage("sales rep cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(salesRep.getFirstName())) {
            result.addMessage("first name is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(salesRep.getLastName())) {
            result.addMessage("last name is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(salesRep.getCity())) {
            result.addMessage("city is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(salesRep.getState())) {
            result.addMessage("state is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(salesRep.getResellerOrgId())) {
            result.addMessage("reseller org is required", ResultType.INVALID);
        }

        return result;
    }
}
