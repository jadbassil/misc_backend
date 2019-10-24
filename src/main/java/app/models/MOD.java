package app.models;

import java.io.Serializable;
import java.util.ArrayList;

public class MOD implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<Location> locations = new ArrayList<Location>();
	public ArrayList<ArrayList<Property>> matrix = new ArrayList<ArrayList<Property>>();
	
	public ArrayList<Integer> changed = new ArrayList<Integer>(); 
	
	public  MOD() {
		//locations.addAll(Location.getPersons());
		locations.addAll(Location.getPlaces());
		this.buildMatrix();
		System.out.println("transitive closure started...");
		boolean e = this.transitiveClosure();
		while(e) {
			e = this.transitiveClosure();
		}
	}
	
	private void buildMatrix() {
		for (Location i : locations) {
			ArrayList<Property> pi = new ArrayList<Property>();
			for(Location j : locations) {
				Property p = new Property(i, j, "Transit");
				pi.add(p);
			}
			this.matrix.add(pi);
		}
	}
	
	public boolean isTransitive() {
		boolean isTransitive = false;
		for (int i = 0; i < locations.size()  && isTransitive; i++) {
			for (int j = 0; j < locations.size() && isTransitive; j++) {
				if (matrix.get(i).get(j) != null) {
					for (int k = 0; k < locations.size() && isTransitive; k++) {
						if (matrix.get(j).get(k) != null) {
							isTransitive = isTransitive && matrix.get(j).get(k) != null;
						}
					}
				}
			}
		}
		return isTransitive;
	}
	
	public boolean transitiveClosure() {
		boolean e = false;
		for (int i = 0; i < Location.getPlaces().size(); i++) {
			for (int j = 0; j <Location.getPlaces().size();  j++) {
				if (matrix.get(i).get(j).getDistance() > 0) {
					for (int k = 0; k < Location.getPlaces().size() ; k++) {
						if(matrix.get(j).get(k).getDistance() >0)
							if (matrix.get(i).get(j).getDistance() + matrix.get(j).get(k).getDistance() <matrix.get(i).get(k).getDistance()) {
								matrix.get(i).get(k).setDistance(matrix.get(i).get(j).getDistance() + matrix.get(j).get(k).getDistance());
								matrix.get(i).get(k).setDuration(matrix.get(i).get(j).getDuration() + matrix.get(j).get(k).getDuration());
								matrix.get(i).get(k).setInstructions(matrix.get(i).get(j).getInstructions()+matrix.get(j).get(k).getInstructions());
								ArrayList<Property> newRoutes = matrix.get(i).get(j).getRoutes();
								newRoutes.addAll(matrix.get(j).get(k).getRoutes());
								matrix.get(i).get(k).setRoutes(newRoutes);
								e = true;
								System.out.println(i+" "+k+" changed to: " + i +','+j+" +" + j+','+k);
							}
					}
				}
			}
		}
		if(e) {
			System.out.println("tc done");
		}
		return e;
	}
	
	public Place nearestLocation() {
		int min = 9999999;
		Location location = null;
		int d;
		for(int i=0;i<Location.getPlaces().size();i++) { // all places
			d = 0;
			Place p = Location.getPlaces().get(i);
			System.out.print(p.getName() + ": ");
//			for(int j=0;j<Location.getPersons().size();j++) {
//				d += matrix.get(j).get(i).getDistance();
//			}
			for (Person person : Location.getPersons()) {
				Place nearestToPerson = person.getNearestPlace();
				int j = Location.getPlaces().indexOf(nearestToPerson);
				d += matrix.get(j).get(i).getDistance();
			}
			if (d<min) {
				min = d;
				location = Location.getPlaces().get(i);
			}
			System.out.println(d);
			
		}
		return (Place) location;
	}

	public ArrayList<Location> getLocations() {
		return locations;
	}
	

}
