import java.util.*;
import java.net.*;
import java.io.*;

public class DownloadFile{
	
	public static final String BASE_URL = "http://codeforfun.16mb.com/testchat/";
	
	public static final String SCRIPT = ConnectServer.URL + "filedo.php";
	
	public static boolean downloadFile(String fileName){
		
		try {
			URL connectURL = new URL(SCRIPT);
			HttpURLConnection conn = (HttpURLConnection) connectURL.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			//conn.setRequestProperty("Accept", "Content-Disposition: name=\"file\"; filename=\"" + fileName + "\"" + "\r\n");
			
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());			
			
			List<String> queryData = new ArrayList<>();
			queryData.add("file");queryData.add(fileName);
			
			wr.writeBytes(Encode.encodeURL(queryData));
			wr.flush();
			wr.close();
			
			InputStream is = conn.getInputStream();
			byte[] buffer = new byte[10240]; // 10K is a 'reasonable' amount
	
			//System.out.println("File name = "+fileName);
			String tmp[] = fileName.split("/");
	
			File newFile = new File("Downloads/"+tmp[tmp.length-1]);
			FileOutputStream fos = new FileOutputStream(newFile);
	
			int len = 0;
			while ((len = is.read(buffer)) >= 0) {
				fos.write(buffer, 0, len);
			}
			fos.flush();
			fos.close();
			
			return true;
		} catch (Exception iox) {
			iox.printStackTrace();
		}
		//System.out.println("DONE-DOWNLOAD");
		return false;
	}
}