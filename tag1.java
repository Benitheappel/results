import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        try {
            String filePath = System.getProperty("user.home") + "/Desktop/input.txt";
            Scanner scanner = new Scanner(new File(filePath));
            List<Integer> left = new ArrayList<>();
            List<Integer> right = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                left.add(Integer.parseInt(parts[0]));
                right.add(Integer.parseInt(parts[1]));
            }
            scanner.close();

            int part1Result = computeDistance(left, right);
            System.out.println("Part 1: " + part1Result);

            int part2Result = computeScore(left, right);
            System.out.println("Part 2: " + part2Result);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
    public static int computeDistance(List<Integer> left, List<Integer> right) {
        Collections.sort(left);
        Collections.sort(right);
        int distance = 0;
        for (int i = 0; i < left.size(); i++) {
            distance += Math.abs(left.get(i) - right.get(i));
        }
        return distance;
    }
    public static int computeScore(List<Integer> left, List<Integer> right) {
        int score = 0;
        for (int number : left) {
            int count = 0;
            for (int value : right) {
                if (value == number) {
                    count++;
                }
            }
            score += number * count;
        }
        return score;
    }
}
