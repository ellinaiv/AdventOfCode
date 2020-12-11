import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
                find_contiguous(int_data, int_data[i]);
                return;
            }
        }
        System.out.println("All ports are valid");

    }

    public static void find_contiguous(long[] all_ports, long port){

        ArrayList<Long> cont_num = new ArrayList<Long>();
        long sum = 0;
        int start_index = 1;
        int loop = 0;
        for(int i = 0; i <= all_ports.length; i++){
            cont_num.add(all_ports[i]);
            sum += all_ports[i];
            if(sum == port){
                cont_num.sort(null);
                long ret = cont_num.get(cont_num.size() - 1) + cont_num.get(0);
                System.out.println("Res: " + ret);
                return;
            }

            else if(sum > port){
                loop ++;
                i = start_index;
                start_index++;
                sum = 0;
                cont_num = new ArrayList<Long>();
            }
            else if(loop == all_ports.length){
                System.out.println("no contiguous intervals detected");
                return;
            }
        }


        /*
        LongStream stream = Arrays.stream(all_ports);
        List<Long> data = stream.boxed().collect(Collectors.toList());

        Optional<Long> max = data.stream().filter(x -> (x != element && (x + element == port))).max(Long::compare);
        Optional<Long> min = data.stream().filter(x -> (x != element && (x + element == port))).min(Long::compare);*/

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