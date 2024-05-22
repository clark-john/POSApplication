package melencio.clark.john.pos.table;

import melencio.clark.john.pos.Strings;

/**
 * @author John Clark Melencio
 */
public class AmountRow implements Row {
  private final String text;
  private final int amount;

  @Override
  public String create(int rowWidth) {
    return text
      + Strings.genSpaces(rowWidth * 3 - text.length()) 
      + Strings.cashFormat(amount);
  }
  
  public AmountRow(String text, int amount) {
    this.text = text;
    this.amount = amount;
  }
}
