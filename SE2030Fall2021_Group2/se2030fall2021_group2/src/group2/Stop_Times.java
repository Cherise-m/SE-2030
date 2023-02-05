package group2;


/**
 * @author Tyler Cernik
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
public class Stop_Times extends TransitDataSets{

	private String trip_id;
	private String arrival_time;
	private String departure_time;
	private String stop_id;
	private int stop_sequence;
	private String stop_headsign;
	private String pickup_type;
	private String drop_off_type;
	private final int id = 1;

	public Stop_Times(String trip_id, String arrival_time, String departure_time, String stop_id, int stop_sequence,
					  String stop_headsign, String pickup_type, String drop_off_type){
		//trip_id,arrival_time,departure_time,stop_id,stop_sequence,stop_headsign,pickup_type,drop_off_type
		this.trip_id = trip_id;
		this.arrival_time = arrival_time;
		this.departure_time = departure_time;
		this.stop_id = stop_id;
		this.stop_sequence = stop_sequence;
		this.stop_headsign = stop_headsign;
		this.pickup_type = pickup_type;
		this.drop_off_type = drop_off_type;
	}

	public Stop_Times() {

	}

	/**
	 * Gets the Trip id of the stop time.
	 * @return The current trip id.
	 */
	public String getTrip_id() {
		return trip_id;
	}

	/**
	 * Assigns a new trip id.
	 * @param trip_id The new trip id.
	 */
	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}

	/**
	 * Gets the arrival time for a given stop on a trip.
	 * @return The current arrival time.
	 */
	public String getArrival_time() {
		return arrival_time;
	}

	/**
	 * Assigns a new arrival time for a stop.
	 * @param arrival_time The new arrival time.
	 */
	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}

	/**
	 * Gets the current departure time for a stop on a trip.
	 * @return The current departure time.
	 */
	public String getDeparture_time() {
		return departure_time;
	}

	/**
	 * Assigns a new departure time to the stop.
	 * @param departure_time The new departure time.
	 */
	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}

	/**
	 * Gets the Stop id used for the Stop Time object.
	 * @return The current stop id.
	 */
	public String getStop_id() {
		return stop_id;
	}

	/**
	 * Assigns a new stop id to the stop time object.
	 * @param stop_id The new stop id.
	 */
	public void setStop_id(String stop_id) {
		this.stop_id = stop_id;
	}

	/**
	 * Gets the stop sequence of the stop time.
	 * @return The current stop sequence.
	 */
	public int getStop_sequence() {
		return stop_sequence;
	}

	/**
	 * Assigns a new stop sequence to the stop time.
	 * @param stop_sequence The new stop sequence.
	 */
	public void setStop_sequence(int stop_sequence) {
		this.stop_sequence = stop_sequence;
	}

	/**
	 * Gets the stop headsign for the current stop time.
	 * @return The current stop headsign.
	 */
	public String getStop_headsign() {
		return stop_headsign;
	}

	/**
	 * Assigns a new stop headsign to the stop time.
	 * @param stop_headsign The new stop headsign.
	 */
	public void setStop_headsign(String stop_headsign) {
		this.stop_headsign = stop_headsign;
	}

	/**
	 * Gets the pickup type for the stop time.
	 * @return The current pickup type.
	 */
	public String getPickup_type() {
		return pickup_type;
	}

	/**
	 * Assigns a new pickup type to the stop time.
	 * @param pickup_type The new pickup type.
	 */
	public void setPickup_type(String pickup_type) {
		this.pickup_type = pickup_type;
	}

	/**
	 * Gets the Drop off type of the stop time.
	 * @return The current drop off type of the stop time.
	 */
	public String getDrop_off_type() {
		return drop_off_type;
	}

	/**
	 * Assigns a new drop off type to the stop time.
	 * @param drop_off_type The new drop off type of the stop time.
	 */
	public void setDrop_off_type(String drop_off_type) {
		this.drop_off_type = drop_off_type;
	}

	/**
	 * The id of the stop time.
	 * This is used to determine what transit data set is currently being accessed.
	 * @return The id of the TransitDataSet object (which should be 1 in this case).
	 */
	public int getId() {
		return id;
	}

	/**
	 * Represents the information of this stop times object as a string.
	 * @return A string containing information from the stop time.
	 */
	@Override
	public String toString() {
		//trip_id,arrival_time,departure_time,stop_id,stop_sequence,stop_headsign,pickup_type,drop_off_type
		return (trip_id + "," + arrival_time + "," + departure_time + "," + stop_id + "," + stop_sequence + "," +
				stop_headsign + "," + pickup_type + "," + drop_off_type + "\n");
	}

	public void finalize() throws Throwable {

	}

}