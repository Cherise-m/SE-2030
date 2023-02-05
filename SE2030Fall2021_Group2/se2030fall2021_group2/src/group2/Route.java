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
public class Route extends TransitDataSets {

	private String route_id;
	private String agency_id;
	private String route_short_name;
	private String route_long_name;
	private String route_desc;
	private int route_type;
	private String route_url;
	private String route_color;
	private String route_text_color;
	private final int id = 3;

	public Route(String route_id, String agency_id, String route_short_name, String route_long_name, String route_desc,
				 int route_type, String route_url, String route_color, String route_text_color){
		//route_id,agency_id,route_short_name,route_long_name,route_desc,route_type,route_url,route_color,route_text_color
		this.route_id = route_id;
		this.agency_id = agency_id;
		this.route_short_name = route_short_name;
		this.route_long_name = route_long_name;
		this.route_desc = route_desc;
		this.route_type = route_type;
		this.route_url = route_url;
		this.route_color = route_color;
		this.route_text_color = route_text_color;
	}

	public Route(){

	}

	/**
	 * The id of the route.
	 * This used to determine the type of transit data set currently being accessed.
	 * @return The id of the TransitDataSets object (which should be 3 in this case).
	 */
	public int getId(){return id;}

	/**
	 * Accesses the route id of the route.
	 * @return The current route id.
	 */
	public String getRoute_id() {
		return route_id;
	}

	/**
	 * Accesses the agency id of the route.
	 * @return The current agency id.
	 */
	public String getAgency_id() {
		return agency_id;
	}

	/**
	 * Assigns a new agency id to the route.
	 * @param agency_id The new agency id.
	 */
	public void setAgency_id(String agency_id) {
		this.agency_id = agency_id;
	}

	/**
	 * Accesses the short name of the route.
	 * @return The current short name of the route.
	 */
	public String getRoute_short_name() {
		return route_short_name;
	}

	/**
	 * Assigns a new short name to the route.
	 * @param route_short_name The new short name for the route.
	 */
	public void setRoute_short_name(String route_short_name) {
		this.route_short_name = route_short_name;
	}

	/**
	 * Accesses the long name of the route.
	 * @return The current long name of the route.
	 */
	public String getRoute_long_name() {
		return route_long_name;
	}

	/**
	 * Assigns a new long name to the route.
	 * @param route_long_name The new long name of the route.
	 */
	public void setRoute_long_name(String route_long_name) {
		this.route_long_name = route_long_name;
	}

	/**
	 * Accesses the route description of the route.
	 * @return The current description of the route.
	 */
	public String getRoute_desc() {
		return route_desc;
	}

	/**
	 * Assigns a new description to the route.
	 * @param route_desc The new description of the route.
	 */
	public void setRoute_desc(String route_desc) {
		this.route_desc = route_desc;
	}

	/**
	 * Accesses the route type.
	 * @return The current route type.
	 */
	public int getRoute_type() {
		return route_type;
	}

	/**
	 * Assigns a new route type to the route.
	 * @param route_type The new route type.
	 */
	public void setRoute_type(int route_type) {
		this.route_type = route_type;
	}

	/**
	 * Accesses the color of the route.
	 * @return The current route color.
	 */
	public String getRoute_color() {
		return route_color;
	}

	/**
	 * Assigns a new color to the route.
	 * @param route_color The new route color.
	 */
	public void setRoute_color(String route_color) {
		this.route_color = route_color;
	}

	/**
	 * Assigns a new route id to the route.
	 * @param route_id The new route id.
	 */
	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}

	public void finalize() throws Throwable {

	}

	/**
	 * Returns the information of the route as a string.
	 * @return A string containing the information as a route.
	 */
	public String toString(){
		//route_id,agency_id,route_short_name,route_long_name,route_desc,route_type,route_url,route_color,route_text_color
		return (route_id + "," + agency_id + "," + route_short_name + "," + route_long_name + "," + route_desc + "," +
				route_type + "," +route_url + "," + route_color + "," + route_text_color + "\n");
	}

}