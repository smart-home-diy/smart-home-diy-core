import com.smart.home.diy.common.ClassForTesting;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestWithAssertJ {

  @Test
  void testAssertJ() {
    ClassForTesting classForTesting = new ClassForTesting();
    assertThat(classForTesting.returnTrue()).as("is not true")
        .isTrue();
  }
}
