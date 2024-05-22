package melencio.clark.john.pos.table;

import melencio.clark.john.pos.Strings;
import melencio.clark.john.pos.model.Discount;

/**
 * @author John Clark Melencio
 */
public class DiscountRow implements Row {
  private final Discount d;
  private final int price;

  @Override
  public String create(int rowWidth) {
    String name = "  -  " + d.getName();
    String percentage = d.getPercentage() + "%";
    return name 
      + Strings.genSpaces(rowWidth * 2 - name.length()) 
      + percentage + Strings.genSpaces(rowWidth - percentage.length())
      + "-" + Strings.cashFormat(d.discountTotal(price));
  }
  
  public DiscountRow(Discount discount, int price) {
    d = discount;
    this.price = price;
  }
}
