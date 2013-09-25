package boredatlecture;

public class Response {
	private ChatPackage out;
	private boolean resp;
	
	public Response(ChatPackage out, boolean resp){
		this.out = out;
		this.resp = resp;
	}
	
	public ChatPackage getPackage(){
		return out;
	}
	
	public boolean update(){
		return resp;
	}
}
