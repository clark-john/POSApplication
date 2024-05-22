/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package melencio.clark.john.pos.table;

import static melencio.clark.john.pos.Strings.*;

import melencio.clark.john.pos.model.Item;

/**
 *
 * @author John Clark Melencio
 */
public class ItemRow implements Row {
  private final Item i;

  @Override
  public String create(int rowWidth) {
    int quantity = i.getQuantity();

    String name = i.getName();
    String price = cashFormat(i.getPrice());
    
    return String.format(
      "%d%s%s%s%s%s%s",
      quantity, genSpaces(rowWidth - 1),
      name, genSpaces(rowWidth - name.length()),
      price, genSpaces(rowWidth - price.length()),
      cashFormat(i.getPrice() * i.getQuantity())
    );
  }
  
  public ItemRow(Item item) {
    i = item;
  }
}

