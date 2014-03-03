package info.jorgeyanesdiez.dnilettercalc.user;

import info.jorgeyanesdiez.dnilettercalc.gui.DniLetterCalculatorGui;
import info.jorgeyanesdiez.dnilettercalc.gui.ValidatingDocument;
import info.jorgeyanesdiez.dnilettercalc.service.DniLetterCalculator;
import info.jorgeyanesdiez.dnilettercalc.service.DniNumericPartValidator;
import info.jorgeyanesdiez.dnilettercalc.service.IControlCharactersCalculator;
import info.jorgeyanesdiez.dnilettercalc.service.IValidator;
import com.google.inject.AbstractModule;

public class InjectorConfiguration extends AbstractModule
{

  @Override
  protected void configure()
  {
    bind(DniLetterCalculatorGui.class);
    bind(IControlCharactersCalculator.class).to(DniLetterCalculator.class);
    bind(IValidator.class).to(DniNumericPartValidator.class);
    bind(ValidatingDocument.class);
  }

}
