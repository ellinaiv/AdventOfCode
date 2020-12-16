import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.lang.Math;

public class ExpenseReport {

    public static void main(String[] args) {

        Path file_path = Paths.get(System.getProperty("user.dir"), "input.txt");

        try {
            String content = Files.readString(file_path);
            String[] data = content.split("\n");
            List<Integer> nums = Arrays.stream(data).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
            int res = findSumNums(2020, nums);
            System.out.println("The rersult of multiplication of numbers summing up to 2020:       " + res);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int findSumNums(int sum, List<Integer> nums){
        int upperLimit = nums.size();
        for(int i = 0; i < upperLimit; i ++){
            for(int k = i + 1; k < upperLimit; k++){
                for( int j = k + 1; j < upperLimit; j++) {
                    if (nums.get(i) + nums.get(k) + nums.get(j)== sum) {
                        return nums.get(i) * nums.get(k) * nums.get(j);
                    }
                }
            }
        }
        return -1;
    }
}