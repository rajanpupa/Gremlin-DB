package com.rajan.graph.gremlin.service;

import com.microsoft.spring.data.gremlin.common.GremlinFactory;
import com.microsoft.spring.data.gremlin.query.GremlinOperations;
import com.microsoft.spring.data.gremlin.query.GremlinTemplate;
import com.rajan.graph.gremlin.model.ApplicationDetail;
import com.rajan.graph.gremlin.model.DomainInstance;
import com.rajan.graph.gremlin.repository.ApplicationRepository;
import com.rajan.graph.gremlin.repository.DomainAppRelationRepository;
import com.rajan.graph.gremlin.repository.DomainRepository;
import com.rajan.graph.gremlin.repository.NetworkRepository;
import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.apache.tinkerpop.gremlin.driver.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OTAService {
    @Autowired
    DomainRepository domainRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    NetworkRepository networkRepository;

    @Autowired
    DomainAppRelationRepository domainAppRelationRepository;

//    @Autowired
//    Cluster cluster;

    @Autowired
    GremlinTemplate gremlinTemplate;

    @Autowired
    private GremlinFactory factory;

    @Autowired
    GremlinOperations gremlinOperations;

    public List<DomainInstance> getAllDomains(){
        List<DomainInstance> domainInstances = new ArrayList<>();
        Iterable<DomainInstance> iterable =  domainRepository.findAll();

        iterable.forEach(d -> domainInstances.add(d));

        return domainInstances;
    }

    public List<ApplicationDetail> getAppDependencies(DomainInstance domainInstance){
        List<ApplicationDetail> applicationDetails = new ArrayList<>();

        // Criteria c = new Criteria();
        // GremlinQuery query = new GremlinQuery(criteria);
        // gremlinTemplate.find(gremlinQuery, ApplicationDetail.class);

        /*
         * Second way is to get the client and execute the query
         */
        Client client = factory.getGremlinClient();
        StringBuilder sb = new StringBuilder();
        sb.append("g.V()")
                .append(".hasLabel('DomainInstance')")
                .append( String.format(".has('version', eq('%s') )", domainInstance.getVersion()) )
                .append(".outE('DomainAppRelation')")
                .append(".inV()")
        ;

        String query = sb.toString();
        System.out.println("Query == " + query);

        ResultSet results = client.submit(query);
        Iterator<Result> iterator =  results.iterator();

        while(iterator.hasNext()){
            Result result = iterator.next();
            ApplicationDetail applicationDetail = mapToAppDetail(result);
            applicationDetails.add(applicationDetail);
        }

        return applicationDetails;
    }

    private ApplicationDetail mapToAppDetail(Result result){
        LinkedHashMap<String, Object> linkedHashMap = result.get(LinkedHashMap.class);
        LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) linkedHashMap.get("properties");
        List<Map<String, String>> nameMap = (List<Map<String, String>>) properties.get("name");

        return ApplicationDetail.builder()
                .id(linkedHashMap.get("id").toString())
                .name( getPropertyValue((List<Map<String, String>>) properties.get("name")) )
                .version( getPropertyValue((List<Map<String, String>>) properties.get("version")) )
                .build()
                ;

    }

    private String getPropertyValue(List<Map<String, String>> list){
        return list.get(0).get("value");
    }

}
