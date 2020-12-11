


import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.*;
import java.util.Map.*;

public class Boot
{
    public static void main(String[] args) 
    {
	
        Path file_path = Paths.get(System.getProperty("user.dir") ,"input.txt");
 
        try
	    {

		
		String content = Files.readString(file_path);
		String[] data = content.split("\n");
		
		ArrayList<Integer> executed_commands = new ArrayList<Integer>();
		Map<Integer, Integer> loop = new HashMap<Integer, Integer>();

		int acc = 0;
	        int last_command = 0;
		int loopnr = 0;
		for(int i = 0; i < data.length; i++){

		    
		    if(executed_commands.contains(i)){
			
			if(loop.containsKey(i)){
			    loop.put(i, loop.get(i) + 1);
			}else{
			    loop.put(i, 1);
			}
			
			
			int iter = 0;
			for(Map.Entry<Integer, Integer> entry : loop.entrySet()) {
			    iter += entry.getValue();
			}
 			if(iter/2 == loop.size()){
			    
			    for(Map.Entry<Integer, Integer> entry : loop.entrySet()) {
				int index = entry.getKey();

				int res = eliminate_loop(entry, data); 
				if( res != -1){
				    return;
				}
			    }
			    System.out.println("No possible solution");
			    return; 
			}

		    }
		    
		    executed_commands.add(i);		    

		    String[] line_content = data[i].split(" ");
		    String operation = line_content[0];

		    String sign = line_content[1].charAt(0) + "";
		    line_content[1] = line_content[1].replace(sign, "");
		    int value = Integer.parseInt(line_content[1]);

		    
		    switch(operation){

		    
		    case "acc":
			
			if(sign.equals("+")){
			    acc += value;
			}else{
			    acc -= value;
			}

			break;
		    case "jmp":
		
		
			if(sign.equals("+")){
                            i += value;
                        }else{
                            i -= value;
			}
			i -= 1;
	
			break;
						
		    }
		}
		System.out.println(acc);
	    }
	catch (IOException e) 
	    {
		e.printStackTrace();
	    }
    }

    public static int eliminate_loop(Map.Entry<Integer, Integer> entry, String[] data){
	int i = entry.getKey();
	int res;
	
	if(data[i].contains("jmp")){

	    data[i] = data[i].replace("jmp", "nop");
	    res = execute_boot(data);

	    if( res == -1){

		data[i] = data[i].replace("nop", "jmp");

		return -1;
	    }
	    return res;
	}else if(data[i].contains("nop")){
            data[i] = data[i].replace("nop", "jmp");
	    res = execute_boot(data);
	    if( res == -1){
                data[i] = data[i].replace("jmp", "nop");	
		return -1;
            }
	    return res;
	}else{
	    return -1;
	}
    }

    public static int execute_boot(String[] data){
	  ArrayList<Integer> executed_commands = new ArrayList<Integer>();
                Map<Integer, Integer> loop = new HashMap<Integer, Integer>();

                int acc = 0;
                int last_command = 0;
                int loopnr = 0;
                for(int i = 0; i < data.length; i++){


                    if(executed_commands.contains(i)){

                        if(loop.containsKey(i)){
                            loop.put(i, loop.get(i) + 1);
                        }else{
                            loop.put(i, 1);
                        }


                        int iter = 0;
                        for(Map.Entry<Integer, Integer> entry : loop.entrySet()) {
                            iter += entry.getValue();
                        }
                        if(iter/2 == loop.size()){
			    return -1;
			}
		    }
		    executed_commands.add(i);

                    String[] line_content = data[i].split(" ");
                    String operation = line_content[0];

                    String sign = line_content[1].charAt(0) + "";
                    line_content[1] = line_content[1].replace(sign, "");
                    int value = Integer.parseInt(line_content[1]);


                    switch(operation){


                    case "acc":

                        if(sign.equals("+")){
                            acc += value;
                        }else{
                            acc -= value;
                        }
                    
			break;
                    case "jmp":


                        if(sign.equals("+")){
                            i += value;
                        }else{
                            i -= value;
                        }
                        i -= 1;

                        break;

                    }
                }
		System.out.println("acc value: " + acc);
		return acc;
    }
}

    

