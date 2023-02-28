package learn.wireless_guardian.controllers;

import learn.wireless_guardian.domain.BusinessService;
import learn.wireless_guardian.domain.Result;
import learn.wireless_guardian.models.Business;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/business")
public class BusinessController {

    private final BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @GetMapping
    public List<Business> findAll() {
        return businessService.findAll();
    }

    @GetMapping("/{businessId}")
    public ResponseEntity<Business> findById(@PathVariable int businessId) {
        Business business = businessService.findById(businessId);
        if (business == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(business);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Business business) {
        Result<Business> result = businessService.add(business);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{businessId}")
    public ResponseEntity<Object> update(@PathVariable int businessId, @RequestBody Business business) {
        if (businessId != business.getBusinessId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Business> result = businessService.update(business);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{businessId}")
    public ResponseEntity<Void> deleteById(@PathVariable int businessId) {
        if (businessService.deleteById(businessId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
