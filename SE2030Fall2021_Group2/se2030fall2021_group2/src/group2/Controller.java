package group2;


import javafx.scene.control.Alert;
import org.junit.platform.commons.util.StringUtils;

import java.io.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

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

public class Controller {

    Scanner in;

    public static DataLists dataList;

    /**
     * Constructor
     */
    public Controller() {
        dataList = new DataLists();
        //dataList.addObserver();
    }

    /**
     * Author: Tyler Cernik
     * A method that calls the method to add an observer. Needed to add this in because we were not able to
     * call the method from the dataList on its own
     * @param observer the class that is an observer, in this case, the GUI class
     */
    public static void attachObserver(Observer observer) {
        dataList.addObserver(observer);
    }

    public void finalize() throws Throwable {

    }

    /**
     * @param Stop The stop that is to be altered
     */
    public boolean alterStopLocation(Stop Stop) {
        return false;
    }

    /**
     * @param departTime  time the trip leaves
     * @param arrivalTime time the trip arrives
     */
    public boolean alterTripStopTimes(double departTime, double arrivalTime) {
        return false;
    }


    /**
     * calcautes the distance of a specified trip
     * @param trip_id the id of the trip that you want to find the distance of
     * @return the distance of the trip
     */
    public double calculateDistance(String trip_id) {
        double distance = 0;
        Stop current;
        Stop next;

        ArrayList<Stop_Times> stopTimesOnTrip = new ArrayList<Stop_Times>();
        ArrayList<Stop> stopsOnTrip = new ArrayList<>();
        ArrayList<Stop_Times> stopTimesList= dataList.getStopTimesList().stream().map(obj -> (Stop_Times)obj)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Stop> stopList= dataList.getStopsList().stream().map(obj -> (Stop)obj)
                .collect(Collectors.toCollection(ArrayList::new));
        for(Stop_Times stopTime: stopTimesList) {
            if(stopTime.getTrip_id().equals(trip_id)){
                stopTimesOnTrip.add(stopTime);
            }
        }
        ArrayList<String> stopIDs = stopTimesOnTrip.stream().map(Stop_Times::getStop_id).collect(Collectors
                .toCollection(ArrayList::new));
        for(Stop stop: stopList) {
            if(stopIDs.contains(stop.getStop_id())) {
                stopsOnTrip.add(stop);
            }
        }
        stopsOnTrip.sort(Comparator.comparing(Stop::getStop_id));
        ListIterator<Stop> iterator = stopsOnTrip.listIterator();
        current = iterator.next();
        while(iterator.hasNext()) {
            next = iterator.next();
            distance += distance(current.getStop_lat(), next.getStop_lat(), current.getStop_lon(), next.getStop_lon());
            current = next;
        }

        distance = distance * 0.000621371;

        return distance;
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }

    /**
     * Written by Derek Richards
     * Calculates the number of trips that a given stop appears on.
     *
     * @param stop_id The stop id of the given stop.
     * @return The number of trips that the given stop is found on.
     */
    public int calculateNumTripsPerStops(String stop_id) {
        List<TransitDataSets> transitStopTimesList = dataList.getStopTimesList();
        List<Stop_Times> stopTimesList = createStopTimesList(transitStopTimesList);
        if(!isStopValid(stop_id)) {
            throw new NumberFormatException();
        }
        String currentTrip = "";
        int numTrips = 0;
        for (Stop_Times stopTime : stopTimesList) {
            if (stopTime.getStop_id().equals(stop_id) &&
                    !stopTime.getTrip_id().equals(currentTrip)) {
                numTrips++;
                currentTrip = stopTime.getTrip_id();
            }
        }

        return numTrips;
    }

    /**
     * Author: Tyler Cernik
     * Checks if a stop is valid. Used in calculateNumTripsPerStops()
     * @param StopId the stop_id of the stop you want to check.
     * @return boolean if the stop is valid
     */
    public boolean isStopValid(String StopId) {
        ArrayList<Stop> stopList= dataList.getStopsList().stream().map(obj -> (Stop)obj)
                .collect(Collectors.toCollection(ArrayList::new));
        for(Stop stop: stopList) {
            if(stop.getStop_id().equals(StopId)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Calculate the average speed of a trip
     * Combo of numtripsperstop and calculate distance
     * **This method is not yet implemented**
     * @return double speed
     */
    public double calculateSpeed() {
        return 0;
    }

    /**
     * @param listOfStopTimes List of all stop times from dataset
     */
    public void createBusPlot(java.util.List<Stop_Times> listOfStopTimes) {

    }

    /**
     * @param ListOfStops List of all stop objects from data
     */
    public void createStopsPlot(List<Stop> ListOfStops) {

    }

    /**
     * Author: Tyler Cernik
     * Exports the data stored in the database into files, into a chosen directory
     * @param directoryPath The directory chosen by the user for the files to be placed in
     * @return boolean of if the files were exported successfully
     * @throws IOException
     */
    public boolean export(String directoryPath) throws IOException {
        int fileName_iterator_number = 1;
        File dir = new File(directoryPath + File.separator + "GTFS");
        if (!dir.exists()) {
            dir.mkdir();
        }

        File routesFile = new File(dir.getAbsolutePath() + File.separator + "routes" + fileName_iterator_number + ".txt");
        File tripsFile = new File(dir.getAbsolutePath() + File.separator + "trips" + fileName_iterator_number + ".txt");
        File stopsFile = new File(dir.getAbsolutePath() + File.separator + "stops" + fileName_iterator_number + ".txt");
        File stop_timesFile = new File(dir.getAbsolutePath() + File.separator + "stop_times" + fileName_iterator_number + ".txt");
        File[] fileArray = new File[]{routesFile, tripsFile, stopsFile, stop_timesFile};
        for (File file : fileArray) {
            PrintWriter writer = new PrintWriter(file);
            if (file.getName().contains("routes")) {
                writer.write("route_id,agency_id,route_short_name,route_long_name," +
                        "route_desc,route_type,route_url,route_color,route_text_color\n");
                for (int k = 0; k < dataList.getRoutesList().size(); k++) {
                    writer.write(dataList.toString(dataList.getRoutesList(), k));
                }
                //writer.write(dataList.toString(dataList.getRoutesList()));
                /**for(TransitDataSets data: dataList.getRoutesList()) {
                 writer.write(dataList.getRoutesList().toString());
                 }**/
            } else if (file.getName().contains("trips")) {
                writer.write("route_id,service_id,trip_id,trip_headsign,direction_id,block_id,shape_id\n");
                //writer.write(dataList.toString(dataList.getTripsList()));
                for (int k = 0; k < dataList.getTripsList().size(); k++) {
                    writer.write(dataList.toString(dataList.getTripsList(), k));
                }
            } else if (file.getName().contains("stops")) {
                writer.write("stop_id,stop_name,stop_desc,stop_lat,stop_lon\n");
                //writer.write(dataList.toString(dataList.getStopsList()));
                for (int k = 0; k < dataList.getStopsList().size(); k++) {
                    writer.write(dataList.toString(dataList.getStopsList(), k));
                }
            } else if (file.getName().contains("stop_times")) {
                writer.write("trip_id,arrival_time,departure_time,stop_id,stop_sequence,stop_headsign," +
                        "pickup_type,drop_off_type\n");
                //writer.write(dataList.toString(dataList.getStopTimesList()));
                for (int k = 0; k < dataList.getStopTimesList().size(); k++) {
                    writer.write(dataList.toString(dataList.getStopTimesList(), k));
                }

            } else {
                writer.close();
                return false;
            }
            writer.close();

        }
        fileName_iterator_number++;
        return true;
    }

    /**
     * Author: Tyler Cernik
     * Imports the valid files that the user selected into the database
     * @param fileList A list of files obtained by the gui importFiles method
     * @return boolean of if files were imported successfully
     */
    public boolean importFiles(List<File> fileList) throws FileNotFoundException, NullPointerException {
        dataList.clearData();
        for (File file : fileList) {
            String fileName = file.getName();
            in = new Scanner(file);
            String line;
            String header = in.nextLine();
            if (isHeaderValid(header)) {
                if (fileName.toLowerCase().contains("trips")) {
                    while (in.hasNextLine()) {
                        line = in.nextLine();

                        if (isLineValid(line, 6)) {
                            String[] transitData = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
                            if (isStringDataValid(transitData[0]) && isStringDataValid(transitData[2])) {
                                dataList.add(new Trip(transitData[0], transitData[1], transitData[2], transitData[3],
                                        transitData[4], transitData[5], transitData[6]));
                            }


                        }

                    }
                    dataList.notify(dataList.getTripsList());
                } else if (fileName.toLowerCase().contains("stops")) {
                    while (in.hasNextLine()) {
                        line = in.nextLine();
                        if (isLineValid(line, 4)) {
                            String[] transitData = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
                            if (isStringDataValid(transitData[0]) && isStringDataValid(transitData[1]) &&
                                    isDoubleDataValid(transitData[3]) && isDoubleDataValid(transitData[4])) {
                                dataList.add(new Stop(transitData[0], transitData[1], transitData[2],
                                        Double.parseDouble(transitData[3]), Double.parseDouble(transitData[4])));

                            }
                        }
                    }
                    dataList.notify(dataList.getStopsList());
                } else if (fileName.toLowerCase().contains("stop_times")) {
                    while (in.hasNextLine()) {
                        line = in.nextLine();
                        if (isLineValid(line, 7)) {
                            String[] transitData = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
                            if (isStringDataValid(transitData[0]) && isStringDataValid(transitData[1]) &&
                                    isStringDataValid(transitData[2]) && isStringDataValid(transitData[3])
                                    && isPosIntDataValid(transitData[4])) {
                                dataList.add(new Stop_Times(transitData[0], transitData[1], transitData[2],
                                        transitData[3], Integer.parseInt(transitData[4]), transitData[5],
                                        transitData[6], transitData[7]));
                            }
                        }
                    }
                    dataList.notify(dataList.getStopTimesList());
                } else if (fileName.toLowerCase().contains("routes")) {
                    while (in.hasNextLine()) {
                        line = in.nextLine();
                        if (isLineValid(line, 8)) {
                            String[] transitData = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
                            if (isStringDataValid(transitData[0]) && isStringDataValid(transitData[7])) {
                                dataList.add(new Route(transitData[0], transitData[1], transitData[2], transitData[3],
                                        transitData[4], Integer.parseInt(transitData[5]), transitData[6], transitData[7],
                                        transitData[8]));
                            }
                        }
                    }
                    dataList.notify(dataList.getRoutesList());
                } else {
                    return false;
                }
            }

            in.close();
        }
        return true;
    }

    /**
     * Author: Tyler Cernik
     * checks if a line is valid
     * @param line the line
     * @param numCommas number commas it needs to be valid
     * @return if the line is valid
     */
    public boolean isLineValid(String line, int numCommas) {

        //return numCommas == line.chars().filter(ch -> ch == 'e').count();
        int commas = 0;
        try {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == ',') {
                    commas++;
                }
            }
            return commas == numCommas;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * Author: Tyler Cernik
     * checks if positive int data is valid
     * @param posIntData
     * @return
     */
    public boolean isPosIntDataValid(String posIntData) {
        try {
            int posInt = Integer.parseInt(posIntData);
            return !posIntData.equals("") && posInt > 0;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    /**
     * Author: Tyler Cernik
     * checks if header line is valid
     * @param header the header line
     * @return if the header is valid
     */
    public boolean isHeaderValid(String header) {
        if (header != null) {
            if (header.equals("stop_id,stop_name,stop_desc,stop_lat,stop_lon")) {
                return true;
            } else if (header.equals("trip_id,arrival_time,departure_time,stop_id,stop_sequence," +
                    "stop_headsign,pickup_type,drop_off_type")) {
                return true;
            } else if (header.equals("route_id,service_id,trip_id,trip_headsign,direction_id,block_id,shape_id")) {
                return true;
            } else if (header.equals("route_id,agency_id,route_short_name,route_long_name,route_desc,route_type," +
                    "route_url,route_color,route_text_color")) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Author: Tyler Cernik
     * checks if integer data is valid
     * @param intData an int that is being checked
     * @return if the data is valid
     */
    public boolean isIntDataValid(String intData) {
        try {
            Integer.parseInt(intData);
            return !intData.equals("");
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    /**
     * Author: Tyler Cernik
     * checks if string data is valid
     * @param stringData the string being checked
     * @return if the string is valid
     */
    public boolean isStringDataValid(String stringData) {
        return stringData != null && !stringData.equals("");
    }

    /**
     * Author: Tyler Cernik
     * checks if double data is valid
     * @param doubleData the double being checked
     * @return if the double data is valid
     */
    public boolean isDoubleDataValid(String doubleData) {
        try {
            Double.parseDouble(doubleData);
            return !doubleData.equals("");
        } catch (NumberFormatException exception) {
            return false;
        }
    }


    /**
     * Written by Derek Richards.
     * Searches for a list of trips happening in the future using a route id.
     * The list of trips is a list of trips containing the route.
     *
     * @param route_id The route id for the route to search for.
     * @return The list of trips containing the route.
     */
    public List<String> searchForTripByRoute(String route_id) {
        List<TransitDataSets> transitTripsList = dataList.getTripsList();
        List<Trip> tripList = createTripList(transitTripsList);
        List<String> listOfTripIds = new ArrayList<>();


        for (Trip trip : tripList) {
            if (trip.getRoute_id().equals(route_id)) {
                listOfTripIds.add(trip.getTrip_id());
            }


        }

        return listOfTripIds;
    }

    /**
     * Author: Cherise Malisa
     * Searches for routeId using the stopId entered by the user
     * @param stop_id The stop you want to search by
     *                stopID validation inspired by Derick
     * @return a string of the routeIds where the stop appears
     */
    public String searchByStop(String stop_id) {

        String message = "";
        List<TransitDataSets> transitTrips = dataList.getTripsList();
        List<TransitDataSets> transitStopTimesList = dataList.getStopTimesList();

        if (transitTrips.isEmpty() && transitStopTimesList.isEmpty()) {
            Alert notLoaded = new Alert(Alert.AlertType.ERROR);
            notLoaded.setTitle("Error with files");
            notLoaded.setHeaderText("Issue getting routes");
            notLoaded.setContentText("Trips file and stop_times file need to be loaded to complete this action");
            notLoaded.showAndWait();
        }

        List<Trip> trips = new ArrayList<>();
        List<Stop_Times> stopTimesList = new ArrayList<>();
        HashSet<String> route_Ids = new HashSet<>();
        List<String> matching_stopIds = new ArrayList<>();

        String listOfRoutes = " ";
        HashSet<String> uniqueTripIds = new HashSet<>();

        for (TransitDataSets transitTrip : transitTrips) {
            if (transitTrip.getId() == 2) {
                trips.add((Trip) transitTrip);
            }
        }

        for (TransitDataSets transitStopTime : transitStopTimesList) {
            if (transitStopTime.getId() == 1) {
                stopTimesList.add((Stop_Times) transitStopTime);
            }
        }


        for (Stop_Times stop_timesC : stopTimesList) {
            if (stop_id.equals(stop_timesC.getStop_id())) {
                matching_stopIds.add(stop_id);
            }
        }
        if (matching_stopIds.isEmpty()) {
            Alert notLoaded = new Alert(Alert.AlertType.ERROR);
            notLoaded.setTitle("Error with StopId");
            notLoaded.setHeaderText("Issue getting routes");
            notLoaded.setContentText("StopId entered is invalid and does not exist" +
                    "\n please enter a valid stopId");
            notLoaded.showAndWait();
        }

        for (Stop_Times stopTimes : stopTimesList) {
            if (stop_id.equals(stopTimes.getStop_id())) {
                uniqueTripIds.add(stopTimes.getTrip_id());
            }

        }

        for (String trip_id_list : uniqueTripIds) {
            for (Trip tripLines : trips) {
                if (tripLines.getTrip_id().equals(trip_id_list)) {
                    route_Ids.add(tripLines.getRoute_id());
                }
            }
        }

        for (String routeIds : route_Ids) {
            listOfRoutes += routeIds + "\n";

        }
        message = "These are the routes available:\n"
                + listOfRoutes;
        return message;


    }

    /**
     * Author: Cherise Malisa
     * Takes in a route Id from the user, verifies that that Id is valid and then searches for stop Ids that
     *  are on the routes for that route Id
     * @param route_id A selected route id that you want to search for stops by
     * @return a list of the corresponding stop Ids that are found for that route
     */
    public String searchByRoute(String route_id) {

        String message = "";

        List<TransitDataSets> transitTrips = dataList.getTripsList();
        List<TransitDataSets> transitStopTimesList = dataList.getStopTimesList();

        if (transitTrips.isEmpty() && transitStopTimesList.isEmpty()) {
            Alert notLoaded = new Alert(Alert.AlertType.ERROR);
            notLoaded.setTitle("Error with files");
            notLoaded.setHeaderText("Issue getting routes");
            notLoaded.setContentText("Trips file and stop_times file need to be loaded to complete this action");
            notLoaded.showAndWait();
        }

        List<Trip> trips = new ArrayList<>();
        List<Stop_Times> stopTimesList = new ArrayList<>();
        HashSet<String> stop_Ids = new HashSet<>();
        List<String> matching_routeIds = new ArrayList<>();

        String listOfStops = " ";
        HashSet<String> uniqueTripIds = new HashSet<>();

        for (TransitDataSets transitTrip : transitTrips) {
            if (transitTrip.getId() == 2) {
                trips.add((Trip) transitTrip);
            }
        }

        for (TransitDataSets transitStopTime : transitStopTimesList) {
            if (transitStopTime.getId() == 1) {
                stopTimesList.add((Stop_Times) transitStopTime);
            }
        }

        for (Trip tripC : trips) {
            if (route_id.equals(tripC.getRoute_id())) {
                matching_routeIds.add(route_id);
            }
        }

        if (matching_routeIds.isEmpty()) {
            Alert notLoaded = new Alert(Alert.AlertType.ERROR);
            notLoaded.setTitle("Error with RouteId");
            notLoaded.setHeaderText("Issue getting stops");
            notLoaded.setContentText("RouteId entered is invalid and does not exist" +
                    "\n please enter a valid RouteId");
            notLoaded.showAndWait();
        }

        for (Trip trip : trips) {
            if (route_id.equals(trip.getRoute_id())) {
                uniqueTripIds.add(trip.getTrip_id());
            }
        }

        for (String tripIds : uniqueTripIds) {
            for (Stop_Times stop_times : stopTimesList) {
                if (stop_times.getTrip_id().equals(tripIds)) {
                    stop_Ids.add(stop_times.getStop_id());
                }
            }

        }

        for (String stopIds : stop_Ids) {
            listOfStops += stopIds + ", ";
        }
        message = "These are the stops available:\n"
                + listOfStops;

        return message;

    }

    /**
     * Created by Derek Richards.
     * Searches for the trip id of the trip
     * with the closest arrival time at a given stop.
     *
     * @param userStopId The stop id the users wants to search by.
     * @return The trip id with the closest arrival time to the current time.
     */
    public String searchForTripByStop(String userStopId) {

        // Adding the stops and stop times to a
        // lists of stops and stop times instead of transit data sets.
        List<TransitDataSets> transitStopList = dataList.getStopsList();
        List<TransitDataSets> transitStopTimesList = dataList.getStopTimesList();
        List<Stop> stopList = createStopList(transitStopList);
        List<Stop_Times> stopTimesList = createStopTimesList(transitStopTimesList);

        // Find a stop that matches the stop ID
        Stop matchingStop = null;
        for (Stop stop : stopList) {
            if (matchingStop == null && stop.getStop_id().equals(userStopId)) {
                matchingStop = stop;
            }
        }

        // Find stopTimes that involve the matching stop
        List<Stop_Times> matchingStopTimes = new ArrayList<>();
        for (Stop_Times stopTime : stopTimesList) {
            if (matchingStop != null &&
                    stopTime.getStop_id().equals(matchingStop.getStop_id())) {
                matchingStopTimes.add(stopTime);
            }
        }

        // Find the trip ID for the next trip that goes by the stop.
        String matchingTripId = "";
        LocalTime currentTime = LocalTime.now();
        long minDifferenceOfSeconds = Integer.MAX_VALUE;
        final int hoursInADay = 24;

        if (matchingStopTimes.isEmpty()) {
            Alert noMatchingTimes = new Alert(Alert.AlertType.WARNING,
                    "There do not seem to be any matching stop times for the desired stop.");
            noMatchingTimes.setTitle("No Stop Times Found");
            noMatchingTimes.setHeaderText("No Stop Times Found");
            noMatchingTimes.show();
        } else {
            for (Stop_Times stopTime : matchingStopTimes) {
                // Use two LocalTime objects and a Duration object to calculate difference in time.
                String arrivalTime = stopTime.getArrival_time();
                int hours = Integer.parseInt(arrivalTime.substring(0, 2));
                int minutes = Integer.parseInt(arrivalTime.substring(3, 5));
                int seconds = Integer.parseInt(arrivalTime.substring(6, 8));

                if (hours >= hoursInADay) {
                    hours -= hoursInADay;
                }

                LocalTime timeOfArrival = LocalTime.of(hours, minutes, seconds);
                Duration duration = Duration.between(currentTime, timeOfArrival);

                if (duration.getSeconds() < minDifferenceOfSeconds) {
                    minDifferenceOfSeconds = duration.getSeconds();
                    matchingTripId = stopTime.getTrip_id();
                }
            }
        }

        return matchingTripId;
    }

    public void update() {

    }

    /**
     * Created by Derek Richards
     * Creates a list of stops from a list of transit data sets.
     *
     * @param transitStopList A list of transit data sets containing stops.
     * @return A list of stops.
     */
    private List<Stop> createStopList(List<TransitDataSets> transitStopList) {
        List<Stop> stopList = new ArrayList<>();
        for (TransitDataSets stop : transitStopList) {
            if (stop.getId() == 0) {
                stopList.add((Stop) stop);
            }
        }
        return stopList;
    }

    /**
     * Created by Derek Richards
     * Creates a list of stop times from a list of transit data sets.
     *
     * @param transitStopTimesList A list of transit data sets containing stop times.
     * @return A list of stop times
     */
    private List<Stop_Times> createStopTimesList(List<TransitDataSets> transitStopTimesList) {
        List<Stop_Times> stopTimesList = new ArrayList<>();
        for (TransitDataSets stopTime : transitStopTimesList) {
            if (stopTime.getId() == 1) {
                stopTimesList.add((Stop_Times) stopTime);
            }
        }
        return stopTimesList;
    }

    /**
     * Created by Derek Richards
     * Creates a list of routes from a list of transit data sets.
     *
     * @param transitRouteList A list of transit data sets containing routes.
     * @return A list of routes.
     */
    private List<Route> createRouteList(List<TransitDataSets> transitRouteList) {
        List<Route> routeList = new ArrayList<>();
        for (TransitDataSets route : transitRouteList) {
            if (route.getId() == 3) {
                routeList.add((Route) route);
            }
        }
        return routeList;
    }

    /**
     * Created by Derek Richards
     * Creates a list of trips from a list of transit data sets.
     *
     * @param transitTripList A list of transit data sets containing trips.
     * @return A list of trips.
     */
    private List<Trip> createTripList(List<TransitDataSets> transitTripList) {
        List<Trip> tripList = new ArrayList<>();
        for (TransitDataSets trip : transitTripList) {
            if (trip.getId() == 2) {
                tripList.add((Trip) trip);
            }
        }
        return tripList;
    }


}