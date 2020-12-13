import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;


public class advent_onboarding{
    
    public static void main(String[] args) {

	int max = 0;
	List<Integer> ids = new ArrayList<Integer>();
	try {
	    File my_obj = new File("input.txt");
	    Scanner my_reader = new Scanner(my_obj);
	    while (my_reader.hasNextLine()) {
		String boarding_pass = my_reader.nextLine();
	       
		int id = find_id(boarding_pass);
		ids.add(id);
		if( id > max ){
		    max = id;
		}
		
	    }
	    Collections.sort(ids);
	    
	    for(int i = 0; i < ids.size() - 1; i++){

		int tmp = ids.get(i);
		int next = ids.get(i+1);
		if(next - tmp > 1){
		    System.out.println(tmp + 1);
		    return;
		}	      
	    }
	    
	    my_reader.close();
	} catch (FileNotFoundException e) {
	    System.out.println("An error occurred.");
	    e.printStackTrace();
	}

	
    }
    
    public static int find_id(String boarding_pass){
	String[] data = boarding_pass.split(""); 
	int row = binary_search(data, 0, 127, 0);
	int column = binary_search(data, 0, 7, 7);
	return row * 8 + column;
    }

    public static int binary_search(String data[], int lower, int upper, int index){
	String check = data[index];
	
	if(index == 6 || index == data.length - 1){
	    if(check.equals("F") || check.equals("L")){
		return lower;
	    }else{
		return upper;
	    }	    
	}

	int row = (upper - lower)/2;
	index++;

	if(check.equals("F") || check.equals("L")){
	    row = lower + row;
	    return binary_search(data, lower, row, index);

	}else{
	    row = upper - row;
	    return binary_search(data, row, upper, index);
	}
    }
}





