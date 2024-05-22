package melencio.clark.john.pos.table;

import java.util.ArrayList;

/**
 * @author John Clark Melencio
 */
public class Table {
  private final int rowWidth;
  private final ArrayList<Row> rows = new ArrayList<>();
  
  public Table(int rowWidth) {
    this.rowWidth = rowWidth;
  }
  
  public void display() {
    for (Row r : rows)
      System.out.println(r.create(rowWidth));
  }
  
  public void addRow(Row row) {
    rows.add(row);
  }
  
  public void addNewLine() {
    rows.add(Row.ofText(""));
  }
}
