package melencio.clark.john.pos;

import static melencio.clark.john.pos.Colors.*;
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
 * main entry for POS application
 * @author John Clark Melencio
 */
public class POS {
  private final int rowWidth = 18;
  private final String STRING_REGEX = "[a-zA-Z\\s]+";
  
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
    String firstName = readLine("Enter first name: ", STRING_REGEX, "Invalid first name");
    String lastName = readLine("Enter last name: ", STRING_REGEX, "Invalid last name");
    
    String customerName = firstName + " " + lastName;
    
    Birthday bday = Birthday.nextBirthday();
    
    mem.apply(true);
    sen.apply(bday.getAge() >= 60);
    
    System.out.println(
      "Here are the list of items: " + String.join(", ", itemsList)
    );
    
    for (int i = 1; i <= 5; i++) {
      System.out.printf("Item #%d:%n", i);
      Item item = enterItem();
      subtotal += item.getPrice() * item.getQuantity();
      items.add(item);
    }

    total = subtotal;
    
    int discountTotal = mem.discountTotal(total);
    
    if (sen.isApplied())
      discountTotal += sen.discountTotal(total);
    
    total = total - discountTotal;
    
    System.out.println("Your cash is " + Strings.cashFormat(total));

    cash = readInt("Enter cash: P", "Invalid cash");
    
    newLine();
    divider();
    newLine();
    centeredTitle(storeName);
    centeredTitle(storeAddress);
    newLine();
    System.out.println(blue("Date: ") + Strings.getDateNow());
    System.out.println(blue("Time: ") + Strings.getTimeNow());
    newLine();
    System.out.println(blue("Customer: ") + green(customerName));
    System.out.println(blue("Birthday: ") + green(bday.getDate()));
    newLine();
    printTable();
    newLine();
    System.out.printf("Thank you %s for your purchase%n", green(customerName));
    newLine();
    divider();
    newLine();
  }
  
  public void newLine() {
    System.out.println();
  }

  public void centeredTitle(String text) {
    System.out.println(
      Strings.genSpaces(
        ((rowWidth * 3 + 6) / 2) - (text.length() / 2)
      ) + red(text)
    );
  }

  public void divider() {
    char[] c = new char[rowWidth * 4 - (rowWidth / 3)];
    Arrays.fill(c, '-');
    System.out.println(c);
  }
  
  public void printTable() {
    Table t = new Table(rowWidth);
    t.addRow(new HeaderRow());
    for (Item item : items)
      t.addRow(new ItemRow(item));
    t.addNewLine();
    t.addRow(new AmountRow("Sub Total:", subtotal));
    t.addRow(Row.ofText(blue("Discount(s):")));
    t.addRow(new DiscountRow(mem, subtotal));
    if (sen.isApplied())
      t.addRow(new DiscountRow(sen, subtotal));
    t.addNewLine();
    t.addRow(new AmountRow("TOTAL:", total));
    t.addRow(new AmountRow("Cash:", cash));
    t.addRow(new AmountRow("Change:", cash - total));
    t.display();
  }
  
  public Item enterItem(){
    String name = nextItemName();
    int price = readInt("How much? P", "Invalid price");
    int quantity = nextQuantity();
    return new Item(name, price, quantity);
  }
    
  public String nextItemName() {
    String itemInput = readLine("Enter item name: ", STRING_REGEX, "Invalid name").trim();
    String item = itemInput.toLowerCase();
    
    for (String i : itemsList)
      if (i.toLowerCase().equals(item)) 
        return itemInput;
    
    System.err.printf("Item \"%s\" doesn't exist. %n", itemInput);
    return nextItemName();
  }
    
  public int nextQuantity() {
    int quantity = readInt("How many? ", "Invalid quantity");
    if (quantity >= 10) {
      System.err.println("Must be from 0 to 9");
      return nextQuantity();
    }
    return quantity;
  }
}
