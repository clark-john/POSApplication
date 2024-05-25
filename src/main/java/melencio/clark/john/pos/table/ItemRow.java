package melencio.clark.john.pos.table;

import static melencio.clark.john.pos.Strings.*;

import melencio.clark.john.pos.model.Item;

/**
 * row for displaying item information
 * @author John Clark Melencio
 */
public class ItemRow implements Row {
  private final Item item;

  public ItemRow(Item item) {
    this.item = item;
  }
  
  @Override
  public String create(int rowWidth) {
    int quantity = item.getQuantity();

    String name = item.getName();
    String price = cashFormat(item.getPrice());
    
    return String.format(
      "%d%s%s%s%s%s%s",
      quantity, genSpaces(rowWidth - (quantity + "").length()),
      name, genSpaces(rowWidth - name.length()),
      price, genSpaces(rowWidth - price.length()),
      cashFormat(item.getPrice() * item.getQuantity())
    );
  }
}
