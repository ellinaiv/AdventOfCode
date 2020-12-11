import java.io.File;  
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;



// NOT DONE
public class bag{
    
    public static void main(String[] args) {

	try {
	    File my_obj = new File("input2.txt");
	    Scanner my_reader = new Scanner(my_obj);
	    ArrayList<String> detected_bags = new ArrayList<String>();
	    String bag_data;
	    String[] line_content;
	    String[] bag_content;
	    
	    while (my_reader.hasNextLine()) {
		bag_data = my_reader.nextLine();
		
		line_content = bag_data.split("contain");
		bag_content = line_content[1].split(",");

		
		if(line_content[1].contains("shiny gold bag")){

		    detected_bags.add(line_content[0]);

		}
	       
	    }
	    my_reader.close();

	    System.out.println(" 4 muted yellow bags".contains("muted yellow bags"));

	    my_obj = new File("input2.txt"); 
	    my_reader = new Scanner(my_obj);
	    //bag found: 2 muted yellow bags. bag containing shiny gold: muted yellow bags 

	    while (my_reader.hasNextLine()){
		bag_data = my_reader.nextLine();

		line_content = bag_data.split("contain");
		bag_content = line_content[1].split(",");
		System.out.println("\nBAG: " + line_content[0]);
		for(int i = 0; i < bag_content.length; i++){
		    for(int k = 0; k < detected_bags.size(); k++){

			System.out.print("bag found:" + bag_content[i] + " ");
			System.out.println("bag containing shiny gold: " + detected_bags.get(k));

			
			System.out.println(bag_content[i].contains(detected_bags.get(k)));
			
			if(bag_content[i].contains( detected_bags.get(k))){
			    detected_bags.add(line_content[0]);
			}
		    }
		}
	    }
	    my_reader.close();

	    System.out.println(detected_bags.size());
	    
	} catch (FileNotFoundException e) {
	    System.out.println("An error occurred.");
	    e.printStackTrace();
	}
	
    }
}
