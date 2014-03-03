package info.jorgeyanesdiez.dnilettercalc.service.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import info.jorgeyanesdiez.dnilettercalc.service.DniLetterCalculator;
import info.jorgeyanesdiez.dnilettercalc.service.DniNumericPartValidator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DniLetterCalculatorTest
{

  private DniLetterCalculator sut;


  @BeforeTest
  public void beforeTest()
  {
    sut = new DniLetterCalculator(new DniNumericPartValidator());
  }


  @Test(expectedExceptions = { IllegalArgumentException.class })
  public void getControlCharacters_WithInvalidInput_ThrowsException()
  {
    sut.getControlCharacters("123");
  }


  @Test()
  public void getControlCharacters_WithValidInput_ReturnsExpected()
  {
    assertThat(sut.getControlCharacters("12345678"), is("Z"));
  }

}
