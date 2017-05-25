package PictureService;

import java.io.IOException;

/**
 * Created by MJPS on 23/05/2017.
 */
public class Place {
    
    
    private IUrlFetcher fetcher;
    private IService service;
    
    
    private String reference;
    private String name;
    private String formattedAddress;
    private String lat;
    private String lng;
    private int zoom;
    private int size;
    
    //Program Specific
    public String getLat() {
        return lat;
    }
    
    public String getLng() {
        return lng;
    }
    
    public int getZoom() {
        return zoom;
    }
    
    public int getSize() {
        return size;
    }
    
    
    //Google Specific Getters/Setters
    public String getFormattedAddress() {
        return formattedAddress;
    }
    
    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }
    
    public String getReference() {
        return reference;
    }
    
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    
    public Place(IService service, IUrlFetcher fetcher) {
        this.service = service;
        this.fetcher = fetcher;
    }
    
    public Place(String lat, String lng, int zoom, int size) {
        this.lat = lat;
        this.lng = lng;
        this.zoom = zoom;
        this.size = size;
    }
    
    public Place getPicture(String nameOfFile) throws IOException {
        Place place = fetcher.createUrl(lat, lng,zoom,size);
        service.savePictureFromUrl(fetcher.getUrl(),nameOfFile);
        return place;
        
    }
    
    

    @Override
    public String toString() {
        return "Place{" +
                "reference='" + reference + '\'' +
                ", formattedAddress='" + formattedAddress + '\'' +
                '}';
    }
}
