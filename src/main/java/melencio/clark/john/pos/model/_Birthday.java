package melencio.clark.john.pos.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Date;
//import java.util.TimeZone;

import melencio.clark.john.pos.Inputs;
import melencio.clark.john.pos.Strings;

/**
 * birthday object
 * @author John Clark Melencio
 */
public class _Birthday {
  private final LocalDate ld;
  private static final int currentYear = LocalDate.now().getYear();
  
  public static _Birthday nextBirthday() {
    String input = Inputs.readLine("Enter birthday: ", "Invalid birthday");
    
    SimpleDateFormat fmt = new SimpleDateFormat("MMMM d, yyyy");
//    fmt.setTimeZone(TimeZone.getTimeZone("PST"));

    Date d;
    
    try {
      d = fmt.parse(input);
    } catch (Exception e) {
      System.out.println("Invalid date");
      return nextBirthday();
    }
    
    LocalDate ld = LocalDate.ofEpochDay(d.getTime() / (24 * 60 * 60 * 1000));
    
    int year = ld.getYear();
    
    if (year < 1850 || year > currentYear) {
      System.out.println("Invalid year");
      return nextBirthday();
    }
    
    return new _Birthday(ld);
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
  
  private _Birthday(LocalDate ld) {
    this.ld = ld;
  }
}
