package boredatlecture;

public class Request {
	private String userName;
	private int lobbyId;
	
	public Request(String userName, int lobbyId){
		this.userName = userName;
		this.lobbyId = lobbyId;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public int getLobbyId(){
		return lobbyId;
	}
	
}
