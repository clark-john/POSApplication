package melencio.clark.john.pos.model;

/**
 * @author John Clark Melencio
 */
public class Discount {
  private final String name;
  private final int percentage;
  private boolean isApplied = false;
  
  public void apply(boolean apply) {
    isApplied = apply;
  }
  
  public int discountTotal(int amount) {
    return isApplied
      ? (int)(((double)percentage / 100) * amount)
      : 0;
  }
  
  public String getName() {
    return name;
  }
  
  public int getPercentage() {
    return percentage;
  }
  
  public boolean isApplied() {
    return isApplied;
  }
  
  public Discount(String name, int percentage) {
    this.name = name;
    this.percentage = percentage;
  }
}
