public class Triangle {

  public static void main(String[] args) {
    int m = Integer.valueOf(args[0]);
    int n = Integer.valueOf(args[1]);
    int m1 = m;
    boolean switcher = true;
    if (m < n) {
      traingle(m, n, m1, switcher);
    } else {
      System.out.println("First value must be smaller than second.");
    }

  }

  public static void traingle(int m, int n, int m1, boolean switcher) {
    if (n != m1 - 1) {
      if (m == n) {
        switcher = false;
        for (int i = 0; i < m; i++) {
          System.out.print("*");
        }
        System.out.println();
      }
      if (switcher == true) {
        for (int i = 0; i < m; i++) {
          System.out.print("*");
        }
        System.out.println();
        traingle(m + 1, n, m1, switcher);
      } else {
        for (int j = 0; j < n; j++) {
          System.out.print("*");
        }
        System.out.println();
        traingle(m, n - 1, m1, switcher);
      }
    }
  }
}
