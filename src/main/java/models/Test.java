package models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Test {
	
	static String filepath = "C:\\Users\\User\\Documents\\M2_IOT\\MISC\\misc_backend\\misc_server\\src\\main\\resources\\obj";
	
    public static void WriteObjectToFile(Object serObj) {
    	 
        try {
 
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static Object ReadObjectFromFile(String filepath) {
    	 
        try {
 
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
 
            Object obj = objectIn.readObject();
 
            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

	public static void main(String[] args) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("C:\\Users\\User\\Documents\\M2_IOT\\MISC\\misc_backend\\misc_server\\src\\main\\resources\\data.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray persons = (JSONArray) jsonObject.get("persons");
            for(Object o : persons) {
            	JSONObject person = (JSONObject) o;
            	String name = (String) person.get("name");
            	double longitude = Double.parseDouble((String) person.get("longitude"));
            	double latitude = Double.parseDouble((String) person.get("latitude"));
            	new Person(name,longitude,latitude);
            }
            JSONArray places = (JSONArray) jsonObject.get("places");
            for(Object o : places) {
            	JSONObject place = (JSONObject) o;
            	String name = (String) place.get("name");
            	double longitude = Double.parseDouble((String) place.get("longitude"));
            	double latitude = Double.parseDouble((String) place.get("latitude"));
            	new Place(name,longitude,latitude);
            }
            
//            MOD mod = new MOD();
//            Test.WriteObjectToFile(mod);
            MOD mod = (MOD) Test.ReadObjectFromFile(filepath); 
            for (int i = 0; i < mod.locations.size(); i++) {
				ArrayList<Property> p = mod.matrix.get(i);
				for (Property property : p) {
					System.out.print(property.getDistance() + " ");
				}
				System.out.println();
				
			}
            System.out.println(mod.matrix.get(0).get(6).getInstructions());
            boolean e = mod.transitiveClosure();
            while(e)
            	e = mod.transitiveClosure();
            for (int i = 0; i < mod.locations.size(); i++) {
				ArrayList<Property> p = mod.matrix.get(i);
				for (Property property : p) {
					System.out.print(property.getDistance() + " ");
				}
				System.out.println();
				
			}
            System.out.println(mod.matrix.get(0).get(6).getInstructions());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
