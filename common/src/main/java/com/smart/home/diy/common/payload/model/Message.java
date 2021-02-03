package com.smart.home.diy.common.payload.model;

public class Message {

  private String action;
  private int statusCode;
  private RequestContext requestContext;
  private Payload payload;

  public Message() {}

  public Message(String action, int statusCode, RequestContext requestContext, Payload payload) {
    this.action = action;
    this.statusCode = statusCode;
    this.requestContext = requestContext;
    this.payload = payload;
  }

  public String getAction() {
    return action;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public RequestContext getRequestContext() {
    return requestContext;
  }

  public Payload getPayload() {
    return payload;
  }
}
