package testcases;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

public class SingleNodeWriteConcernTests {
	 
    private final static int ONE_MILLION = 1000000;
 
    private final Logger logger = Logger
            .getLogger(SingleNodeWriteConcernTests.class);
 
    @Test
    public void shouldInsertRecordsInNonCurrentMode() throws Exception {
        ServerAddress serverAddress = new ServerAddress("127.0.0.1", 27017);
 
        Mongo mongo = new Mongo(serverAddress);
        mongo.setWriteConcern(WriteConcern.NONE);
        runASingleTestCase(mongo, "NONE");
 
        mongo = new Mongo(serverAddress);
        mongo.setWriteConcern(WriteConcern.FSYNC_SAFE);
        runASingleTestCase(mongo, "FSYNC_SAFE");
 
        mongo = new Mongo(serverAddress);
        mongo.setWriteConcern(WriteConcern.NORMAL);
        runASingleTestCase(mongo, "NORMAL");
 
        mongo = new Mongo(serverAddress);
        mongo.setWriteConcern(WriteConcern.SAFE);
        runASingleTestCase(mongo, "SAFE");
 
    }
 
    private void runASingleTestCase(Mongo mongo, String name) throws Exception {
        DB db = mongo.getDB("play");
        DBCollection people = db.getCollection("people");
        if (db.collectionExists("people")) {
            people.drop();
        }
        insertRecords(mongo, name);
 
        mongo.dropDatabase("play");
    }
 
    private void insertRecords(Mongo mongo, final String name) throws Exception {
 
        DB db = mongo.getDB("play");
        final DBCollection collection = db.getCollection("people");
        collection.ensureIndex("fName");
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= ONE_MILLION; i++) {
            BasicDBObject obj = new BasicDBObject();
            Map<String, String> map = new HashMap<String, String>();
            map.put("fName", "Shekhar" + i);
            map.put("lName", "Gulati" + i);
            map.put("age", String.valueOf(i));
            map.put("bio", StringUtils.repeat("I am a Java Developer. ", 100));
            obj.putAll(map);
            collection.insert(obj);
        }
        long endTime = System.currentTimeMillis();
        double seconds = ((double) (endTime - startTime)) / (1000);
        double rate = ONE_MILLION / seconds;
 
        String message = String
                .format("WriteConcern %s inserted %d records in %.2f seconds at %.2f (rec/s)",
                        name, ONE_MILLION, seconds, rate);
        logger.info(message);
 
    }
 
}
