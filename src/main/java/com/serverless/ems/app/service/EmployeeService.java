package com.serverless.ems.app.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.serverless.ems.app.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {


    private final DynamoDBMapper dynamoDBMapper;

    public EmployeeService(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Employee createEmployee(Employee employee){
        dynamoDBMapper.save(employee);
        return employee;
    }

    public Employee getEmployee(int id){
        return dynamoDBMapper.load(Employee.class, id, dynamoDBMapper);
    }
}
