package com.rajan.graph.gremlin.model;

import com.microsoft.spring.data.gremlin.annotation.Vertex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Vertex
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationDetail {
    @Id
    private String id;
    private String name;
    private String version;

}
