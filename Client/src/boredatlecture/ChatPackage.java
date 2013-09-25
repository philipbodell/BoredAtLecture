package boredatlecture;

import java.io.Serializable;

public class ChatPackage extends Package implements Serializable  {
	static final long serialVersionUID = 42L;
    
    private int lobbyId;
    private String message;
    
    public ChatPackage(int lobbyId ,String message){
        this.lobbyId=lobbyId;
        this.message=message;
        
    }
    
    public int getLobbyId(){
        return lobbyId;
    }
    public String getMessage(){
        return message;
    }
}
