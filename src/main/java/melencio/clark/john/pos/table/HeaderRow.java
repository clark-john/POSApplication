/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package melencio.clark.john.pos.table;

import static melencio.clark.john.pos.Strings.genSpaces;

/**
 *
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
