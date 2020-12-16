import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TreesCounter {

    public static void main(String[] args) {

        Path file_path = Paths.get(System.getProperty("user.dir"), "input.txt");

        try {
            String content = Files.readString(file_path);
            String[] lines = content.split("\n");
            List<String[]> map = create2DMap(lines);
            int res = countTrees(map, map.get(0).length, 3, 1);
            res *= countTrees(map, map.get(0).length, 1, 1);
            res *= countTrees(map, map.get(0).length, 5, 1);
            res *= countTrees(map, map.get(0).length, 7, 1);
            res *= countTrees(map, map.get(0).length, 1, 2);
            System.out.println("The number of trees:       " + res);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<String[]> create2DMap(String[] lines){
        List<String[]> map = new ArrayList<String[]>();
        for(int i = 0; i < lines.length ;i++){
            map.add(lines[i].split(""));
        }
        return map;
    }

    public static int countTrees(List<String[]> map, int len, int right, int down){

        int treesNum = 0;
        int index = right;

        for(int i = 1; i < map.size(); i+=down){

            String[] line = map.get(i);
            if(index >= len){
                index = index - len;
            }
            if(line[index].equals("#")){
                treesNum++;
            }

            index+=right;
        }

        System.out.println(treesNum);
        return treesNum;
    }
}