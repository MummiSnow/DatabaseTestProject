import exceptions.InputException;
import org.junit.Ignore;
import workers.CitySearch;
import workers.IWorker;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoTimeoutException;
import org.junit.Test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class CitySearchLongTest {
    IWorker worker = null;

    @Ignore("Servers wen't down on May 25th.")
    @Test(expected = MongoTimeoutException.class)
    public void NonExistentPortMongo() throws InputException, UnknownHostException {

        worker = new CitySearch("FinalDatabaseProject",
                "Books",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),7687),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:60000")));
    }
    @Ignore("Servers wen't down on May 25th.")
    @Test(expected = MongoTimeoutException.class)
    public void MongoPortNegative5() throws InputException, UnknownHostException {

        worker = new CitySearch("FinalDatabaseProject",
                "Books",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),7687),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:-5")));
    }
    @Ignore("Servers wen't down on May 25th.")
    @Test(expected = MongoTimeoutException.class)
    public void MongoPortNegative1() throws InputException, UnknownHostException {

        worker = new CitySearch("FinalDatabaseProject",
                "Books",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),7687),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:-1")));
    }
    @Test(expected = MongoTimeoutException.class)
    @Ignore("Servers wen't down on May 25th.")
    public void MongoPort0() throws InputException, UnknownHostException {

        worker = new CitySearch("FinalDatabaseProject",
                "Books",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),7687),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:0")));
    }
    @Test(expected = MongoTimeoutException.class)
    @Ignore("Servers wen't down on May 25th.")
    public void MongoPortPositive1() throws InputException, UnknownHostException {

        worker = new CitySearch("FinalDatabaseProject",
                "Books",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),7687),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:1")));
    }

    @Test(expected = MongoTimeoutException.class)
    @Ignore("Servers wen't down on May 25th.")
    public void MongoPortPositive2() throws InputException, UnknownHostException {

        worker = new CitySearch("FinalDatabaseProject",
                "Books",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),7687),
                new MongoClient(new MongoClientURI("mongodb://207.154.228.197:2")));
    }

    @Test(expected = MongoTimeoutException.class)
    @Ignore("Servers wen't down on May 25th.")
    public void NonExistentIPMongo() throws InputException, UnknownHostException {

        worker = new CitySearch("FinalDatabaseProject",
                "Books",
                new InetSocketAddress(InetAddress.getByName("165.227.128.49"),7687),
                new MongoClient(new MongoClientURI("mongodb://256.256.256.256:27017")));
    }

}
