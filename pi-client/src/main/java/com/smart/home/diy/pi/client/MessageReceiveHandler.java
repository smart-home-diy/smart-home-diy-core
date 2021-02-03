package com.smart.home.diy.pi.client;

import com.smart.home.diy.common.payload.model.Message;

public interface MessageReceiveHandler {
  void handleReceiveMessage(Message message);
}
