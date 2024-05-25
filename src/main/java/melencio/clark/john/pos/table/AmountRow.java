package melencio.clark.john.pos.table;

import static melencio.clark.john.pos.Colors.*;

import melencio.clark.john.pos.Strings;

/**
 * row for displaying cash amount
 * @author John Clark Melencio
 */
public class AmountRow implements Row {
  private final String text;
  private final int amount;

  public AmountRow(String text, int amount) {
    this.text = text;
    this.amount = amount;
  }
  
  @Override
  public String create(int rowWidth) {
    return blue(text)
      + Strings.genSpaces(rowWidth * 3 - text.length()) 
      + green(Strings.cashFormat(amount));
  }
}
