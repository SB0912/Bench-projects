package learn.wireless_guardian.controllers;

import learn.wireless_guardian.domain.Result;
import learn.wireless_guardian.domain.SalesRepService;
import learn.wireless_guardian.models.Business;
import learn.wireless_guardian.models.SalesRep;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/salesRep")
public class SalesRepController {

    private final SalesRepService salesRepService;

    public SalesRepController(SalesRepService salesRepService) {
        this.salesRepService = salesRepService;
    }

    @GetMapping
    public List<SalesRep> findAll() {
        return salesRepService.findAll();
    }

    @GetMapping("/{salesRepId}")
    public ResponseEntity<SalesRep> findById(@PathVariable int salesRepId) {
        SalesRep salesRep = salesRepService.findById(salesRepId);
        if (salesRep == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(salesRep);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody SalesRep salesRep) {
        Result<SalesRep> result = salesRepService.add(salesRep);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{salesRepId}")
    public ResponseEntity<Object> update(@PathVariable int salesRepId, @RequestBody SalesRep salesRep) {
        if (salesRepId != salesRep.getSalesRepId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<SalesRep> result = salesRepService.update(salesRep);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{salesRepId}")
    public ResponseEntity<Void> deleteById(@PathVariable int salesRepId) {
        if (salesRepService.deleteById(salesRepId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

