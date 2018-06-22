package com.shopsuite.examples.aws2.dynamodb;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDBClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesRequest;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

public class DynamoDB {
    public void exampleDynamoDB() {

        // first the complete way to connect.
        DynamoDBClient clientComplete = DynamoDBClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(ProfileCredentialsProvider.builder()
                        .profileName("my-profile")
                        .build())
                .build();

        // a more short version, if the roles and credentials are correctly configure within the instance/machine
        DynamoDBClient client = DynamoDBClient.builder().build();

        //requesting information from AWS, as always will be done via a Request Object
        //DynamoDB is build up form tables, nothing more, tables containing items
        //here we list all table names we defined in our DynamoDB, within our region!
        ListTablesResponse response = client.listTables(ListTablesRequest.builder()
                .limit(5)
                .build());

        response.tableNames().forEach(System.out::println);
    }
}
