package com.rajan.graph.gremlin;

import com.microsoft.spring.data.gremlin.common.GremlinFactory;
import com.rajan.graph.gremlin.model.ApplicationDetail;
import com.rajan.graph.gremlin.model.DomainInstance;
import com.rajan.graph.gremlin.model.network.Network;
import com.rajan.graph.gremlin.model.relation.AppAppRelation;
import com.rajan.graph.gremlin.model.relation.AppDomainRelation;
import com.rajan.graph.gremlin.model.relation.DomainAppRelation;
import com.rajan.graph.gremlin.model.relation.DomainDomainRelation;
import com.rajan.graph.gremlin.repository.ApplicationRepository;
import com.rajan.graph.gremlin.repository.DomainRepository;
import com.rajan.graph.gremlin.repository.NetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class GremlinSpikeApplication //implements CommandLineRunner
{
	public static void main(String[] args) {
		SpringApplication.run(GremlinSpikeApplication.class, args);
	}

	@Autowired
	DomainRepository domainRepository;
	@Autowired
	ApplicationRepository applicationRepository;
	@Autowired
	NetworkRepository networkRepository;

	@Autowired
	private GremlinFactory factory;

	private final DomainInstance d1 = new DomainInstance("01", "Domain1", "1.1");
	private final DomainInstance d2 = new DomainInstance("02", "Domain1", "1.3");
	private final DomainInstance d3 = new DomainInstance("03", "Domain2", "1.5");
	private final DomainInstance d4 = new DomainInstance("04", "Domain2", "1.7");

	private final ApplicationDetail a1 = new ApplicationDetail("011", "Application1", "1.0");
	private final ApplicationDetail a2 = new ApplicationDetail("022", "Application1", "1.1");
	private final ApplicationDetail a3 = new ApplicationDetail("033", "Application2", "1.0");
	private final ApplicationDetail a4 = new ApplicationDetail("044", "Application2", "1.1");

	// Define relationship classes to define the relationships between some of the persons.
	private final DomainDomainRelation d1d3Relation = new DomainDomainRelation("0103", "domains",d1, d3);

	private final DomainAppRelation d1a1Relation = new DomainAppRelation("01011", "domainapps", d1, a1);
	private final DomainAppRelation d1a2Relation = new DomainAppRelation("01022", "domainapps", d1, a2);

	private final AppAppRelation a3a4Relation = new AppAppRelation("033044", "appapps", a3, a4);

	private final AppDomainRelation a4d4Relation = new AppDomainRelation("04404", "appdomains",a4, d4);

	private final Network network = new Network();


	//@Override
	public void run(String... args) throws Exception {
		this.networkRepository.deleteAll();

		this.network.getEdges().add(this.d1d3Relation);
		this.network.getEdges().add(this.d1a2Relation);
		this.network.getEdges().add(this.a3a4Relation);
		this.network.getEdges().add(this.d1a1Relation);
		this.network.getEdges().add(this.a4d4Relation);

		this.network.getVertexes().add(d1);
		this.network.getVertexes().add(d2);
		this.network.getVertexes().add(d3);
		this.network.getVertexes().add(d4);

		this.network.getVertexes().add(a1);
		this.network.getVertexes().add(a2);
		this.network.getVertexes().add(a3);
		this.network.getVertexes().add(a4);

		this.networkRepository.save(this.network);
	}
}
