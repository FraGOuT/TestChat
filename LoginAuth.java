import java.util.List;
import java.util.ArrayList;

public class LoginAuth{
	
	public static final String SCRIPT = "userAuth.php";
	
	public static boolean loginUser(){
		
		List<String> queryArgs = new ArrayList<>();
		queryArgs.add("username");queryArgs.add(Chat.username);
		queryArgs.add("password");queryArgs.add(Chat.password);
		
		String query = Encode.encodeURL(queryArgs);
		
		String result = ConnectServer.runOnServer(SCRIPT,query);
		
		//Check the response from the server 
		if(result.equals("0")){
			System.out.println("Login Failed\n");
			return false;
		}
		else{
			System.out.println("\nLogin Successfull\n");
			Chat.accessToken = result;
			//System.out.println("Token - "+result);
			return true;
		}	
	}
}