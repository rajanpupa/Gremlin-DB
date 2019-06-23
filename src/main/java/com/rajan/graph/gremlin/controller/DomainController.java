package com.rajan.graph.gremlin.controller;

import com.rajan.graph.gremlin.model.ApplicationDetail;
import com.rajan.graph.gremlin.model.DomainInstance;
import com.rajan.graph.gremlin.service.OTAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/domain")
public class DomainController {
    @Autowired
    OTAService otaService;

    @GetMapping("/all")
    public List<DomainInstance> getAllDomains(){
        return otaService.getAllDomains();
    }

    @PostMapping("/app/dependencies")
    public List<ApplicationDetail> getAppDependencies(@RequestBody DomainInstance domainInstance){
        return otaService.getAppDependencies(domainInstance);
    }
}
