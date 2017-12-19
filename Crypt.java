
public class Crypt{
	
	private static final String KEY = "MNBVCXZLKJHGFDSAPOIUYTREWQ";
	private static final String KEYSMALL = KEY.toLowerCase();
	
	
	public static String encrypt(String message){
		StringBuffer encryptMessage = new StringBuffer(message.length());
		//System.out.println("Message = "+message+ " -- len="+message.length());
		for(int i=0;i<message.length();i++){
			
			//System.out.println("I="+i+" -- C="+message.charAt(i));
			if(message.charAt(i) == ' ')
				encryptMessage.append(' ');
			else if(message.charAt(i) == '.')
				encryptMessage.append('.');
			else if(message.charAt(i)-'A' < 26)
				encryptMessage.append(KEY.charAt(message.charAt(i)-'A'));
			else if(message.charAt(i)-'a' < 26)
				encryptMessage.append(KEYSMALL.charAt(message.charAt(i)-'a'));
			else
				encryptMessage.append(message.charAt(i));
			
		}
		return encryptMessage.toString();
	}
	
	
	public static String decrypt(String message){
		StringBuffer decryptMessage = new StringBuffer(message.length());
		
		for(int i=0;i<message.length();i++){
			
			//System.out.printf("%c -- offset = %d\n",message.charAt(i),offset);
			if(message.charAt(i) == ' ')
				decryptMessage.append(' ');
			else if(message.charAt(i) >= '!' && message.charAt(i) <='@')
				decryptMessage.append(message.charAt(i));
			else if(message.charAt(i) < 'Z')
				decryptMessage.append((char)('A'+KEY.indexOf(message.charAt(i))));
			else
				decryptMessage.append((char)('a'+KEYSMALL.indexOf(message.charAt(i))));
		}
		return decryptMessage.toString();
	}
	
	public static void main(String args[]){
		
		String mess = "WOW hello woRlD"; 
		//"ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		mess = encrypt(mess);
		System.out.println("ENCRYPT MESSAGE = "+mess);
		
		mess = decrypt(mess);
		System.out.println("DECRYPT MESSAGE = "+mess);
	}
}