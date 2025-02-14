package com.serverless.ems.app.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "employee_table")
public class Employee {

    @DynamoDBHashKey(attributeName = "emp_id")
    private String emp_id;

    @DynamoDBAttribute(attributeName = "emp_name")
    private String emp_name;

    @DynamoDBAttribute(attributeName = "emp_role")
    private String emp_role;

}
