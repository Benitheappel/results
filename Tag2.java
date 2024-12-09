import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        try {
            String filePath = System.getProperty("user.home") + "/Desktop/input.txt";
            Scanner scanner = new Scanner(new File(filePath));
            List<int[]> data = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                int[] levels = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();
                data.add(levels);
            }
            scanner.close();

            int part1Result = calculateSafeReports(data, false);
            System.out.println("Part 1: " + part1Result);

            int part2Result = calculateSafeReports(data, true);
            System.out.println("Part 2: " + part2Result);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public static int calculateSafeReports(List<int[]> data, boolean allowTolerance) {
        int safeCount = 0;

        for (int[] levels : data) {
            if (isSafe(levels)) {
                safeCount++;
            } else if (allowTolerance) {
                // Check if tolerating one level makes it safe
                for (int i = 0; i < levels.length; i++) {
                    int[] toleratedLevels = removeIndex(levels, i);
                    if (isSafe(toleratedLevels)) {
                        safeCount++;
                        break;
                    }
                }
            }
        }

        return safeCount;
    }
    public static boolean isSafe(int[] levels) {
        int[] differences = calculateDifferences(levels);

        boolean isMonotonic = true;
        for (int i = 1; i < differences.length; i++) {
            if (differences[i] * differences[i - 1] <= 0) {
                isMonotonic = false;
                break;
            }
        }
        boolean isInRange = true;
        for (int diff : differences) {
            if (Math.abs(diff) > 3 || diff == 0) {
                isInRange = false;
                break;
            }
        }

        return isMonotonic && isInRange;
    }
    public static int[] calculateDifferences(int[] levels) {
        int[] differences = new int[levels.length - 1];
        for (int i = 0; i < levels.length - 1; i++) {
            differences[i] = levels[i] - levels[i + 1];
        }
        return differences;
    }
    public static int[] removeIndex(int[] array, int index) {
        int[] result = new int[array.length - 1];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i != index) {
                result[j++] = array[i];
            }
        }
        return result;
    }
}
