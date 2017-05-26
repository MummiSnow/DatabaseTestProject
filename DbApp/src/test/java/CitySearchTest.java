import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import exceptions.InputException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.neo4j.driver.v1.exceptions.ServiceUnavailableException;
import workers.CitySearch;
import workers.IWorker;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CitySearchTest {

    @Mock IWorker mockedWorker;
    IWorker worker = null;

    //region Black box input tests


    @Ignore("Test fails due to problems with external mongo disallowing all\n incoming requests from other IP's than loopback")
    @Test
    public void CorrectUse() throws InputException, UnknownHostException {
        worker = new CitySearch("FinalDatabaseProject",
                "Books",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),7687),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:27017")));
    }

    @Test(expected = InputException.class)
    public void testNumbers() throws InputException, UnknownHostException {
        worker = new CitySearch("abc123",
                "abc",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),7687),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:27017")));
    }

    @Test(expected=InputException.class)
    public void dbNameSpecialChar() throws InputException, UnknownHostException {

        worker = new CitySearch("å",
                "abc",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),7687),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:27017")));

    }

    @Test(expected=InputException.class)
    public void collectionSpecialChar() throws InputException, UnknownHostException {

        worker = new CitySearch("abc",
                "å",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),7687),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:27017")));
    }

    @Test(expected = NullPointerException.class)
    public void dbNameNullValue() throws NullPointerException, InputException, UnknownHostException {
        worker = new CitySearch(null,
                "abc",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),7687),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:27017")));
    }
    @Test(expected = NullPointerException.class)
    public void collectionNameNullValue() throws NullPointerException, InputException, UnknownHostException {
        worker = new CitySearch("abc",
                null,
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),7687),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:27017")));
    }


    @Test(expected = InputException.class)
    public void dbNameEmptyValue() throws InputException, UnknownHostException {
        worker = new CitySearch("",
                "abc",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),7687),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:27017")));
    }
    @Test(expected = InputException.class)
    public void collectionNameEmptyValue() throws InputException, UnknownHostException {
        worker = new CitySearch("abc",
                "",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),7687),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:27017")));
    }


    @Test(expected = InputException.class)
    public void dbNameWSValue() throws InputException, UnknownHostException {
        worker = new CitySearch("    ",
                "abc",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),7687),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:27017")));
    }
    @Test(expected = InputException.class)
    public void collectionNameWSValue() throws InputException, UnknownHostException {
        worker = new CitySearch("abc",
                "    ",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),7687),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:27017")));
    }
    @Test(expected = ServiceUnavailableException.class)
    public void WrongIpNeo4J() throws InputException, UnknownHostException {

        worker = new CitySearch("FinalDatabaseProject",
                "Books",
                new InetSocketAddress(InetAddress.getByName("16.227.128.49"),7687),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:27017")));
    }

    @Test(expected = ServiceUnavailableException.class)
    public void WrongPortNeo4J() throws InputException, UnknownHostException {

        worker = new CitySearch("FinalDatabaseProject",
                "Books",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),7474),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:27017")));
    }

    @Test(expected = UnknownHostException.class)
    public void NonExistentIPNeo4J() throws InputException, UnknownHostException {

        worker = new CitySearch("FinalDatabaseProject",
                "Books",
                new InetSocketAddress(InetAddress.getByName("256.256.256.256"),7687),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:27017")));
    }
    @Test(expected = IllegalArgumentException.class)
    public void NonExistentPortNeo4J() throws InputException, UnknownHostException {

        worker = new CitySearch("FinalDatabaseProject",
                "Books",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),66000),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:27017")));
    }


    @Test(expected = NullPointerException.class)
    public void NullNeo4jValue() throws InputException, UnknownHostException {

        worker = new CitySearch("FinalDatabaseProject",
                "Books",
                null,
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:27017")));
    }

    @Ignore("Servers wen't down on May 25th.")
    @Test(expected = NullPointerException.class)
    public void NullMongoClient() throws InputException, UnknownHostException {

        worker = new CitySearch("FinalDatabaseProject",
                "Books",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),7687),
                null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Neo4jEmptyIp() throws InputException, UnknownHostException {

        worker = new CitySearch("FinalDatabaseProject",
                "Books",
                new InetSocketAddress(InetAddress.getByName(""),7687),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:27017")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void Neo4jPortNegative5() throws InputException, UnknownHostException {

        worker = new CitySearch("FinalDatabaseProject",
                "Books",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),-5),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:27017")));
    }
    @Test(expected = IllegalArgumentException.class)
    public void Neo4jPortNegative1() throws InputException, UnknownHostException {

        worker = new CitySearch("FinalDatabaseProject",
                "Books",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),-1),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:27017")));
    }
    @Test(expected = ServiceUnavailableException.class)
    public void Neo4jPort0() throws InputException, UnknownHostException {

        worker = new CitySearch("FinalDatabaseProject",
                "Books",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),0),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:27017")));
    }
    @Test(expected = ServiceUnavailableException.class)
    public void Neo4jPortPositive1() throws InputException, UnknownHostException {

        worker = new CitySearch("FinalDatabaseProject",
                "Books",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),1),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:27017")));
    }

    @Test(expected = ServiceUnavailableException.class)
    public void Neo4jPortPositive2() throws InputException, UnknownHostException {

        worker = new CitySearch("FinalDatabaseProject",
                "Books",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),2),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:27017")));
    }

    //endregion


    @Test
    public void mockCitySearch() throws Exception, InputException {
        given(mockedWorker.Search("Roskilde")).willReturn("Test passing");
        verify(mockedWorker, times(0)).Search(anyString());
        Assert.assertThat(mockedWorker.Search("Roskilde"),is("Test passing"));
        verify(mockedWorker, times(1)).Search(anyString());
    }
}