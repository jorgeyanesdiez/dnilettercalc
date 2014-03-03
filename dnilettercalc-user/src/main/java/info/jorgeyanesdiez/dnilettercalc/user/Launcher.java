package info.jorgeyanesdiez.dnilettercalc.user;

import info.jorgeyanesdiez.dnilettercalc.gui.DniLetterCalculatorGui;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * A launcher to create an instance of the GUI.
 */
public class Launcher
{

  /**
   * Command-line entry point.
   * @param args The command-line arguments.
   */
  public static void main(String[] args)
  {
    Injector injector = Guice.createInjector(new InjectorConfiguration());
    DniLetterCalculatorGui gui = injector.getInstance(DniLetterCalculatorGui.class);
    gui.setVisible(true);
  }


  /**
   * Main constructor.
   */
  private Launcher()
  {
    super();
  }

}
