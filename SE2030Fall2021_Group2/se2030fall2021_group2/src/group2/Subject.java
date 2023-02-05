package group2;


import java.util.List;

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
public interface Subject {

	/**
	 * 
	 * @param obs
	 */
	public void addObserver(Observer obs);
	public void removeObserver(Observer obs);
	/**
	 * 
	 * @param list
	 */
	public void notify(List<TransitDataSets> list);

	/**void addObserver(GUI GUI);

	void notify(GUI GUI);**/
}