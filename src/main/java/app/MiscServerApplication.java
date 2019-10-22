package app;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import models.Location;
import models.MOD;
import models.Person;
import models.Place;
import utils.Utils;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class MiscServerApplication implements ApplicationRunner{

	public static void main(String[] args) {
		SpringApplication.run(MiscServerApplication.class, args);
	}
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		System.out.println("Server is running");
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
//            Utils.WriteObjectToFile(mod);
            MOD mod = (MOD) Utils.ReadObjectFromFile(Utils.filepath);
            for (Location location : mod.locations) {
    			if(location.isPerson) {
    				Person p = (Person) location;
    				System.out.println(p.getName());
    			}else {
    				Place p = (Place) location;
    				System.out.println(p.getName());
    			}
    				
    		}
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
