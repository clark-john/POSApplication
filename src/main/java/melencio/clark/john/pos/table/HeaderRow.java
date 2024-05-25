package melencio.clark.john.pos.table;

import static melencio.clark.john.pos.Colors.*;
import static melencio.clark.john.pos.Strings.genSpaces;

/**
 * row for displaying column names
 * @author John Clark Melencio
 */
public class HeaderRow implements Row {
  @Override
  public String create(int rowWidth) {
    return blue("Quantity")
      + genSpaces(rowWidth - 8) + green("Item Name")
      + genSpaces(rowWidth - 9) + purple("Item Price")
      + genSpaces(rowWidth - 10) + yellow("Amount");
  }
}
