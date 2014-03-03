package info.jorgeyanesdiez.dnilettercalc.gui;

import info.jorgeyanesdiez.dnilettercalc.service.IControlCharactersCalculator;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.inject.Inject;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * A simple GUI to use the DNI letter calculator service.
 */
public class DniLetterCalculatorGui
{

  /**
   * Static initializer.
   */
  static
  {
    setSystemLookAndFeel();
  }


  /**
   * Attempts to set the system's look and feel. If unsuccessful,
   * the method does not throw the exception but returns it instead.
   * @return Null when the look and feel was set, the exception otherwise.
   */
  protected static Exception setSystemLookAndFeel()
  {
    Exception exception = null;
    try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
    catch (ClassNotFoundException e) { exception = e; }
    catch (InstantiationException e) { exception = e; }
    catch (IllegalAccessException e) { exception = e; }
    catch (UnsupportedLookAndFeelException e) { exception = e; }
    return exception;
  }


  /**
   * Main constructor.
   * @param calculatorService The service to use to calculate the DNI letter.
   * @param validatingDocument The document that is used to validate user input.
   */
  @Inject
  public DniLetterCalculatorGui(IControlCharactersCalculator calculatorService, ValidatingDocument validatingDocument)
  {
    super();
    this.calculatorService = calculatorService;
    this.validatingDocument = validatingDocument;
    validatingDocument.addDocumentListener(new DocumentListener() {
      public void changedUpdate(DocumentEvent e) { updateDniLetterField(); }
      public void removeUpdate(DocumentEvent e) { updateDniLetterField(); }
      public void insertUpdate(DocumentEvent e) { updateDniLetterField(); }
    });
  }


  /** The service to use to calculate the DNI letter. */
  protected IControlCharactersCalculator calculatorService = null;


  /** The document that is used to validate user input. */
  protected ValidatingDocument validatingDocument = null;


  // Window components.
  protected JFrame mainWindow = null;
  protected JLabel dniNumbersLabel = null;
  protected JTextField dniNumbersField = null;
  protected JTextField dniLetterField = null;


  /**
   * Sets whether the application is visible.
   * @param visible The visibility flag.
   */
  public void setVisible(final boolean visible)
  {
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        if (visible)
        {
          if (mainWindow == null) { create(); }
          mainWindow.setVisible(visible);
        }
        else
        {
          if (mainWindow != null) { mainWindow.setVisible(visible); }
        }
      }
    });
  }


  /**
   * Creates the GUI.
   */
  protected void create()
  {
    // Main window.
    mainWindow = new JFrame("DNI letter calculator");
    mainWindow.setLayout(new GridBagLayout());
    GridBagConstraints constraints;

    // DNI numbers label.
    dniNumbersLabel = new JLabel("DNI:");
    constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.weightx = 0.4;
    constraints.weighty = 1.0;
    constraints.insets = new Insets(6, 6, 6, 3);
    mainWindow.add(dniNumbersLabel, constraints);

    // DNI numbers field.
    dniNumbersField = new JTextField(8);
    dniNumbersField.setDocument(validatingDocument);
    constraints = new GridBagConstraints();
    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.weightx = 0.4;
    constraints.weighty = 1.0;
    constraints.insets = new Insets(6, 3, 6, 3);
    mainWindow.add(dniNumbersField, constraints);

    // DNI letter field.
    dniLetterField = new JTextField(2); // extra space because it looks better.
    dniLetterField.setEditable(false);
    constraints = new GridBagConstraints();
    constraints.gridx = 2;
    constraints.gridy = 0;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.weightx = 0.2;
    constraints.weighty = 1.0;
    constraints.insets = new Insets(6, 3, 6, 3);
    mainWindow.add(dniLetterField, constraints);

    // Prepare the main window.
    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainWindow.pack();
    mainWindow.setResizable(false);
    mainWindow.setLocationRelativeTo(null); // null==center
  }


  /**
   * Updates the DNI letter field by using the calculator service.
   */
  public void updateDniLetterField()
  {
    String dniLetter;
    try { dniLetter = calculatorService.getControlCharacters(dniNumbersField.getText()); }
    catch(IllegalArgumentException exc) { dniLetter = ""; }
    dniLetterField.setText(dniLetter);
  }

}
