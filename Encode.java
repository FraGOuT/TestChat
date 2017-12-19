import java.util.List;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Encode{
	
	public static String encodeURL(List<String> list){
		
		if(list.size()%2!=0){
			//Small error check
			//The list size should always be an even number as the parameters to the php script are in pair.
			System.out.println("ERROR - Malformed URL - Please check the argument for the URL");
			System.exit(0);
		}
		
		StringBuilder url = new StringBuilder(); 
		
		try{
			for(int i=0;i<list.size();i+=2){
				url.append(URLEncoder.encode(list.get(i),"UTF-8"));
				url.append("=");
				url.append(URLEncoder.encode(list.get(i+1),"UTF-8"));
				url.append("&");	
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		url.deleteCharAt(url.length()-1);
		
		return url.toString();
	}
	
}