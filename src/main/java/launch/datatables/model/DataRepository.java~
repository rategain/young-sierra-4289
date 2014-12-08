package datatables.model;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class DataRepository {
	
	
    private static List<LogData> LogsData = null;
    
  
    public static List<LogData> GetLogs() throws UnknownHostException, MongoException
    {
    	String dateTime = null;
        System.out.println("in DATAREpository");
        if (LogsData == null)
        {
        	LogsData = new LinkedList<LogData>();

            
            Mongo mongo = new Mongo("10.0.0.0", 27017);
            //Mongo mongo = new Mongo("10.0.0.193", 27019);
			DB db = mongo.getDB("local");
			DBCollection collection = db.getCollection("startup_log");
			
			DBCursor cursor = collection.find();
			Gson gson = new Gson();
			while(cursor.hasNext()) {
			   // System.out.println(cursor.next());
				DBObject str = cursor.next();
				
								
				String jsonProduct = gson.toJson(str.get("Product"));
				String jsonDateTime = gson.toJson(str.get("CreatedOn"));
				String jsonModule = gson.toJson(str.get("Module"));
				String jsonType = gson.toJson(str.get("Type"));
				String jsonUserID = gson.toJson(str.get("UserId"));
				String jsonMessage = gson.toJson(str.get("Message"));
				String jsonDetails = gson.toJson(str.get("Details"));
				String jsonIP = gson.toJson(str.get("IP"));
				
				/*System.out.println(str.get("Product"));
				System.out.println(str.get("CreatedOn"));
				System.out.println(str.get("Module"));
				System.out.println(str.get("Type"));
				System.out.println(str.get("UserId"));
				System.out.println(str.get("Message"));
				System.out.println(str.get("Details"));
				System.out.println(str.get("IP"));
				System.out.println();*/
				
				//String JsonDate = jsonDateTime;

		        int idx1 = jsonDateTime.indexOf("(");
		        int idx2 = jsonDateTime.indexOf("+");
		        
		        String s = null;
		        dateTime = "22-nov-2011";

		        /*if (idx1 > 0 && idx1 < idx2 && idx2 < jsonDateTime.length()) {
		             s = jsonDateTime.substring(idx1 + 1, idx2);  
		        }
		        
		        if (!s.equalsIgnoreCase("null")) {
		            Long date1 = Long.parseLong(s);
		            Date dt1 = new Date(date1);

		            dateTime= getDateCurrentTimeZone(dt1);
		        }*/
		        
		       
		        jsonProduct = "RevGain";
				//System.out.println(json);
		        LogsData.add(new LogData(jsonProduct, dateTime,jsonModule,jsonType,jsonUserID,jsonMessage, jsonDetails, jsonIP ));
			}
            
            
        }
        return LogsData;
    }
    
    public static String getDateCurrentTimeZone(Date dt) {
        try {
            //Convert DATE to UTC DATETIME
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    "MMM dd yyyy hh:mm:ss aa ");
             simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            //simpleDateFormat.setTimeZone(TimeZone.getDefault());
             //Convert to LocalDATE from UTC
             Date fromGmt = new Date(dt.getTime() + TimeZone.getDefault().getOffset(dt.getTime()));

                        return new SimpleDateFormat("MMM dd yyyy hh:mm:ss aa").format(fromGmt).toString();

        } catch (Exception e) {
        }
        return "";
    }

}
