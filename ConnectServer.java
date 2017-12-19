import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectServer {
	
	public static final String url = "http://codeforfun.16mb.com/testchat/";
	//public static final String URL = "http://localhost/testChat/";
	
	public static String runOnServer(String script,String query){
		try {
			URL obj = new URL(URL+script+"?"+query);
			
			//System.out.println("Requested URL = "+url+script+query);
			
			HttpURLConnection connection = (HttpURLConnection)obj.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0");
			connection.setDoOutput(true);
			connection.setConnectTimeout(10000);
			
			//Send post request data
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(query);
			wr.flush();
			wr.close();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String input;
			StringBuffer response = new StringBuffer();
			
			while((input = br.readLine()) != null){
				response.append(input);
			}
			
			br.close();
			//System.out.println("ConnectionServer -- -- Response Received is = "+response.toString());
		
			return response.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
