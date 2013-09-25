package boredatlecture;

import java.io.Serializable;
    
	class Package implements Serializable{
		
	    private long id;
	    private String position;
	    private String ip;
	    private long receiveID; //if 0, go to lobby
	    
	    
	    public Package(){
	        
	    }
	    
	    public long getID(){
	        return id;
	    }
	    public long getReceiveID(){
	        return receiveID;
	    }
	    public String getPosition(){
	        return position;
	    }
	    public String getIP(){
	        return ip;
	    }
	    
	    
	}
