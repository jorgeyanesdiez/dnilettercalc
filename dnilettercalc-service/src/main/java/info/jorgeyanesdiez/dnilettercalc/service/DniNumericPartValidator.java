package info.jorgeyanesdiez.dnilettercalc.service;

/**
 * Implementation of a validator for the numeric part
 * of a Spanish DNI document.
 */
public class DniNumericPartValidator implements IValidator
{

  @Override
  public Validity validate(String input)
  {
    Validity result = Validity.Invalid;
    if(input != null && input.matches("^[0-9]*$"))
    {
      if(input.length() == 8) { result = Validity.Valid; }
      else if(input.length() < 8 ) { result = Validity.Partial; }
    }
    return result;
  }

}
