package melencio.clark.john.pos.table;

import static melencio.clark.john.pos.Strings.genSpaces;

/**
 * row for displaying table headers
 * @author John Clark Melencio
 */
public class HeaderRow implements Row {
  @Override
  public String create(int rowWidth) {
    return String.format(
      "Quantity%sItem Name%sItem Price%sAmount",
      genSpaces(rowWidth - 8),
      genSpaces(rowWidth - 9),
      genSpaces(rowWidth - 10)
    );
  }
}
