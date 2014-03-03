package info.jorgeyanesdiez.dnilettercalc.gui;

import info.jorgeyanesdiez.dnilettercalc.service.IValidator;
import info.jorgeyanesdiez.dnilettercalc.service.Validity;
import java.awt.Toolkit;
import javax.inject.Inject;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Implementation of a PlainDocument that supports
 * the conditional application of a pluggable validator.
 */
public class ValidatingDocument extends PlainDocument
{

  /**
   * Default serial version UID.
   */
  private static final long serialVersionUID = 1L;


  /**
   * Main constructor.
   * @param validationService The service to use to validate the data.
   */
  @Inject
  public ValidatingDocument(IValidator validationService)
  {
    super();
    this.validationService = validationService;
  }


  /** The service to use to validate the data. */
  private IValidator validationService = null;


  /**
   * Whether validation is currently enabled.
   */
  private boolean isValidationEnabled = true;


  /**
   * Can be used to determine whether any content added to
   * this document at this moment will be validated.
   * @return Whether validation is currently enabled.
   */
  public boolean isValidationEnabled()
  {
    return isValidationEnabled;
  }


  /**
   * Sets whether validation of content added to this document is desired.
   * @param isValidationEnabled Whether validation is desired.
   */
  public void setValidationEnabled(boolean isValidationEnabled)
  {
    this.isValidationEnabled = isValidationEnabled;
  }


  @Override
  public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
  {
    if (str != null)
    {
      // Determine the new value
      String newValue = null;
      if (getLength() == 0)
      {
        newValue = str;
      }
      else
      {
        try
        {
          StringBuffer content = new StringBuffer(getText(0, getLength()));
          content.insert(offs, str);
          newValue = content.toString();
        }
        catch (IndexOutOfBoundsException e)
        {
          newValue = null;
        }
      }

      // Determine if the new value is valid
      boolean isNewValueValid = !isValidationEnabled() || validationService.validate(newValue) != Validity.Invalid;

      // Update the document or notify that the input is invalid.
      if (isNewValueValid)
      {
        super.insertString(offs, str, a);
      }
      else
      {
        Toolkit.getDefaultToolkit().beep();
      }
    }
  }

}
