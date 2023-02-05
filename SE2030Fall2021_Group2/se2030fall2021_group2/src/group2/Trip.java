package group2;


/**
 * @author Tyler Cernik, Derek Richards, Cherise Malisa
 * @version 1.0
 * created 06-Oct-2021 1:19:17 PM
 * This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version. This program is
 * distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details. You should have received a copy
 * of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 * */
public class Trip extends TransitDataSets{

	private String route_id;
	private String service_id;
	private String trip_id;
	private String trip_headsign;
	private String direction_id;
	private String block_id;
	private String shape_id;
	private final int id = 2;

	public Trip(String route_id, String service_id, String trip_id, String trip_headsign,
				String direction_id, String block_id, String shape_id){
		//route_id,service_id,trip_id,trip_headsign,direction_id,block_id,shape_id
		this.route_id = route_id;
		this.service_id = service_id;
		this.trip_id = trip_id;
		this.trip_headsign = trip_headsign;
		this.direction_id = direction_id;
		this.block_id = block_id;
		this.shape_id = shape_id;
	}

	public Trip() {

	}

	/**
	 * The id of the trip.
	 * This is used to determine what type of transit data set is currently being accessed.
	 * @return The id of the TransitDataSets object (which should be 2 in this case).
	 */
	public int getId(){return id;}

	/**
	 * Returns the information of the Trip object as a string.
	 * @return A string containing the information of the trip.
	 */
	@Override
	public String toString() {
		//route_id,service_id,trip_id,trip_headsign,direction_id,block_id,shape_id
		return (route_id + "," + service_id + "," + trip_id + "," + trip_headsign + "," + direction_id + "," +
				block_id + "," + shape_id + "\n");
	}

	/**
	 * Gets the route id of the trip.
	 * This is used to determine the route that is associated with the Trip.
	 * @return The route id.
	 */
	public String getRoute_id() {
		return route_id;
	}

	/**
	 * Assigns a new route id to the stop.
	 * @param route_id The new route id.
	 */
	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}

	/**
	 * Accesses the service id of the trip.
	 * @return The service id.
	 */
	public String getService_id() {
		return service_id;
	}

	/**
	 * Assigns a new service id to the Trip.
	 * @param service_id The new service id.
	 */
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	/**
	 * Accesses the trip id of the current trip.
	 * @return The current trip id.
	 */
	public String getTrip_id() {
		return trip_id;
	}

	/**
	 * Assigns a new trip id to the trip.
	 * @param trip_id The new trip id.
	 */
	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}

	/**
	 * Accesses the trip headsign of the trip.
	 * @return The current trip headsign.
	 */
	public String getTrip_headsign() {
		return trip_headsign;
	}

	/**
	 * Assigns a new trip headsign to the new trip.
	 * @param trip_headsign The new trip headsign.
	 */
	public void setTrip_headsign(String trip_headsign) {
		this.trip_headsign = trip_headsign;
	}

	/**
	 * Accesses the direction id of the trip.
	 * @return The direction id.
	 */
	public String getDirection_id() {
		return direction_id;
	}

	/**
	 * Assigns a new direction id to the trip.
	 * @param direction_id The new direction id.
	 */
	public void setDirection_id(String direction_id) {
		this.direction_id = direction_id;
	}

	/**
	 * Accesses the block id of the trip.
	 * @return The block id of the trip.
	 */
	public String getBlock_id() {
		return block_id;
	}

	/**
	 * Assigns a new block id to the trip.
	 * @param block_id The new block id.
	 */
	public void setBlock_id(String block_id) {
		this.block_id = block_id;
	}

	/**
	 * Accesses the shape id of the trip.
	 * @return The current shape id of the trip.
	 */
	public String getShape_id() {
		return shape_id;
	}

	/**
	 * Assigns a new shape id to the trip.
	 * @param shape_id The new shape id.
	 */
	public void setShape_id(String shape_id) {
		this.shape_id = shape_id;
	}

	public void finalize() throws Throwable {

	}

}