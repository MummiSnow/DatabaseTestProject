package PictureService;

import java.net.URL;

/**
 * Created by MJPS on 25/05/2017.
 */
public interface IUrlFetcher {
	
	Place createUrl(String lat, String lng, int zoom, int size);
	String getUrl();
	
}
