package info.jorgeyanesdiez.dnilettercalc.service;

/**
 * Contract for services that validate the input
 * for calculator services in this package.
 */
public interface IValidator
{

  /**
   * Determines the validity of the input.
   * @param input The input to be tested.
   * @return The requested validity.
   */
  public Validity validate(String input);

}
