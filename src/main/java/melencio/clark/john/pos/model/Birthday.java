package melencio.clark.john.pos.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.Arrays;

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
    String input = Inputs.readLine("Enter birthday: ", "Invalid birthday");
    
    try {
      // validate
      new SimpleDateFormat(Strings.DATE_FMT).parse(input);
    } catch (Exception e) {
      System.err.println("Invalid date");
      return nextBirthday();
    }
    
    // split date by spaced comma separator
    // note: f for fragments
    String[] f = input.split(",\\s");
    
    // parse year string
    int year = Integer.parseInt(f[1]);
    
    // split first fragment by space
    String[] md = f[0].split("\\s");
    
    // parse day of the month
    int dayOfMonth = Integer.parseInt(md[1]);
    // get month string
    String month = md[0];
    
    // get Month of month string
    Month m = Arrays.stream(Month.values())
      .filter(mon -> mon.toString().startsWith(month.toUpperCase()))
      .findFirst()
      .get();
    
    // safely invalidate when month is null
    if (m == null) {
      System.err.println("Invalid month");
      return nextBirthday();
    }
    
    // validate year (no newer than current year)
    if (year > currentYear) {
      System.err.println("Invalid year");
      return nextBirthday();
    }
    
    /*
    local date creation error cases:
      - feb 29 on non leap year
      - day of month more than 31
    */
    LocalDate ld;
    
    try {
      ld = LocalDate.of(year, m, dayOfMonth);
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return nextBirthday();
    }
    
    return new Birthday(ld);
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
    return Strings.formatDate(ld.toEpochDay() * 24 * 60 * 60 * 1000);
  }
  
  private Birthday(LocalDate date) {
    ld = date;
  }
}
