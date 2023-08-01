import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Scanner;
import java.io.IOException;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TimeZone;

public class WeatherProgram {

    private static final String BASE_URL = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";

    private static String getGivenDateWeatherTemp(String user_date) {
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();

            // Checking status code and building the response
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                // Converting string response to JSONObject
                JSONObject jsonObject = new JSONObject(response.toString());
                // Getting list of hourly weather information from jsonObject
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                // Iterate jsonArray using for loop
                for (int i = 0; i < jsonArray.length(); i++) {
                    // store each object in JSONObject
                    JSONObject explrObject = jsonArray.getJSONObject(i);
                    // get field value from JSONObject using get() method
                    long unix_seconds= Long.valueOf((explrObject.get("dt").toString()));
                    Date date = new Date(unix_seconds*1000L);
                    // format of the date as per GMT timezone
                    SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    jdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                    String unix_date = jdf.format(date);
                    // Comparing the given user date in the data, if date is found and valid then returning the temperature for given date
                    if(unix_date.equals(user_date)){
                        JSONObject main= (JSONObject) explrObject.get("main");
                        return  String.valueOf(main.get("temp"));
                    }
                }

            } else {
                System.out.println("Failed to fetch weather data for date: " + user_date);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getWindSpeed(String user_date) {
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            // Checking status code and building the response
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                // Converting string response to JSONObject
                JSONObject jsonObject = new JSONObject(response.toString());
                // Getting list of hourly weather information from jsonObject
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                // Iterate jsonArray using for loop
                for (int i = 0; i < jsonArray.length(); i++) {
                    // store each object in JSONObject
                    JSONObject explrObject = jsonArray.getJSONObject(i);
                    // get field value from JSONObject using get() method
                    long unix_seconds= Long.valueOf((explrObject.get("dt").toString()));
                    Date date = new Date(unix_seconds*1000L);
                    // format of the date as per GMT timezone
                    SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    jdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                    String unix_date = jdf.format(date);
                    // Comparing the given user date in the data, if date is found and valid then returning the wind speed for given date
                    if(unix_date.equals(user_date)){
                        JSONObject wind= (JSONObject) explrObject.get("wind");
                        return  String.valueOf(wind.get("speed"));
                    }
                }
            }
            else {
                System.out.println("Failed to fetch weather data for date: " + user_date);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    private static String getPressure(String user_date) {
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            // Checking status code and building the response
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                // Converting string response to JSONObject
                JSONObject jsonObject = new JSONObject(response.toString());
                // Getting list of hourly weather information from jsonObject
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                // Iterate jsonArray using for loop
                for (int i = 0; i < jsonArray.length(); i++) {
                    // store each object in JSONObject
                    JSONObject explrObject = jsonArray.getJSONObject(i);
                    // get field value from JSONObject using get() method
                    long unix_seconds= Long.valueOf((explrObject.get("dt").toString()));
                    Date date = new Date(unix_seconds*1000L);
                    // format of the date as per GMT timezone
                    SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    jdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                    String unix_date = jdf.format(date);
                    // Comparing the given user date in the data, if date is found and valid then returning the pressure for given date
                    if(unix_date.equals(user_date)){
                        JSONObject main= (JSONObject) explrObject.get("main");
                        return  String.valueOf(main.get("pressure"));
                    }
                }

            }
            else {
                System.out.println("Failed to fetch weather data for date: " + user_date);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Get weather");
            System.out.println("2. Get Wind Speed");
            System.out.println("3. Get Pressure");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter the date (YYYY-MM-DD HH:MM:SS): ");
                    String dateWeather = scanner.nextLine();
                    String weather = getGivenDateWeatherTemp(dateWeather);
                    System.out.println("weather "+weather);
                    if (weather != null) {
                        System.out.println("The temperature on " + dateWeather + " was " + weather + "°F.");
                    }
                    else{
                        System.out.println("Given date is not found. Please enter a valid date (YYYY-MM-DD HH:MM:SS)");
                    }
                    break;
                case "2":
                    System.out.print("Enter the date (YYYY-MM-DD HH:MM:SS): ");
                    String dateWindSpeed = scanner.nextLine();
                    String windSpeed = getWindSpeed(dateWindSpeed);
                    if (windSpeed != null) {
                        System.out.println("The wind speed on " + dateWindSpeed + " was " + windSpeed + " kph.");
                    }
                    else{
                        System.out.println("Given date is not found. Please enter a valid date (YYYY-MM-DD HH:MM:SS)");
                    }
                    break;
                case "3":
                    System.out.print("Enter the date (YYYY-MM-DD HH:MM:SS): ");
                    String datePressure = scanner.nextLine();
                    String pressure = getPressure(datePressure);
                    if (pressure != null) {
                        System.out.println("The pressure on " + datePressure + " was " + pressure + " mb.");
                    }
                    else{
                        System.out.println("Given date is not found. Please enter a valid date (YYYY-MM-DD HH:MM:SS)");
                    }
                    break;
                case "0":
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
