package learn.wireless_guardian.domain;

import learn.wireless_guardian.data.BusinessRepository;
import learn.wireless_guardian.data.ResellerOrgRepository;
import learn.wireless_guardian.models.Business;
import learn.wireless_guardian.models.ResellerOrg;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResellerOrgService {

    private final ResellerOrgRepository resellerOrgRepository;

    public ResellerOrgService(ResellerOrgRepository resellerOrgRepository) {
        this.resellerOrgRepository = resellerOrgRepository;
    }

    public List<ResellerOrg> findAll(){
        return resellerOrgRepository.findAll();
    }

    public ResellerOrg findById(int resellerOrgId){
        return  resellerOrgRepository.findById(resellerOrgId);
    }

    public Result<ResellerOrg> add(ResellerOrg resellerOrg){
        Result<ResellerOrg> result = validate(resellerOrg);
        if(!result.isSuccess()){
            return result;
        }

        if(resellerOrg.getResellerOrgId() != 0){
            result.addMessage("resellerId cannot be set for `add` operation", ResultType.INVALID);
        }

        resellerOrg = resellerOrgRepository.add(resellerOrg);
        result.setPayload(resellerOrg);
        return result;
    }

    public Result<ResellerOrg> update(ResellerOrg resellerOrg){
        Result<ResellerOrg> result = validate(resellerOrg);
        if(!result.isSuccess()){
            return result;
        }

        if(resellerOrg.getResellerOrgId() <= 0){
            result.addMessage("resellerId must be set for `update` operation", ResultType.INVALID);
        }

        if(!resellerOrgRepository.update(resellerOrg)){
            String msg = String.format("resellerId #%s not found", resellerOrg.getResellerOrgId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int resellerOrgId){
        return resellerOrgRepository.deleteById(resellerOrgId);
    }

    private Result<ResellerOrg> validate(ResellerOrg resellerOrg) {
        Result<ResellerOrg> result = new Result<>();
        if (resellerOrg == null) {
            result.addMessage("reseller organization cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(resellerOrg.getName())) {
            result.addMessage("reseller organization name is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(resellerOrg.getSalesReps().toString())) {
            result.addMessage("sales reps are required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(resellerOrg.getSites().toString())) {
            result.addMessage("at least one site is required", ResultType.INVALID);
        }

        return result;
    }
}
