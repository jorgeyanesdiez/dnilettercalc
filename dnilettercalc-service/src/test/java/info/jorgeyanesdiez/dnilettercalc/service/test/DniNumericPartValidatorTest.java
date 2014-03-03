package info.jorgeyanesdiez.dnilettercalc.service.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import info.jorgeyanesdiez.dnilettercalc.service.DniNumericPartValidator;
import info.jorgeyanesdiez.dnilettercalc.service.Validity;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DniNumericPartValidatorTest
{

  private DniNumericPartValidator sut;


  @BeforeTest
  public void beforeTest()
  {
    sut = new DniNumericPartValidator();
  }


  @Test
  public void validate_WithNull_ReturnsExpected()
  {
    assertThat(sut.validate(null), is(Validity.Invalid));
  }


  @Test
  public void validate_WithWhitespace_ReturnsExpected()
  {
    assertThat(sut.validate(" "), is(Validity.Invalid));
  }


  @Test
  public void validate_WithNonNumericString_ReturnsExpected()
  {
    assertThat(sut.validate("A"), is(Validity.Invalid));
  }


  @Test
  public void validate_WithEmptyString_ReturnsExpected()
  {
    assertThat(sut.validate(""), is(Validity.Partial));
  }


  @Test
  public void validate_WithIncorrectLengthString_ReturnsExpected()
  {
    assertThat(sut.validate("1"), is(Validity.Partial));
  }


  @Test
  public void validate_WithValidString_ReturnsExpected()
  {
    StringBuffer sb = new StringBuffer();
    for(int i = 0 ; i < 8 ; i++) { sb.append("1"); }
    assertThat(sut.validate(sb.toString()), is(Validity.Valid));
  }

}
