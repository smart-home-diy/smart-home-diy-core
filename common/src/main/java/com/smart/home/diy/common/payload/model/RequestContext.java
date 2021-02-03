package com.smart.home.diy.common.payload.model;

public class RequestContext {

  private String connectionId;
  private String routeKey;

  public RequestContext() {}

  public RequestContext(String connectionId, String routeKey) {
    this.connectionId = connectionId;
    this.routeKey = routeKey;
  }

  public String getConnectionId() {
    return connectionId;
  }

  public String getRouteKey() {
    return routeKey;
  }
}
