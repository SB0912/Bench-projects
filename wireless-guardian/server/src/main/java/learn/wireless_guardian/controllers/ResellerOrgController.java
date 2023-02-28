package learn.wireless_guardian.controllers;

import learn.wireless_guardian.domain.ResellerOrgService;
import learn.wireless_guardian.domain.Result;
import learn.wireless_guardian.models.Business;
import learn.wireless_guardian.models.ResellerOrg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/resellerOrg")
public class ResellerOrgController {

    private final ResellerOrgService resellerOrgService;

    public ResellerOrgController(ResellerOrgService resellerOrgService) {
        this.resellerOrgService = resellerOrgService;
    }

    @GetMapping
    public List<ResellerOrg> findAll(){
        return resellerOrgService.findAll();
    }

    @GetMapping("/{resellerOrgId}")
    public ResponseEntity<ResellerOrg> findById(@PathVariable int resellerOrgId){
        ResellerOrg resellerOrg = resellerOrgService.findById(resellerOrgId);
        if(resellerOrg == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(resellerOrg);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody ResellerOrg resellerOrg) {
        Result<ResellerOrg> result = resellerOrgService.add(resellerOrg);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{resellerOrgId}")
    public ResponseEntity<Object> update(@PathVariable int resellerOrgId, @RequestBody ResellerOrg resellerOrg) {
        if (resellerOrgId != resellerOrg.getResellerOrgId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<ResellerOrg> result = resellerOrgService.update(resellerOrg);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{resellerOrgId}")
    public ResponseEntity<Void> deleteById(@PathVariable int resellerOrgId) {
        if (resellerOrgService.deleteById(resellerOrgId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
