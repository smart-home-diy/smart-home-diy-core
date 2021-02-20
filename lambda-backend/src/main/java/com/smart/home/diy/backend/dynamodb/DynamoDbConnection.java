package com.smart.home.diy.backend.dynamodb;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamoDbConnection {

  private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDbConnection.class);

  public DynamoDbConnection() {
    AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

    DynamoDB dynamoDB = new DynamoDB(client);
    Table table = dynamoDB.getTable("PiState");

//    GetItemSpec spec = new GetItemSpec().withPrimaryKey("PiName", "testPi");

    ScanRequest scanRequest = new ScanRequest().withTableName("PiState");
    ScanResult scanResult = client.scan(scanRequest);

    LOGGER.info("getting info");
    System.out.println("Attempting to read the item...");
//    Item outcome = table.getItem(spec);
    System.out.println("GetItem succeeded: ");
    System.out.println(scanResult);

    UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("PiName", "testPi")
        .withUpdateExpression("set Connected = :c")
        .withValueMap(new ValueMap().withBoolean(":c", false))
        .withReturnValues(ReturnValue.UPDATED_NEW);

    UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
    System.out.println(outcome);
  }
}
