package com.smart.home.diy.backend.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.smart.home.diy.common.payload.MessageMapper;
import com.smart.home.diy.common.payload.model.Message;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class Handler implements RequestStreamHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(Handler.class);

  @Override
  public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {

    String inputText = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8)).lines()
        .collect(Collectors.joining());
    System.out.println(inputText);

    MessageMapper messageMapper = new MessageMapper();
    Message message = messageMapper.mapToMessage(inputText);
    LOGGER.debug("input: [" + inputText + "]");

    String outputText = messageMapper.mapToString(message);
    output.write(outputText.getBytes(StandardCharsets.UTF_8));
    LOGGER.info("output: [" + outputText + "]");
  }
}
