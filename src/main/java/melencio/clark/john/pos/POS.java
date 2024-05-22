// POS.java
// eto main class
package melencio.clark.john.pos;

import static melencio.clark.john.pos.Inputs.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import melencio.clark.john.pos.model.Birthday;
import melencio.clark.john.pos.model.Discount;
import melencio.clark.john.pos.model.Item;
import melencio.clark.john.pos.table.AmountRow;
import melencio.clark.john.pos.table.DiscountRow;
import melencio.clark.john.pos.table.HeaderRow;
import melencio.clark.john.pos.table.ItemRow;
import melencio.clark.john.pos.table.Row;
import melencio.clark.john.pos.table.Table;

/**
 * @author John Clark Melencio
 */
public class POS {
  private final String storeName = "Bilihan System";
  private final String storeAddress = "967 Roosevelt Avenue, Quezon City";
  
  private final ArrayList<Item> items = new ArrayList<>();
  private final List<String> itemsList = 
    Arrays.asList("Rice", "Soda", "Cotton", "Coffee", "Meat", "Sugar");
  
  private int subtotal;
  private int total;
  private int cash;
  
  private final Discount mem = new Discount("Member's Discount", 10);
  private final Discount sen = new Discount("Senior's Discount", 20);
  
  public static void main(String[] args) {
    new POS().runSystem();
  }
  
  public void runSystem() {
    String firstName = readLine("Enter first name: ", "Invalid name");
    String lastName = readLine("Enter last name: ", "Invalid name");
    
    String customerName = firstName + " " + lastName;
    
    Birthday bday = Birthday.nextBirthday();
    
    boolean isSenior = bday.getAge() >= 60;
    
    mem.apply(true);
    sen.apply(isSenior);
    
    System.out.println(
      "Here are the list of items: " + String.join(", ", itemsList)
    );
    
    for (int i = 1; i <= 5; i++) {
      System.out.printf("Item #%d:\n", i);
      Item item = enterItem();
      subtotal += item.getPrice() * item.getQuantity();
      items.add(item);
    }
    
    int discountTotal = mem.discountTotal(subtotal);
    
    if (sen.isApplied())
      discountTotal += sen.discountTotal(subtotal);
    
    total = subtotal - discountTotal;
    
    cash = nextMoney("Enter cash: ", "Invalid cash");
    
    if (total > cash) {
      System.out.println("Insufficient balance");
      return;
    }
    
    newLine();
    System.out.println(storeName);
    System.out.println(storeAddress);
    newLine();
    System.out.println("Date: " + Strings.getDateNow());
    System.out.println("Time: " + Strings.getTimeNow());
    newLine();
    System.out.println("Customer: " + customerName);
    System.out.println("Birthday: " + bday.getDate());
    newLine();
    printTable();
    newLine();
    System.out.printf("Thank You %s for your Purchase\n", customerName);
  }
  
  public void newLine() {
    System.out.println();
  }
  
  public void printTable() {
    Table t = new Table(15);
    t.addRow(new HeaderRow());
    for (Item i : items)
      t.addRow(new ItemRow(i));
    t.addNewLine();
    t.addRow(new AmountRow("Subtotal:", subtotal));
    t.addRow(Row.ofText("Discount(s):"));
    t.addRow(new DiscountRow(mem, subtotal));
    if (sen.isApplied())
      t.addRow(new DiscountRow(sen, subtotal));
    t.addNewLine();
    t.addRow(new AmountRow("TOTAL:", total));
    t.addRow(new AmountRow("Cash", cash));
    t.addRow(new AmountRow("Change", cash - total));
    t.display();
  }
  
  public Item enterItem(){
    String name = nextItemName();
    int price = nextPrice();
    int quantity = nextQuantity();
    return new Item(name, price, quantity);
  }
  
  public int nextMoney(String prompt, String invalidMsg) {
    return Integer.parseInt(
      readLine(prompt, "P[\\d,]*", invalidMsg)
        .substring(1)
        .replaceAll(",", "")
    );
  }
  
  public String nextItemName() {
    String itemInput = readLine("Enter item name: ", "Invalid name").trim();
    String item = itemInput.toLowerCase();
    
    for (String i : itemsList)
      if (i.toLowerCase().equals(item)) 
        return itemInput;
    
    System.out.printf("Item \"%s\" doesn't exist. \n", itemInput);
    return nextItemName();
  }
  
  public int nextPrice() {
    return nextMoney("How much? ", "Invalid price");
  }
  
  public int nextQuantity() {
    int quantity = readInt("How many? ", "Invalid quantity");
    if (quantity < 0 || quantity > 9) {
      System.out.println("Must be from 0 to 9");
      return nextQuantity();
    }
    return quantity;
  }
}
