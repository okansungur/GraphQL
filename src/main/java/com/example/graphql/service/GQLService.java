package com.example.graphql.service;

import com.example.graphql.FetcherData.TheDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Service
public class GQLService {
    private GraphQL graphQL;
    @Value("classpath:students.graphql")
    Resource graphResource;
    TheDataFetcher theDataFetcher;

    @Autowired
    public GQLService(TheDataFetcher theDataFetcher) {
        this.theDataFetcher = theDataFetcher;
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("allStudents", theDataFetcher.getStudentDataFetcher()))
                .type(newTypeWiring("Query")
                        .dataFetcher("student", theDataFetcher.getStudentByIdDataFetcher()))
                .type(newTypeWiring("Student")
                        .dataFetcher("lecture", theDataFetcher.getLectureDataFetcher()))
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }

    @PostConstruct
    private void getSchema() throws IOException {
        File file = graphResource.getFile();
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(file);
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

}