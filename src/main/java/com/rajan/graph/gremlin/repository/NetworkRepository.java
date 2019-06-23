package com.rajan.graph.gremlin.repository;

import com.rajan.graph.gremlin.model.network.Network;
import com.microsoft.spring.data.gremlin.repository.GremlinRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetworkRepository extends GremlinRepository<Network, String> {
}
