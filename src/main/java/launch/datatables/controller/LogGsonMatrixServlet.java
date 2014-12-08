package datatables.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import datatables.model.DataRepository;
import datatables.model.JQueryDataTableParamModel;
import datatables.model.LogData;

/**
 * CompanyServlet provides data to the JQuery DataTables
 */
public class LogGsonMatrixServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LogGsonMatrixServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JQueryDataTableParamModel param = DataTablesParamUtility.getParam(request);
		
		String sEcho = param.sEcho;
		int iTotalRecords; // total number of records (unfiltered)
		int iTotalDisplayRecords; //value will be set when code filters companies by keyword
		JsonArray data = new JsonArray(); //data that will be shown in the table

		iTotalRecords = DataRepository.GetLogs().size();
		List<LogData> companies = new LinkedList<LogData>();
		for(LogData c : DataRepository.GetLogs()){
			if(	c.getProduct().toLowerCase().contains(param.sSearch.toLowerCase())
				||
				c.getDateTime().toLowerCase().contains(param.sSearch.toLowerCase())
				
				||
				c.getModule().toLowerCase().contains(param.sSearch.toLowerCase())
				||
				c.getType().toLowerCase().contains(param.sSearch.toLowerCase())
				||
				c.getUserID().toLowerCase().contains(param.sSearch.toLowerCase())
				||
				c.getMessage().toLowerCase().contains(param.sSearch.toLowerCase())
				||
				c.getDetails().toLowerCase().contains(param.sSearch.toLowerCase())
				||
				c.getIP().toLowerCase().contains(param.sSearch.toLowerCase()))
			{
				companies.add(c); // add company that matches given search criterion
			}
		}
		iTotalDisplayRecords = companies.size(); // number of companies that match search criterion should be returned
		
		final int sortColumnIndex = param.iSortColumnIndex;
		final int sortDirection = param.sSortDirection.equals("asc") ? -1 : 1;
		
		Collections.sort(companies, new Comparator<LogData>(){
			@Override
			public int compare(LogData c1, LogData c2) {	
				switch(sortColumnIndex){
				case 0:
					return c1.getProduct().compareTo(c2.getProduct()) * sortDirection;
				case 1:
					return c1.getDateTime().compareTo(c2.getDateTime()) * sortDirection;
				case 2:
					return c1.getModule().compareTo(c2.getModule()) * sortDirection;
				case 3:
					return c1.getType().compareTo(c2.getType()) * sortDirection;
				case 4:
					return c1.getUserID().compareTo(c2.getUserID()) * sortDirection;
				case 5:
					return c1.getMessage().compareTo(c2.getMessage()) * sortDirection;
				case 6:
					return c1.getDetails().compareTo(c2.getDetails()) * sortDirection;
				case 7:
					return c1.getIP().compareTo(c2.getIP()) * sortDirection;
				
				}
				return 0;
			}
		});
		
		if(companies.size()< param.iDisplayStart + param.iDisplayLength) {
			companies = companies.subList(param.iDisplayStart, companies.size());
		} else {
			companies = companies.subList(param.iDisplayStart, param.iDisplayStart + param.iDisplayLength);
		}
	
		try {
			JsonObject jsonResponse = new JsonObject();			
			jsonResponse.addProperty("sEcho", sEcho);
			jsonResponse.addProperty("iTotalRecords", iTotalRecords);
			jsonResponse.addProperty("iTotalDisplayRecords", iTotalDisplayRecords);
			
			for(LogData c : companies){
				JsonArray row = new JsonArray();
				row.add(new JsonPrimitive(c.getProduct()));
				row.add(new JsonPrimitive(c.getDateTime()));
				row.add(new JsonPrimitive(c.getModule()));
				row.add(new JsonPrimitive(c.getType()));
				row.add(new JsonPrimitive(c.getUserID()));
				row.add(new JsonPrimitive(c.getMessage()));
				row.add(new JsonPrimitive(c.getDetails()));
				row.add(new JsonPrimitive(c.getIP()));
				
				data.add(row);
			}
			jsonResponse.add("aaData", data);
			
			response.setContentType("application/Json");
			response.getWriter().print(jsonResponse.toString());
			
		} catch (JsonIOException e) {
			e.printStackTrace();
			response.setContentType("text/html");
			response.getWriter().print(e.getMessage());
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}