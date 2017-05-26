package PictureService;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.lang.reflect.MalformedParametersException;
import java.net.MalformedURLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Created by MJPS on 26/05/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class PlaceTest {
	
	private static String googleUrl = "https://maps.googleapis.com/maps/api/staticmap?center=36.57288,69.85783&zoom=16&size=640x640&key=";
	private static String apiKey = "AIzaSyCSrSediuHzqqIbZC5JUvAEzEjiP9FDd8c";
	
	//@Mock
	IUrlFetcher fetcher;
	//@Mock
	IService service;
	
	Place place;
	Place placeWithNoParams;
	
	@Before
	public void setUp() throws Exception {
		//Alternate way of Mocking
		fetcher = mock(GoogleURL.class);
		service = mock(PictureService.class);
		
		place = new Place(service,fetcher,"36.57288","69.85783",16,640);
		//placeWithNoParams = new Place(service,fetcher,anyString(),anyString(),anyInt(),anyInt());
		
		place.getPicture(place.getLat()+" "+place.getLng());
	}
	@Test
	public void testGetLatitude() throws Exception {
		String placeLat = place.getLat();
		String lat = placeLat;
		
		assertEquals("36.57288", lat);
		assertThat("36.57288",is(equalTo(lat)));
	}
	
	@Test
	public void testGetLongitude() throws Exception {
		String placeLng = place.getLng();
		String lng = placeLng;
		
		assertEquals("69.85783", lng);
		assertThat("69.85783",is(equalTo(lng)));
	}
	
	@Test
	public void testGetZoomTest() throws Exception {
		int piczoom = place.getZoom();
		int zoom = piczoom;
		
		assertEquals(16, zoom);
		assertThat(16,is(equalTo(16)));
	}
	
	@Test
	public void getSize() throws Exception {
		int picSize = place.getSize();
		int size = picSize;
		
		assertEquals(640, size);
		assertThat(640,is(equalTo(640)));
	}
	
	@Test
	public void getName() throws Exception {
		String placeName = place.getName();
		String name = placeName;
		
		assertEquals(null, name);
		assertThat(name,is(nullValue()));
	}
	
	@Test
	public void testGetUrlMethodFromFetcher() throws Exception {
		String expectedStr = googleUrl+apiKey;
		given(fetcher.getUrl()).willReturn(expectedStr);
		when(fetcher.getUrl()).thenReturn(expectedStr);
	}
	
	@Test
	public void testThatSavePictureFromUrlIsCalledOnce() throws IOException {
		verify(service, times(1)).savePictureFromUrl(anyObject(),anyObject());
	}
	@Test
	public void testThatCreateUrlIsCalledOnce() throws IOException {
		verify(fetcher, times(1)).createUrl("36.57288","69.85783",16,640);
		verify(fetcher, times(1)).createUrl(anyString(), anyString(), anyInt(), anyInt());
	}
	
	@Test
	public void testThatFetcherGetUrlPropertyIsCalledOnce() {
		verify(fetcher, times(1)).getUrl();
	}
	
	
	@Test
	public void testThatSavePictureIsCalledOnce() throws IOException {
		verify(service, times(1)).savePictureFromUrl(anyString(),anyString());
	}
	
	
	@Test
	public void testThrowsIOExceptionIfDestinationNameIsADirectory() {
		try {
			doThrow(new IOException()).when(service).savePictureFromUrl(anyString(),eq("src"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testThrowsMalformedURLExceptionIfURLIsNotCreatedSuccessfully() {
		try {
			doThrow(new MalformedURLException()).when(service).savePictureFromUrl(eq("rr"),anyString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = NullPointerException.class)
	public void testThrowNullPointerExceptionWhenUsingWrongConstructor() {
		Place placeFail = new Place("36.57288","69.85783",16,640);
		try {
			placeFail.getPicture(placeFail.getLat()+" "+placeFail.getLng());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}