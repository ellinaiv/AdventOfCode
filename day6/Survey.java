
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
	ArrayList<String> evryone_yes = new ArrayList<String>();
	HashMap<String,Integer> all_yes = new HashMap<String,Integer>();    
	int group_members = data.length;

	for(int i = 0; i < data.length; i++){
	    String[] answer = data[i].split("");

	    for(int k = 0; k < answer.length; k++){

		if(! all_yes.containsKey(answer[k])){
		    all_yes.put(answer[k],1);

		    if(group_members == 1){
			evryone_yes.add(answer[k]);
		    }
		}else{

		    all_yes.put(answer[k], all_yes.get(answer[k]) + 1);
		    
		    if(all_yes.get(answer[k]) == group_members){
			evryone_yes.add(answer[k]);
		    }
		}
	    }
	}
	
	return evryone_yes.size();
    }
}
