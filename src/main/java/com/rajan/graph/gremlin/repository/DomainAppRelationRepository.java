package com.rajan.graph.gremlin.repository;

import com.microsoft.spring.data.gremlin.repository.GremlinRepository;
import com.rajan.graph.gremlin.model.relation.DomainAppRelation;

public interface DomainAppRelationRepository extends GremlinRepository<DomainAppRelation, String> {
    
}
