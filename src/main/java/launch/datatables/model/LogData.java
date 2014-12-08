package datatables.model;

public class LogData {
	
	
    public LogData(String product, String dateTime, String module, String type, String userID,String message, String details, String IP)
    {
       // id = nextID++;
        this.product = product;
        this.dateTime = dateTime;
        this.module = module;
        this.type = type;
        this.userID = userID;
        this.message = message;
        this.details = details;
        this.IP = IP;
        
        
       
    }
      
       
    private String product;
	    public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String IP) {
		this.IP = IP;
	}

		private String dateTime;
	    private String module;
	    private String type;
	    private String userID;
        private String message;
        private String details;
	    private String IP;

	
}
