package com.rajan.graph.gremlin.model.relation;

import com.rajan.graph.gremlin.model.ApplicationDetail;
import com.microsoft.spring.data.gremlin.annotation.Edge;
import com.microsoft.spring.data.gremlin.annotation.EdgeFrom;
import com.microsoft.spring.data.gremlin.annotation.EdgeTo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Edge
@AllArgsConstructor
@NoArgsConstructor
public class AppAppRelation {

    @Id
    private String id;

    private String name;

    @EdgeFrom
    private ApplicationDetail appFrom;

    @EdgeTo
    private ApplicationDetail appTo;
}
