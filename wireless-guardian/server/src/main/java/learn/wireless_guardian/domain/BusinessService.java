package learn.wireless_guardian.domain;

import learn.wireless_guardian.data.BusinessRepository;
import learn.wireless_guardian.models.Business;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {

    private final BusinessRepository businessRepository;

    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    public List<Business> findAll() {
        return businessRepository.findAll();
    }

    public Business findById(int businessId) {
        return businessRepository.findById(businessId);
    }

    public Result<Business> add(Business business) {
        Result<Business> result = validate(business);
        if (!result.isSuccess()) {
            return result;
        }

        if (business.getBusinessId() != 0) {
            result.addMessage("businessId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        business = businessRepository.add(business);
        result.setPayload(business);
        return result;
    }

    public Result<Business> update(Business business) {
        Result<Business> result = validate(business);
        if (!result.isSuccess()) {
            return result;
        }

        if (business.getBusinessId() <= 0) {
            result.addMessage("businessId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!businessRepository.update(business)) {
            String msg = String.format("businessId: %s, not found", business.getBusinessId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int businessId) {
        return businessRepository.deleteById(businessId);
    }

    private Result<Business> validate(Business business) {
        Result<Business> result = new Result<>();
        if (business == null) {
            result.addMessage("business cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(business.getBusinessName())) {
            result.addMessage("business name is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(business.getAddress())) {
            result.addMessage("address is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(business.getCity())) {
            result.addMessage("city is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(business.getZipCode())) {
            result.addMessage("zipcode is required", ResultType.INVALID);
        }

        return result;
    }
}
