package info.jorgeyanesdiez.dnilettercalc.service;

/**
 * Contract for services that calculate control
 * characters that correspond to a given input.
 */
public interface IControlCharactersCalculator
{

  /**
   * Finds the control characters for the given input.
   * @param input The input for which the control characters are needed.
   * @return The control characters.
   */
  public String getControlCharacters(String input);

}
