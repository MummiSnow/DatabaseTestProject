package PictureService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by MJPS on 25/05/2017.
 */
public interface IService {
    
    void savePictureFromUrl(String url, String destionationFile) throws IOException;
    
}
