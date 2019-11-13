package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
	
	static Path p = Paths.get("");
	//public static String filepath = "C:\\Users\\User\\Documents\\M2_IOT\\MISC\\misc_backend\\misc_server\\src\\main\\resources\\obj";
	
	static File file = new File("src\\main\\resources\\obj");
	public static String filepath = file.getAbsolutePath();

	 	
	
    public static void WriteObjectToFile(Object serObj) {
    	System.out.println("filepath: " + filepath);
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
}
