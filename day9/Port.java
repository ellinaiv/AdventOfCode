import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Port {
    public static void main(String[] args) {

        Path file_path = Paths.get(System.getProperty("user.dir"), "input.txt");

        try {
            String content = Files.readString(file_path);
            String[] data = content.split("\n");
            LongStream int_data = Arrays.stream(data).mapToLong(Long::parseLong);
            long[] ports = int_data.toArray();

            int preamble_range = 25;
            validate_ports(ports, preamble_range);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void validate_ports(long[] int_data, int preamble_range){

        for(int i = preamble_range; i < int_data.length; i++) {
            long[] preamble_int = Arrays.copyOfRange(int_data, i - preamble_range, i);
            LongStream stream = Arrays.stream(preamble_int);
            List<Long> preamble = stream.boxed().collect(Collectors.toList());
            long sum = Long.valueOf(int_data[i]);
            if (!valid(preamble, sum)) {
                System.out.println("Not valid port nr: " + int_data[i]);
                return;
            }
        }
        System.out.println("All ports are valid");

    }
    public static boolean valid(List<Long> data, long sum){

        for(int i = 0; i < data.size(); i++){
            long element = data.get(i);
            long filtred_elems = data.stream().filter(x -> (x != element && (x + element == sum))).count();

            if(filtred_elems != 0){
                return true;
            }
        }
        return false;
    }
}