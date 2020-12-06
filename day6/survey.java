
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.*;

public class Survey
{
    public static void main(String[] args) 
    {
	
        Path file_path = Paths.get(System.getProperty("user.dir") ,"input.txt");
 
        try
        {
            String content = Files.readString(file_path);
	    String[] data = content.split("\n\n");
	    int yes_num = 0;

	    for(int i = 0; i < data.length; i++){
		yes_num += find_unique(data[i]);
	    }
	    System.out.println(yes_num);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public static int find_unique(String answers){

	String[] data = answers.split("\n");
	ArrayList<String> uniques = new ArrayList<String>();

	for(int i = 0; i < data.length; i++){
	    String[] answer = data[i].split("");
	    for(int k = 0; k < answer.length; k++){
		if(! uniques.contains(answer[k])){
		    
		    uniques.add(answer[k]);
		}

	    }
	}

	return uniques.size();
    }
}
