package melencio.clark.john.pos.table;

import static melencio.clark.john.pos.Colors.*;

import melencio.clark.john.pos.Strings;
import melencio.clark.john.pos.model.Discount;

/**
 * row for displaying discount information
 * @author John Clark Melencio
 */
public class DiscountRow implements Row {
  private final Discount discount;
  private final int price;

  public DiscountRow(Discount discount, int price) {
    this.discount = discount;
    this.price = price;
  }
  
  @Override
  public String create(int rowWidth) {
    String name = "  -  " + discount.getName();
    String percentage = discount.getPercentage() + "%";
    return cyan(name) 
      + Strings.genSpaces(rowWidth * 2 - name.length()) 
      + green(percentage) + Strings.genSpaces(rowWidth - percentage.length())
      + red("-" + Strings.cashFormat(discount.discountTotal(price)));
  }
}
