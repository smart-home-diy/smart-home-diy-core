import com.smart.home.diy.common.ClassForTesting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestClass {

  @Test
  void testClass() {
    ClassForTesting classForTesting = new ClassForTesting();
    Assertions.assertTrue(classForTesting.returnTrue());
    System.out.println("hello");
  }
}
