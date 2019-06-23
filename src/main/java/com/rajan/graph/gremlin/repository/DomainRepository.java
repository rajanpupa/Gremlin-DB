package com.rajan.graph.gremlin.repository;

import com.rajan.graph.gremlin.model.DomainInstance;
import com.microsoft.spring.data.gremlin.repository.GremlinRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository extends GremlinRepository<DomainInstance, String> {
}
