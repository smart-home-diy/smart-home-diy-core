package com.smart.home.diy.common.payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.home.diy.common.payload.model.Message;

public class MessageMapper {
  private static final ObjectMapper MAPPER = new ObjectMapper();

  public Message mapToMessage(String message) {
    try {
      return MAPPER.readValue(message, Message.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public String mapToString(Message message) {
    try {
      return MAPPER.writeValueAsString(message);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
