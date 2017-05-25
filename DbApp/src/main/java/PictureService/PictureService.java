package PictureService;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by MJPS on 23/05/2017.
 */
public class PictureService implements IService {
    
    private InputStream inputStream;
    private OutputStream outputStream;
    
    @Override
    public void savePictureFromUrl(String url, String destionationFile) throws IOException {
        
        URL picurl = new URL(url);
        inputStream = picurl.openStream();
        outputStream = new FileOutputStream(destionationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = inputStream.read(b)) != -1) {
            outputStream.write(b, 0, length);
        }

        inputStream.close();
        outputStream.close();
        
    
    }
    
    
    
    
    
    
    //<editor-fold desc="reverseGeocode">
    /**
     * Returns an arraylist of places that is based on latitude and longitude and reverse geocode it
     * @param lat, latitude
     * @param lng, longitude
     *
     * @return {@link ArrayList<Place>}
     * */
    /*public ArrayList<Place> reverseGeocode(String lat, String lng) {

        //a seperate stringbuilder to hold values from json
        StringBuilder results = new StringBuilder();


        try {

            sb.append(GEOCODE_API_BASE);
            sb.append(TYPE_RGEOCODE);
            sb.append(OUT_JSON);
            sb.append("&latlng=" + lat + "," + lng);
            sb.append("&key=" + API_KEY);

            setUrl(sb.toString());
            connection = (HttpURLConnection) getUrl().openConnection();

            in = new InputStreamReader(connection.getInputStream());

            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                results.append(buff, 0, read);
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
        } catch (MalformedURLException e) {
            System.out.println("Error processing places API URL");
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("Error Connection to Places API");
            System.out.println(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        ArrayList<Place> resultList = null;
        Place place;
        try {
            //Creating JSON object hierarchy
            JSONObject jsObject = new JSONObject(results.toString());
            JSONArray resultsArr = jsObject.getJSONArray("results");

            //extract the Place Descriptions
            resultList = new ArrayList<>(resultsArr.length());

            for (int i = 0; i < resultsArr.length(); i++) {
                place = new Place();
                place.setFormattedAddress(resultsArr.getJSONObject(i).getString("formatted_address"));
                place.setReference(resultsArr.getJSONObject(i).getString("place_id"));
                resultList.add(place);
            }
        } catch (JSONException e) {
            System.out.println("Error processing JSON results");
            System.out.println(e);
        }


        return resultList;

    }*/
    //</editor-fold>
    //<editor-fold desc="search">
    /*public ArrayList<Place> search(String keyword, String lat, String lng, int radius) {
        HttpURLConnection connection = null;
        StringBuilder results = new StringBuilder();


        try {

            sb.append(PLACES_API_BASE);
            sb.append(TYPE_SEARCH);
            sb.append(OUT_JSON);
            sb.append("&location=" + lat + "," + lng);
            sb.append("&radius=" + String.valueOf(radius));
            sb.append("&keyword=" + URLEncoder.encode(keyword, "utf8"));
            sb.append("&key=" + API_KEY);

            setUrl(sb.toString());
            connection = (HttpURLConnection) url.openConnection();

            in = new InputStreamReader(connection.getInputStream());

            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                results.append(buff, 0, read);
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
        } catch (MalformedURLException e) {
            System.out.println("Error processing places API URL");
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("Error Connection to Places API");
            System.out.println(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        ArrayList<Place> resultList = null;
        try {
            //Creating JSON object hierarchy
            JSONObject jsObject = new JSONObject(results.toString());
            JSONArray resultsArr = jsObject.getJSONArray("results");

            //extract the Place Descriptions
            resultList = new ArrayList<>(resultsArr.length());

            for (int i = 0; i < resultsArr.length(); i++) {
                Place place = new Place();
                place.setReference(resultsArr.getJSONObject(i).getString("reference"));
                place.setName(resultsArr.getJSONObject(i).getString("name"));
                resultList.add(place);
            }
        } catch (JSONException e) {
            System.out.println("Error processing JSON results");
            System.out.println(e);
        }


        return resultList;

    }*/
    //</editor-fold>
    //<editor-fold desc="details">
    /*public Place details(String reference) {

        StringBuilder results = new StringBuilder();
        try {

            sb.append(PLACES_API_BASE);
            sb.append(TYPE_DETAILS);
            sb.append(OUT_JSON);
            sb.append("?sensor=false");
            sb.append("&key=" + API_KEY);
            sb.append("&reference=" + URLEncoder.encode(reference, "utf8"));

            setUrl(sb.toString());
            connection = (HttpURLConnection) url.openConnection();

            in = new InputStreamReader(connection.getInputStream());

            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                results.append(buff, 0, read);
            }

        } catch (UnsupportedEncodingException e) {
            System.out.println();
        } catch (MalformedURLException e) {
            System.out.println("Error processing places API URL");
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("Error Connection to Places API");
            System.out.println(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        Place place = null;

        try {
            //Creating JSON object hierarchy
            JSONObject jsObject = new JSONObject(results.toString()).getJSONObject("result");
            //extract data
            place = new Place();
            place.setName(jsObject.getString("name"));
            place.setFormattedAddress(jsObject.getString("formatted_address"));

        } catch (JSONException e) {
            System.out.println("Error processing JSON results");
            System.out.println(e);
        }

        return place;

    }*/
    //</editor-fold>
    
    
    

}
