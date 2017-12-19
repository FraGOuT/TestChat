import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Chat {
	
	public static final String QUIT_COMMAND = ".q";
	public static final String CHANGE_USER_COMMAND = ".chrec";
	public static final String SEND_FILE_TO_USER = ".upsendfile";
	public static final String DOWNLOAD_FILE_TO_USER = ".downfile";
	public static final int REFRESH_RATE_FOR_RECIEVE_THREAD = 300;//in ms
	
	public static String username = "undefined";
	public static String password = "";
	public static String accessToken = null;
	
	public static String recipientUsername;
	public static Scanner sc = new Scanner(System.in);
	
	
	public static void main(String args[]){
		
		//Query the server for authenticating the user
		do{
			System.out.print("Enter Your Username: ");
			username = sc.next();
		
			System.out.print("Enter Your Password: ");
			password = sc.next();
		}while(LoginAuth.loginUser() != true);
		
		sc.nextLine();//Garbage
		
		System.out.println("\nYour username is "+username);
		System.out.print("Enter recipient: ");
		recipientUsername = sc.next();
		sc.nextLine();//consume the garbage character;
		

		//Message write thread
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(true){
					System.out.print("1Enter Message: ");
					String message = sc.nextLine();

					//System.out.printf("Message - '%s'  == %c!..\n",message,message.charAt(0));
					
					if(message.length()==0){
						System.out.println("\tEmpty message not sent");
						continue;
					}
					if(message.equals(QUIT_COMMAND))
						System.exit(0);
					
					if(message.contains(SEND_FILE_TO_USER)){
						//We need to upload the file to server.
						String filePath = message.split(" ")[1];
						UploadFile.uploadFile(filePath);
						//filePath.replace("\\","\\\\")
						//String[] tmp = filePath.split("\\");
						
						int ii;
						for(ii=filePath.length()-1;ii>=0;ii--)
						{
							//System.out.println("II="+ii);
							if(filePath.charAt(ii) == '\\')
								break;
						}
						ii = ii > 0 ? ii+1 : 0;
						System.out.println("Uploaded file = "+filePath.substring(ii));
						
						message = DOWNLOAD_FILE_TO_USER+" "+filePath.substring(ii);
					}
					
					if(message.length()>CHANGE_USER_COMMAND.length() && message.substring(0,CHANGE_USER_COMMAND.length()).equals(CHANGE_USER_COMMAND)){
						recipientUsername = message.substring(CHANGE_USER_COMMAND.length()+1);
						
						System.out.println("Recipient changed to "+recipientUsername);
						continue;
					}
					
					Send.sendMessage(recipientUsername,message);
				}
			}
		}).start();
		

		//Message recieve thread
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					Recieve.recieveMessage();
					//System.out.println("Recieve Message Running");
					try {
						Thread.sleep(REFRESH_RATE_FOR_RECIEVE_THREAD);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

	}
	
}
