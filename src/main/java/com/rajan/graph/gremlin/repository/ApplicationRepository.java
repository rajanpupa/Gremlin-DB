package com.rajan.graph.gremlin.repository;

import com.rajan.graph.gremlin.model.ApplicationDetail;
import com.microsoft.spring.data.gremlin.repository.GremlinRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends GremlinRepository<ApplicationDetail, String> {
}
