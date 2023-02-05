package group2;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


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
public class GUI implements Observer {

    @FXML
    TextArea pane;
    @FXML
    TextField textField;
    @FXML
    Button searchButton;
    @FXML
    Button numTripsPerStopsButton;
    @FXML
    ListView<String> routesListView;
    @FXML
    ListView<String> tripsListView;
    @FXML
    ListView<String> stopTimesListView;
    @FXML
    ListView<String> stopsListView;
    //What type is it?
    @FXML
    ListView<String> numStopsPerTripListView;
    @FXML
    Label routesLabel;
    @FXML
    Label tripsLabel;
    @FXML
    Label stopTimesLabel;
    @FXML
    Label stopsLabel;
    @FXML
    ChoiceBox SearchType;
    @FXML
    TextField itemToSearchBy;
    @FXML
    TextArea searchAnswer;


    public Controller m_Controller;

    private final ObservableList<String> items = FXCollections.observableArrayList("Search by stop for routes",
            "Search by stop for next trip", "Calculate trip distance", "Number of trips on Stop",
            "Search by route for trips", "Search by route for stops");

    private List<File> fileList = new ArrayList<>();

    /**
     * puts in some initial information for the GUI
     */
    public void initialize() {
        SearchType.setItems(items);
    }


    public GUI() {
        m_Controller = new Controller();
        Controller.attachObserver(this);
    }

    public void finalize() throws Throwable {

    }

    /**
     * Author: Tyler Cernik
     * The search button method
     * supports all different search options based on what the user inputs
     */
    public void search() {
        String searchOption = String.valueOf(SearchType.getValue());
        if (searchOption.equals("Search by stop for routes")) {
            String routesAnswer = m_Controller.searchByStop(itemToSearchBy.getText());
            searchAnswer.setText(routesAnswer);
        } else if (searchOption.equals("Search by stop for next trip")) {
            String matchingTripId = SearchByStop(itemToSearchBy.getText());
            searchAnswer.setText(matchingTripId);
        } else if (searchOption.equals("Calculate trip distance")) {
            String tripDistance = displayDistance(itemToSearchBy.getText());
            searchAnswer.setText(tripDistance);
        } else if (searchOption.equals("Number of trips on Stop")) {
            String numTripsPerStop = displayNumTripsPerStop(itemToSearchBy.getText());
            searchAnswer.setText(numTripsPerStop);
        } else if (searchOption.equals("Search by route for trips")) {
            String answer = displayTripByRoute(itemToSearchBy.getText());

            searchAnswer.setText("Trips " + answer + "\nare on route " + itemToSearchBy.getText());

        } else if (searchOption.equals("Search by route for stops")) {
            String stops = m_Controller.searchByRoute(itemToSearchBy.getText());
            stops = stops.substring(0,stops.length()-2);
            searchAnswer.setText(stops);
        }
    }

    public boolean alterStopLocation() {
        return false;
    }

    public void alterTripStopTimes() {

    }

    /**
     * Author: Tyler Cernik
     * returns the distance of a trip to be displayed
     * Uses the controller calculate distance method for business logic
     * @param tripId the trip that we want to find the length of
     * @return the distance of the trip in a user friendly way
     */
    public String displayDistance(String tripId) {
        try {
            double distance = m_Controller.calculateDistance(tripId);
            String theDistance = String.format("%,.2f", distance);
            return "The distance of the trip\nis " + theDistance + " miles.";
        } catch (NoSuchElementException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Invalid trip_id");
            a.setContentText("Inputted trip_id is invalid");
            a.showAndWait();
        }
        return "The distance of the trip\nis " + -1 + " miles.";
    }


    /**
     * displays the trips on a route
     * @param route_id the route we are searching by
     * @return the trips that are on the route
     */
    public String displayTripByRoute(String route_id) {
        try {
            List<String> listOfTripId = m_Controller.searchForTripByRoute(route_id);
            String answer = "";
            for(String tripId: listOfTripId) {
                answer += tripId + ", ";
            }
            return answer.substring(0,answer.length()-2);
        } catch (IndexOutOfBoundsException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Invalid trip_id");
            a.setContentText("Inputted trip_id is invalid");
            a.showAndWait();
        }
        return "-1";
    }


    ObservableList<String> obNumTripsPerStops = FXCollections.observableArrayList();

    /**
     * finds the number of trips a stop is on
     * @param stop_id the stop we are searching with
     * @return the number of trips
     */
    public String displayNumTripsPerStop(String stop_id) {
        /*List<TransitDataSets> transitStopList = m_Controller.dataList.getStopsList();
        List<TransitDataSets> transitStopTimesList = m_Controller.dataList.getStopTimesList();
        List<Stop> stopList = createStopList(transitStopList);
        List<Stop_Times> stopTimesList = createStopTimesList(transitStopTimesList);
        obNumTripsPerStops.clear();

        for (Stop stop : stopList) {
            String currentTrip = "";
            int numTrips = 0;

            for (Stop_Times stopTime : stopTimesList) {
                if (stopTime.getStop_id().equals(stop.getStop_id()) &&
                        !stopTime.getTrip_id().equals(currentTrip)) {
                    numTrips++;
                    currentTrip = stopTime.getTrip_id();
                }
            }
            obNumTripsPerStops.add(stop.getStop_name() + " appears on " + numTrips + " trips.");
        }
        numStopsPerTripListView.getItems().addAll(obNumTripsPerStops);*/
        try {
            int numTripsPerStop = m_Controller.calculateNumTripsPerStops(stop_id);
            return "There are " + numTripsPerStop + " trips\non " + stop_id ;
        } catch(NumberFormatException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Invalid trip_id");
            a.setContentText("Inputted trip_id is invalid");
            a.showAndWait();
        }
        return "There are " + -1 + " trips\non " + stop_id ;
    }


    public void displayPlot() {


    }

    public void displaySpeed() {

    }


    /**
     * Author: Tyler Cernik
     * creates the directory chooser for export, and is the GUI half of the export method
     */
    public void export() {
        DirectoryChooser dirChoose = new DirectoryChooser();
        dirChoose.setTitle("Select Folder to export files");
        dirChoose.setInitialDirectory(new File(System.getProperty("user.dir")));
        File selectedDirectory = dirChoose.showDialog(new Stage());

        try {
            m_Controller.export(selectedDirectory.getAbsolutePath());
            confirm.setTitle("Export");
            confirm.setContentText("Files successfully exported");
            confirm.showAndWait();

        } catch (IOException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Export Failed");
            a.setHeaderText("IO Exception");
            a.setContentText("An IO exception has occurred.");
            a.showAndWait();
        }


    }


    /**
     * Author: Tyler Cernik
     * loads the files from the user into the database. Uses the controller import method for business logic
     * @return if the files were imported properly
     */
    public boolean importFile() {

        try {
            loaded = m_Controller.importFiles(fileList);
            tripsLabel.setTextFill(Color.web("#000000"));
            routesLabel.setTextFill(Color.web("#000000"));
            stopTimesLabel.setTextFill(Color.web("#000000"));
            stopsLabel.setTextFill(Color.web("#000000"));

            if(stopsListView.getItems().size() != 0 && stopTimesListView.getItems().size() != 0 &&
                    tripsListView.getItems().size() != 0 && routesListView.getItems().size() != 0) {
                searchButton.setDisable(false);
            }
            if (loaded) {
                fileList.clear();
                confirm.setTitle("Confirmation Dialog");
                confirm.setContentText("All  files have been successfully loaded");
                confirm.showAndWait();
            } else {
                notLoaded.setTitle("Error Dialog");
                notLoaded.setContentText("Failed to load files");
                notLoaded.showAndWait();
            }

        } catch (FileNotFoundException exception) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Import Failed");
            a.setHeaderText("File Not Found Exception");
            a.setContentText("A file you selected could not be found.");
            a.showAndWait();
            return false;
        } catch (NullPointerException exception) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Import Failed");
            a.setHeaderText("No file was selected");
            a.setContentText("Please try again.");
            a.showAndWait();
            return false;
        }
        return true;
    }


    /**
     * Created by Derek Richards
     * Uses the Controller's searchForTripByStop method to
     * search for a trip id for a given stop.
     * @param userStopId The id for the stop that the user wants to search by.
     * @return The trip id with the closest arrival time to the current time.
     */
    public String SearchByStop(String userStopId) {
        return m_Controller.searchForTripByStop(userStopId);
    }

    File file;

    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
    Alert notLoaded = new Alert(Alert.AlertType.ERROR);
    boolean loaded;

    /**
     * Author: Tyler Cernik and Cherise Malisa
     * selects a single file to be loaded
     *
     */
    @FXML
    public void loadFiles() {

        String fileName = "";

        FileChooser fileChooser = new FileChooser();
        File initialD = Paths.get(System.getProperty("user.dir"), "GTFS").toFile();

        if (!initialD.exists()) {
            initialD.mkdir();
        }

        fileChooser.setTitle("File Chooser");
        fileChooser.setInitialDirectory(initialD);
        file = fileChooser.showOpenDialog(new Stage());

        try {
            fileName = file.getName();
            if (fileName.toLowerCase().contains("routes")) {
                fileList.add(file);
                routesLabel.setTextFill(Color.web("#00FF00"));
            } else if (fileName.toLowerCase().contains("trips")) {
                fileList.add(file);
                tripsLabel.setTextFill(Color.web("#00FF00"));
            } else if (fileName.toLowerCase().contains("stop_times")) {
                fileList.add(file);
                stopTimesLabel.setTextFill(Color.web("#00FF00"));
            } else if (fileName.toLowerCase().contains("stops")) {
                fileList.add(file);
                stopsLabel.setTextFill(Color.web("#00FF00"));
            }
        } catch (NullPointerException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("File Selection");
            a.setHeaderText("No File was selected");
            a.setContentText("Please select a file.");
            a.showAndWait();
        }


    }

    /**
     * Author: Tyler Cernik and Cherise Malisa
     * selects all files to be loaded
     */
    @FXML
    public void loadAllFiles() {

        FileChooser fileChooser = new FileChooser();
        File initialD = Paths.get(System.getProperty("user.dir"), "GTFS").toFile();
        String fileName = " ";

        for (int x = 0; x < 4; x++) {

            if (!initialD.exists()) {
                initialD.mkdir();
            }

            fileChooser.setTitle("File Chooser");
            fileChooser.setInitialDirectory(initialD);
            file = fileChooser.showOpenDialog(new Stage());
            try {
                fileName = file.getName();
                if (fileName.toLowerCase().contains("routes")) {
                    fileList.add(file);
                    routesLabel.setTextFill(Color.web("#00FF00"));
                } else if (fileName.toLowerCase().contains("trips")) {
                    fileList.add(file);
                    tripsLabel.setTextFill(Color.web("#00FF00"));
                } else if (fileName.toLowerCase().contains("stop_times")) {
                    fileList.add(file);
                    stopTimesLabel.setTextFill(Color.web("#00FF00"));
                } else if (fileName.toLowerCase().contains("stops")) {
                    fileList.add(file);
                    stopsLabel.setTextFill(Color.web("#00FF00"));
                }
            } catch (NullPointerException e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("File Selection");
                a.setHeaderText("No File was selected");
                a.setContentText("Please select a file.");
                a.showAndWait();
            }
        }


    }


    @Override
    /**
     * Author: Tyler Cernik
     * Observer method
     * updates the displayed information to current info
     */
    public void updateGUI(List<TransitDataSets> list) {
        displayTransitDataSet(list);

    }

    ObservableList<String> obList = FXCollections.observableArrayList();


    /**
     * Author: Tyler Cernik
     * method that displays a list
     * @param list the list that is updated and being displayed
     */
    private void displayTransitDataSet(List<TransitDataSets> list) {
        //displayRoutes();
        //obStopsList.removeAll(obStopsList);
        obList.clear();
        try {
            for (TransitDataSets item : list) {
                obList.add(item.toString());
            }
            if (list.get(0).getId() == 0) {
                stopsListView.getItems().clear();
                stopsListView.getItems().setAll(obList);
            } else if (list.get(0).getId() == 1) {
                stopTimesListView.getItems().clear();
                stopTimesListView.getItems().setAll(obList);
            } else if (list.get(0).getId() == 2) {
                tripsListView.getItems().clear();
                tripsListView.getItems().setAll(obList);
            } else if (list.get(0).getId() == 3) {
                routesListView.getItems().clear();
                routesListView.getItems().setAll(obList);
            }
        } catch (IndexOutOfBoundsException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("File Selection");
            a.setHeaderText("Failed to import a file");
            a.setContentText("Please reselect files");
            a.showAndWait();
        }
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
    public List<Trip> createTripList(List<TransitDataSets> transitTripList) {
        List<Trip> tripList = new ArrayList<>();
        for (TransitDataSets trip : transitTripList) {
            if (trip.getId() == 2) {
                tripList.add((Trip) trip);
            }
        }
        return tripList;
    }


}