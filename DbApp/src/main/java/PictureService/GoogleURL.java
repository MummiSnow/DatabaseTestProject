package PictureService;

import java.io.*;
/**
 * Created by MJPS on 25/05/2017.
 */
public class GoogleURL implements IUrlFetcher {
	
	//URLS
	private final String STATIC_API_BASE = "https://maps.googleapis.com/maps/api";
	private final String TYPE_STATIC_MAP = "/staticmap?";
	private final String API_KEY = "AIzaSyCSrSediuHzqqIbZC5JUvAEzEjiP9FDd8c";
	
	//Dependency injection for constructor
	private StringBuilder sb;
	
	private String lat;
	private String lng;
	private int zoom;
	private int size;
	private String url;
	private void setUrl(String url) {
		this.url = url;
	}
	public String getUrl(){return url;}
	
	
	public GoogleURL(StringBuilder sb) {
		this.sb = sb;
	}
	
	/**
	 * @param lat, latitude
	 * @param lng, longitude
	 * @param zoom, the amount of detail needed:
	 *              1 = World
	 *              5 = Landmass/continent
	 *              10 = City
	 *              15 = Streets
	 *              20 = Buildings
	 * @param size, of the picture 640x640 is max for free.
	 * */
	public Place createUrl(String lat, String lng, int zoom, int size) {
		if ((lat.length() != 0 || lat != "") && ( lng.length() != 0  || lng != "")) {
			this.lat = lat;
			this.lng = lng;
		}
		if (zoom != 0 || zoom <= 20) {
			this.zoom = zoom;
		}
		this.size = size;
		sb.append(STATIC_API_BASE);
		sb.append(TYPE_STATIC_MAP);
		sb.append("center="+lat+","+lng);
		sb.append("&zoom="+String.valueOf(zoom));
		sb.append("&size="+size+"x"+size);
		sb.append("&key=" + API_KEY);
		setUrl(sb.toString());
		
		return new Place(lat,lng,zoom,size);
	}
	
	
}
