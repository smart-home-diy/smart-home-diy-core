package com.smart.home.diy.pi.client;

import com.smart.home.diy.common.payload.model.Message;
import com.smart.home.diy.common.payload.model.Payload;
import com.smart.home.diy.common.payload.model.RequestContext;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class AppRunner {

  public static void main(String[] args) {
    try {
      // open websocket
      PiWebSocketClient client = new PiWebSocketClient(new URI("wss://7krblbehs9.execute-api.us-east-1.amazonaws.com/dev/"));


      // add listener
//    client.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
//      public void handleMessage(String message) {
//        System.out.println(message);
//      }
//    });

      // send message to websocket
      //client.sendMessage("{\"action\": \"sendMessage\", \"otherData\": \"otherValue\"}");

      readSystemInput(client);

      Thread.sleep(60000);

    } catch (InterruptedException ex) {
      System.err.println("InterruptedException exception: " + ex.getMessage());
    } catch (URISyntaxException ex) {
      System.err.println("URISyntaxException exception: " + ex.getMessage());
    }
  }

  private static void readSystemInput(PiWebSocketClient client) {
    try (Scanner scanner = new Scanner(System.in)) {
      while (scanner.hasNext()) {
        String text = scanner.next();
        System.out.println(text);
        Message message = createMessage(text);
        client.sendMessage(message);
      }
    }
  }

  private static Message createMessage(String text) {
    Payload payload = new Payload(text);
    RequestContext requestContext = new RequestContext(null, null);
    return new Message("sendMessage", 200, requestContext, payload);
  }
}
