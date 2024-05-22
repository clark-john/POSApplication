// Strings.java
package melencio.clark.john.pos;

import java.text.MessageFormat;
import java.util.Arrays;

/**
 * @author John Clark Melencio
 */
public class Strings {
  public static String cashFormat(int price) {
    return MessageFormat.format("P{0}", price);
  }
  
  public static String genSpaces(int width) {
    char[] c = new char[width];
    Arrays.fill(c, ' ');
    return new String(c);
  }
  
  public static String formatDate(long millis) {
    return MessageFormat.format("{0,date,MMMM d, yyyy}", millis);
  }
  
  public static String getTimeNow() {
    return MessageFormat.format("{0,time,h:mm a}", System.currentTimeMillis());
  }
  
  public static String getDateNow() {
    return formatDate(System.currentTimeMillis());
  }
}
