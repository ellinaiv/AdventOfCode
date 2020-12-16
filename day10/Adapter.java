import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.lang.Math;

public class Adapter {
    public static void main(String[] args) {

        Path file_path = Paths.get(System.getProperty("user.dir"), "input.txt");

        try {
            String content = Files.readString(file_path);
            String[] data = content.split("\n");
            List<Integer> jolts = Arrays.stream(data).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
            Collections.sort(jolts);
            int res = multiplyDiffs(jolts);
            System.out.println("Diff of ones multiplied by diff of threes:      " + res);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int multiplyDiffs(List<Integer> jolts){
        int diffThrees = 1;
        int diffOnes = 1;
        for(int i = 0; i < jolts.size() - 1; i++) {
            int diff = Math.abs(jolts.get(i + 1) - jolts.get(i));
            if (diff == 3 ) {
                diffThrees++;
            }else if(diff == 1){
                diffOnes++;
            }
        }
        int res = diffOnes * diffThrees;
        return res;
    }
}
