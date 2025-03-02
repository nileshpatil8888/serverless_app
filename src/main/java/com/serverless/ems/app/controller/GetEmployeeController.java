package com.serverless.ems.app.controller;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.ems.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetEmployeeController implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Autowired
    EmployeeService service;

    ObjectMapper mapper;

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context){
        var A = input.getQueryStringParameters().get("id");
        var employee = service.getEmployee(Integer.parseInt(A));
        try {
            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(mapper.writeValueAsString(employee));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
