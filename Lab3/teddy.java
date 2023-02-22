public class teddy {

  public static void main(String[] args) {
    int m = Integer.valueOf(args[0]);
    System.out.println(m % 10);
    System.out.println(((m % 100) / 10));

    teddy(m);
  }

  public static void teddy(int m) {
    String retS = "";
    if (m < 42) {
      retS = "You lose";
      System.out.println(retS);
    } else if (m == 42) {
      retS = "You win";
      System.out.println(retS);
    } else {
      if (m % 5 == 0) {
        System.out.println(m);
        teddy(m - 42);
      } else if (m % 2 == 0) {
        System.out.println(m);
        teddy(m / 2);
      } else if (m % 3 == 0 || m % 4 == 0) {
        int d1 = m % 10;
        int d2 = ((m % 100) / 10);
        System.out.println(m);
        teddy(m - (d1 * d2));
      } else {
        // System.out.println("nested else");
        retS = "You lose";
        System.out.println(retS);
      }
    }
  }
}
