package com.smart.home.diy.common.payload;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.home.diy.common.payload.model.Payload;
import org.junit.jupiter.api.Test;


class MessageMapperTest {

  @Test
  void test() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    Payload payload = new Payload("hello");

    mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    System.out.println(mapper.writeValueAsString(payload));
  }
}