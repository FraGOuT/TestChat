import java.util.*;
import java.net.*;
import java.io.*;
public class UploadFile{
	
	public static String fileName = "hello.txt";
    public static final String lineEnd = "\r\n";
    public static final String twoHyphens = "--";
    public static final String boundary = "*****";
	
	public static FileInputStream fileInputStream = null;
	public static DataOutputStream dos = null;
	public static InputStream is = null;
	
	public static final String BASE_URL = "http://codeforfun.16mb.com/testchat/";
	
	public static final String SCRIPT = ConnectServer.URL + "fileup.php";
	
	public static boolean uploadFile(String fileName){
		
		//Check whether the file exists.
		try {
			URL connectURL = new URL(SCRIPT);
            HttpURLConnection conn = (HttpURLConnection) connectURL.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            dos = new DataOutputStream(conn.getOutputStream());

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\"" + fileName + "\"" + lineEnd);
            dos.writeBytes(lineEnd);

			
			File f = new File(fileName);
			if(f.exists())
				fileInputStream = new FileInputStream(fileName);
			else{
				System.out.println("Such a file does not exists");
				return false;
			}	
			
			
            int bytesAvailable = fileInputStream.available();
            int maxBufferSize = 1024;
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
            byte[] buffer = new byte[bufferSize];

            int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {
                dos.write(buffer, 0, bufferSize);
				//dos.flush();
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            fileInputStream.close();
            dos.flush();

            is = conn.getInputStream();
            int ch;
            StringBuffer b = new StringBuffer();
            while ((ch = is.read()) != -1) {
                b.append((char) ch);
            }
			System.out.println("Response = "+b.toString());
            dos.close();
			return true;
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
		finally{
			if(fileInputStream != null)
				fileInputStream.close();
			if(dos != null)
				dos.close();
			if(is != null)
				is.close();
		}
		return false;
	}
}