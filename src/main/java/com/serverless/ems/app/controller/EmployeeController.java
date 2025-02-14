package com.serverless.ems.app.controller;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.ems.app.config.DynamoDBConfig;
import com.serverless.ems.app.model.Employee;
import com.serverless.ems.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeController implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Autowired
    EmployeeService employeeService;

    public EmployeeController() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(client);
        this.employeeService = new EmployeeService(dynamoDBMapper);
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request,Context context) {

        // Check if request body is null or empty
        if (request.getBody() == null || request.getBody().trim().isEmpty()) {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody("Error: Request body is missing or empty");
        }
        try {
            Employee employee = new ObjectMapper().readValue(request.getBody(), Employee.class);
            Employee employee1 = employeeService.createEmployee(employee);
            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(new ObjectMapper().writeValueAsString(employee1));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
