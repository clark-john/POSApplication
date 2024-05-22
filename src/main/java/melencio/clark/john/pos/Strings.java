package melencio.clark.john.pos;

import java.text.MessageFormat;
import java.util.Arrays;

/**
 * string utility methods
 * @author John Clark Melencio
 */
public class Strings {
  public final static String DATE_FMT = "MMMM d, yyyy";

  public static String cashFormat(int price) {
    return MessageFormat.format("P{0}", price);
  }
  
  public static String genSpaces(int width) {
    char[] c = new char[width];
    Arrays.fill(c, ' ');
    return new String(c);
  }
  
  public static String formatDate(long millis) {
    return MessageFormat.format("{0,date," + DATE_FMT + "}", millis);
  }
  
  public static String getTimeNow() {
    return MessageFormat.format("{0,time,h:mm a}", System.currentTimeMillis());
  }
  
  public static String getDateNow() {
    return formatDate(System.currentTimeMillis());
  }
}
