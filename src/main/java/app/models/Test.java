package app.models;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import utils.Utils;

public class Test {
	
	

	public static void main(String[] args) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("C:\\Users\\User\\Documents\\M2_IOT\\MISC\\misc_backend\\misc_server\\src\\main\\resources\\data.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray persons = (JSONArray) jsonObject.get("persons");
           
            JSONArray places = (JSONArray) jsonObject.get("places");
            int id = 0;
            for(Object o : places) {
            	JSONObject place = (JSONObject) o;
            	String name = (String) place.get("name");
            	double longitude = Double.parseDouble((String) place.get("longitude"));
            	double latitude = Double.parseDouble((String) place.get("latitude"));
            	new Place(id++,name,latitude,longitude);
            }
            id = 0;
            for(Object o : persons) {
            	JSONObject person = (JSONObject) o;
            	String name = (String) person.get("name");
            	double longitude = Double.parseDouble((String) person.get("longitude"));
            	double latitude = Double.parseDouble((String) person.get("latitude"));
            	new Person(id++,name,latitude,longitude);
            }
            MOD mod = (MOD) Utils.ReadObjectFromFile(Utils.filepath);
            System.out.println(mod.matrix.get(0).get(1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
