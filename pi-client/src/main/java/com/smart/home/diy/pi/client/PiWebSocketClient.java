package com.smart.home.diy.pi.client;

import com.smart.home.diy.common.payload.MessageMapper;
import com.smart.home.diy.common.payload.model.Message;

import javax.websocket.*;
import java.net.URI;

@ClientEndpoint
public class PiWebSocketClient {

  private static final MessageMapper MESSAGE_MAPPER = new MessageMapper();
  private Session currentSession;
  private MessageReceiveHandler messageReceiveHandler;

  public PiWebSocketClient(URI endpointURI) {
    try {
      WebSocketContainer container = ContainerProvider.getWebSocketContainer();
      container.connectToServer(this, endpointURI);
    } catch (Exception e) {
      System.out.println("error occurred in setting up websocket: " + e.getMessage());
      throw new RuntimeException(e);
    }
  }

  @OnOpen
  public void onOpen(Session session) {
    System.out.println("connection open");
    currentSession = session;
  }

  @OnClose
  public void onClose(Session session) {
    System.out.println("connection closed");
    currentSession = null;
  }

  @OnMessage
  public void receiveMessage(String messageText) {
    System.out.println("message received: [" + messageText + "]");
    Message message = MESSAGE_MAPPER.mapToMessage(messageText);
  //  messageReceiveHandler.handleReceiveMessage(message);
  }

  public void sendMessage(Message message) {
    String messageText = MESSAGE_MAPPER.mapToString(message);
    System.out.println("sending message: [" + messageText + "]");
    this.currentSession.getAsyncRemote().sendText(messageText);
  }

  @OnError
  public void onError(Throwable throwable) {
    System.err.println("error occurred: [" + throwable.getMessage() + "]");
  }
}
