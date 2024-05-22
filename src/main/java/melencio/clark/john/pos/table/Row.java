package melencio.clark.john.pos.table;

/**
 * row base interface 
 * @author Clark
 */
public interface Row {
  String create(int rowWidth);
  
  public static Row ofText(String text) {
    return new Row() {
      @Override
      public String create(int rowWidth) {
        return text;
      }
    };
  }
}
