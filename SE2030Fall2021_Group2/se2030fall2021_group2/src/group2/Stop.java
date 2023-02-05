package group2;


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
public class Stop extends TransitDataSets{

	private String stop_id;
	private String stop_name;
	private String stop_desc;
	private double stop_lat;
	private double stop_lon;
	private final int id = 0;

	public Stop(String stop_id, String stop_name, String stop_desc, double stop_lat, double stop_lon){
		this.stop_id = stop_id;
		this.stop_name = stop_name;
		this.stop_desc = stop_desc;
		this.stop_lat = stop_lat;
		this.stop_lon = stop_lon;
	}

    public Stop() {

    }

	/**
	 * Returns the stop id of the stop
	 * @return The stop id
	 */
    public String getStop_id() {
		return stop_id;
	}

	/**
	 * Sets the stop id to another stop id
	 * @param stop_id The new stop id
	 */
	public void setStop_id(String stop_id) {
		this.stop_id = stop_id;
	}

	/**
	 * Gets the name of the stop.
	 * @return The name of the stop.
	 */
	public String getStop_name() {
		return stop_name;
	}

	/**
	 * Assigns a new name to the stop.
	 * @param stop_name The new stop name.
	 */
	public void setStop_name(String stop_name) {
		this.stop_name = stop_name;
	}

	/**
	 * Accesses the description of the stop.
	 * @return The description of the stop.
	 */
	public String getStop_desc() {
		return stop_desc;
	}

	/**
	 * Assigns a new description to the stop.
	 * @param stop_desc The new stop description.
	 */
	public void setStop_desc(String stop_desc) {
		this.stop_desc = stop_desc;
	}

	/**
	 * Accesses the latitude of the stop.
	 * @return The latitude of the stop.
	 */
	public double getStop_lat() {
		return stop_lat;
	}

	/**
	 * Assigns the latitude of the stop.
	 * @param stop_lat The new latitude of the stop.
	 */
	public void setStop_lat(long stop_lat) {
		this.stop_lat = stop_lat;
	}

	/**
	 * Accesses the longitude of the stop.
	 * @return The longitude of the stop.
	 */
	public double getStop_lon() {
		return stop_lon;
	}

	/**
	 * Assigns the longitude of the stop.
	 * @param stop_long The new longitude of the stop.
	 */
	public void setStop_lon(double stop_long) {
		this.stop_lon = stop_long;
	}

	/**
	 * The id (not stop id) of the stop.
	 * This is used to determine what transit data set is currently being accessed.
	 * @return The id of the TransitDataSet object (which should be 0 in this case).
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the information of a Stop object as a String.
	 * @return A string containing the information of the Stop.
	 */
	@Override
	public String toString() {
		//stop_id,stop_name,stop_desc,stop_lat,stop_lon
		return (stop_id + "," + stop_name + "," + stop_desc + "," + stop_lat + "," + stop_lon + "\n");
	}

	public void finalize() throws Throwable {

	}

}