package group2;


import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cernikt
 * @version 1.0
 * @created 06-Oct-2021 1:19:17 PM
 * This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version. This program is
 * distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details. You should have received a copy
 * of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
public class DataLists implements Subject {

	private ArrayList<TransitDataSets> RoutesList;
	private ArrayList<TransitDataSets> StopsList;
	private ArrayList<TransitDataSets> StopTimesList;
	private ArrayList<TransitDataSets> TripsList;
	private List<Observer> obsList;

	/**
	 * Accesses the current list of Routes.
	 * @return A list of Routes.
	 */
	public List<TransitDataSets> getRoutesList() {
		return RoutesList;
	}

	/**
	 * Accesses the current list of Stops.
	 * @return A list of Stops.
	 */
	public List<TransitDataSets> getStopsList() {
		return StopsList;
	}

	/**
	 * Accesses the current list of StopTimes.
	 * @return A list of StopTimes.
	 */
	public List<TransitDataSets> getStopTimesList() {
		return StopTimesList;
	}

	/**
	 * Accesses the current list of Trips.
	 * @return A list of Trips.
	 */
	public List<TransitDataSets> getTripsList() {
		return TripsList;
	}

	public DataLists(){
		RoutesList = new ArrayList<TransitDataSets>();
		StopsList = new ArrayList<TransitDataSets>();
		StopTimesList = new ArrayList<TransitDataSets>();
		TripsList = new ArrayList<TransitDataSets>();
		obsList = new ArrayList<>();
	}

	/**
	 * Clears data from all of the current lists.
	 */
	public void clearData() {
		RoutesList.clear();
		StopsList.clear();
		StopTimesList.clear();
		TripsList.clear();
	}

	public void finalize() throws Throwable {

	}

	/**
	 * Adds a TransitDataSets object to one of the lists.
	 * The Id of the object determines which list it gets added to.
	 * @param data A TransitDataSets object.
	 * @return True if the object was added to a list successfully; false otherwise.
	 */
	public boolean add(TransitDataSets data) {
		if(data.getId() == 0) {
			StopsList.add(data);
		} else if(data.getId() == 1) {
			StopTimesList.add(data);
		} else if(data.getId() == 2) {
			TripsList.add(data);
		} else if(data.getId() == 3) {
			RoutesList.add(data);
		} else {
			return false;
		}
		return true;
	}


	public boolean update(){
		return false;
	}

	/**
	 * Adds an Observer object to a list of Observers.
	 * @param obs An Observer object to add to a list.
	 */
	@Override
	public void addObserver(Observer obs) {
		obsList.add(obs);
	}

	/**
	 * Removes an Observer object from a list of Observers.
	 * @param obs An Observer object to remove from a list.
	 */
	@Override
	public void removeObserver(Observer obs) {
		obsList.remove(obs);
	}

	/**
	 * Updates the GUI with a List of TransitDataSets.
	 * @param list A list of objects to use to update the GUI.
	 */
	@Override
	public void notify(List<TransitDataSets> list) {
		for(Observer obs : obsList) {
			obs.updateGUI(list);
		}

	}

	/**
	 * Returns the information of a TransitDataSets object as a String.
	 * @param list A list to access a TransitDataSets object from.
	 * @param i The index of the object accessed.
	 * @return The information of a TransitDataSets object as a String.
	 */
	public String toString(List<TransitDataSets> list, int i) {
		//String string = "";
		/**for(int i = 0; i<list.size();i++) {
			string += list.get(i).toString();
		}**/

		//string = list.get(i).toString();
		return list.get(i).toString();
	}
}