package info.jorgeyanesdiez.dnilettercalc.service;

import javax.inject.Inject;

/**
 * Implementation of a Spanish DNI letter calculator service.
 */
public class DniLetterCalculator implements IControlCharactersCalculator
{

  /**
   * The control characters in the required order.
   */
  protected static final String controlChars = "TRWAGMYFPDXBNJZSQVHLCKE";


  /**
   * Main constructor.
   * @param validationService The service to use to validate the data.
   */
  @Inject
  public DniLetterCalculator(IValidator validationService)
  {
    super();
    this.validationService = validationService;
  }


  /** The service to use to validate the data. */
  protected IValidator validationService = null;


  /**
   * @throws IllegalArgumentException When the input is invalid.
   */
  @Override
  public String getControlCharacters(String input)
  {
    if (validationService.validate(input) != Validity.Valid) { throw new IllegalArgumentException(); }
    int numericPart = Integer.parseInt(input);
    int controlCharIndex = numericPart % controlChars.length();
    String controlChar = controlChars.substring(controlCharIndex, controlCharIndex + 1);
    return controlChar;
  }

}
