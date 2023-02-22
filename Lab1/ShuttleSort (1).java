
// Worst case performance would have a O(n^2)
// Avg is hard to calculate because there are 2 nest loops
// Less efficent than other sorts because of how many loops it takes.

import java.util.Random;
import java.util.*;

public class ShuttleSort {

  static void ShuttleShakeSort(int[] intArray, int arrSize) {
    boolean swapped = false;
    int counter = 0;
    int compCount = 0;
    int switchCount = 0;
    int part1 = 0;
    int part2 = 0;
    while (swapped == false) {
      for (int j = 0; j < arrSize; j++) {
        if (j + 1 != arrSize) {
          compCount++;
          if (intArray[j] > intArray[j + 1]) {
            part1 = intArray[j];
            part2 = intArray[j + 1];

            intArray[j] = part2;
            intArray[j + 1] = part1;
            counter++;
            switchCount++;
            part2 = 0;
            part1 = 0;
          }
        }
      }
      for (int jj = arrSize - 1; jj >= 0; jj--) {
        if (jj - 1 != -1) {
          compCount++;
          if (intArray[jj] < intArray[jj - 1]) {
            part1 = intArray[jj];
            part2 = intArray[jj - 1];
            intArray[jj] = part2;
            intArray[jj - 1] = part1;
            counter++;
            switchCount++;
            part2 = 0;
            part1 = 0;
          }
        }
      }
      if (counter == 0) {
        System.out.println("Total ints compared: " + compCount);
        System.out.println("Total ints switched: " + switchCount);
        swapped = true;
      }
      counter = 0;
    }
  }

  static void displayArray(int[] intArray, int arrSize) {
    for (int fin = 0; fin < arrSize; fin++) {
      System.out.println(intArray[fin]);
    }
  }

  public static void main(String[] args) {
    System.out.println();
    System.out.println();
    int arrSize = Integer.parseInt(args[0]);

    Random rand = new Random();
    int[] intArray = new int[arrSize];

    for (int i = 0; i < arrSize; i++) {
      int randNum = rand.nextInt(1000);
      intArray[i] = randNum;
    }
    displayArray(intArray, arrSize);
    System.out.println();
    System.out.println();
    ShuttleShakeSort(intArray, arrSize);
    System.out.println();
    System.out.println();
    displayArray(intArray, arrSize);

  }
}
