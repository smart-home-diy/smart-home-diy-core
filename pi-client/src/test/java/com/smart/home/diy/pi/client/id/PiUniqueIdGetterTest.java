package com.smart.home.diy.pi.client.id;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import java.io.IOException;

import static com.smart.home.diy.pi.client.id.PiUniqueIdGetter.CPU_INFO_FILE_PATH_VARIABLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SystemStubsExtension.class)
class PiUniqueIdGetterTest {

  @SystemStub
  private final EnvironmentVariables environmentVariables =
      new EnvironmentVariables(CPU_INFO_FILE_PATH_VARIABLE, "src\\test\\resources\\cpuinfo");

  @Test
  void testGetUniquePiId() {
    String expectedPiId = "dev_pi_id";
    String actualPiId = new PiUniqueIdGetter().getUniqueId();

    assertThat(actualPiId)
        .as("actual Pi Id did not equal expected Pi Id")
        .isEqualTo(expectedPiId);
  }

  @Test
  void testCpuInfoFileNotFound() {
    environmentVariables.set(CPU_INFO_FILE_PATH_VARIABLE, "fake-file-path");
    assertThatThrownBy(() -> new PiUniqueIdGetter().getUniqueId())
        .as("IOException not thrown when fake cpuinfo file given")
        .isInstanceOf(RuntimeException.class)
        .hasCauseInstanceOf(IOException.class);
  }
}
