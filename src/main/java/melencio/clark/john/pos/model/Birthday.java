package melencio.clark.john.pos.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;

import melencio.clark.john.pos.Inputs;
import melencio.clark.john.pos.Strings;

/**
 * birthday object
 * @author John Clark Melencio
 */
public class Birthday {
  private final LocalDate ld;
  private static final int currentYear = LocalDate.now().getYear();
  
  public static Birthday nextBirthday() {
    String input = Inputs.readLine("Enter your birthday (e.g. April 19, 2006): ", "Invalid birthday");
    
    try {
      new SimpleDateFormat(Strings.DATE_FMT).parse(input);
    } catch (Exception e) {
      System.err.println("Invalid date");
      return nextBirthday();
    }
    
    String[] f = input.split(",\\s");
    
    int year = Integer.parseInt(removeSpaces(f[1]));
    
    String[] md = f[0].split("\\s");
    
    int date = Integer.parseInt(md[1]);
    String monthStr = md[0];
    
    Month month = Arrays.stream(Month.values())
      .filter(mon -> mon.toString().startsWith(monthStr.trim().toUpperCase()))
      .findFirst()
      .get();
    
    if (month == null) {
      System.err.println("Invalid month");
      return nextBirthday();
    }
    
    if (year > currentYear) {
      System.err.println("Invalid year");
      return nextBirthday();
    }
    
    LocalDate ld;
    
    try {
      ld = LocalDate.of(year, month, date);
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return nextBirthday();
    }
    
    return new Birthday(ld);
  }
  
  private static String removeSpaces(String text) {
    return text.replaceAll(" ", "");
  }
  
  public Birthday(LocalDate date) {
    ld = date;
  }
  
  public int getAge() {
    int year = ld.getYear();
    int month = ld.getMonthValue();
    int day = ld.getDayOfMonth();
    
    return currentYear 
      - year
      - (MonthDay.now().isBefore(MonthDay.of(month, day)) ? 1 : 0);
  }

  public String getDate() {
    int year = ld.getYear();
    Month month = ld.getMonth();
    int day = ld.getDayOfMonth();
    
    return String.format(
      "%s %d, %d",
      month.getDisplayName(TextStyle.FULL, Locale.getDefault()),
      day,
      year
    );
  }
}
