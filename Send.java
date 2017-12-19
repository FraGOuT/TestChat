import java.util.List;
import java.util.ArrayList;


public class Send {
	
	public static final String SCRIPT = "sendmessage.php";
	
	public static void sendMessage(String reciepient,String message){
		
		List<String> parameters = new ArrayList<String>();
		parameters.add("access_token");parameters.add(Chat.accessToken);
		parameters.add("touser");parameters.add(reciepient);
		parameters.add("message");parameters.add(Crypt.encrypt(message));
		String query = Encode.encodeURL(parameters);
		
		String result = ConnectServer.runOnServer(SCRIPT,query);
		
		//Check the response from the server 
		if(result == null || !result.equals("1")){
			System.out.println("ERROR-Server1 : The message could not be sent.");
		}
		else{
			//System.out.println("Message sent result = "+result);
		}
		
	}

}
