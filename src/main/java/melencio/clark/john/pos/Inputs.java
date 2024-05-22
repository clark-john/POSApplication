package melencio.clark.john.pos;

import java.util.Scanner;

/**
 * input utility methods
 * @author John Clark Melencio
 */
public class Inputs {
  private static final Scanner sc = new Scanner(System.in);
  
  private static String readString(
    String prompt, String regex, String invalidMsg
  ) {
    System.out.print(prompt);
    String input = sc.nextLine();
    
    if (input.trim().isEmpty()) {
      System.err.println(invalidMsg);
      return readString(prompt, regex, invalidMsg);
    }
    
    if (regex == null || input.matches(regex))
      return input;
    
    System.err.println(invalidMsg);
    return readString(prompt, regex, invalidMsg);
  }
  
  public static String readLine(String prompt, String invalidMsg) {
    return readString(prompt, null, invalidMsg);
  }
  
  public static String readLine(
    String prompt, String regex, String invalidMsg
  ) {
    return readString(prompt, regex, invalidMsg);
  }
  
  public static int readInt(String prompt, String invalidMsg) {
    return Integer.parseInt(readString(prompt, "\\d{1,}", invalidMsg));
  }
}
