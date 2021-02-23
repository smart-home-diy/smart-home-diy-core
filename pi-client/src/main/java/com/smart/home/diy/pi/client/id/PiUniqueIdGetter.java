package com.smart.home.diy.pi.client.id;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PiUniqueIdGetter {

  protected static final String CPU_INFO_FILE_PATH_VARIABLE = "CPU_INFO_FILE_PATH";
  private static final String SERIAL = "Serial";
  private static final String WHITE_SPACE_REGEX = "\\s+";

  public String getUniqueId() {
    try {
      return getSerialNumberIfPi();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private String getSerialNumberIfPi() throws IOException {
    return Files.lines(getCpuInfoFilePath())
        .filter(this::isSerialLine)
        .map(this::getValueFromPiCpuInfo)
        .findFirst()
        .orElseThrow();
  }

  private Path getCpuInfoFilePath() {
    return Path.of(System.getenv(CPU_INFO_FILE_PATH_VARIABLE));
  }

  private boolean isSerialLine(String line) {
    return line.contains(SERIAL);
  }

  private String getValueFromPiCpuInfo(String infoLine) {
    String[] info = infoLine.split(WHITE_SPACE_REGEX);
    return info[info.length - 1];
  }
}
