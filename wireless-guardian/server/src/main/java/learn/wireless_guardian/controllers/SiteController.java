package learn.wireless_guardian.controllers;


import learn.wireless_guardian.domain.Result;
import learn.wireless_guardian.domain.SiteService;
import learn.wireless_guardian.models.Business;
import learn.wireless_guardian.models.SalesRep;
import learn.wireless_guardian.models.Site;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/site")
public class SiteController {

    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @GetMapping
    public List<Site> findAll() {
        return siteService.findAll();
    }

    @GetMapping("/{siteId}")
    public ResponseEntity<Site> findById(@PathVariable int siteId) {
        Site site = siteService.findById(siteId);
        if (site == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(site);
    }

    @GetMapping("/business/{businessId}")
    public ResponseEntity<List<Site>> findByBusinessId(@PathVariable int businessId) {
        List<Site> site = siteService.findByBusinessId(businessId);
        if (site == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(site);
    }

    @GetMapping("/resellerOrg/{resellerOrgId}")
    public ResponseEntity<List<Site>> findByResellerOrgId(@PathVariable int resellerOrgId) {
        List<Site> site = siteService.findByResellerOrgId(resellerOrgId);
        if (site == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(site);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Site site) {
        Result<Site> result = siteService.add(site);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{siteId}")
    public ResponseEntity<Object> update(@PathVariable int siteId, @RequestBody Site site) {
        if (siteId != site.getSiteId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Site> result = siteService.update(site);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{siteId}")
    public ResponseEntity<Void> deleteById(@PathVariable int siteId) {
        if (siteService.deleteById(siteId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
