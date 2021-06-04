package cracker;

public class BruteForce {

	char[] allPossibleCharacters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','1','2','3','4','5','6','7','8','9','0','!','@','#','£','¤','$','%','&','/','{','(','[',')',']','=','}','?','+','*','½','§'}; //TODO: For part that uses multithread guessing, Could have methods/Setting variables, since nowadays common passwords are 8 marks long, contain atleast 1 uppercase and one special character
	char[] lowercaseChars = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	char[] uppercaseChars = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	char[] specialChars = {'1','2','3','4','5','6','7','8','9','0','!','@','#','£','¤','$','%','&','/','{','(','[',')',']','=','}','?','+','*','½','§'};
	char[] customCharSet = {'1'};
	char[] usedCharSet;
	//Two fields, one button, like in cracker. But keeping things separated bcuz it's more clear.
	//Also need options for this... (Preferences menu?)
	
	private static int passwordMinLenght = 4;
	private static String inputHash ="";
	public static String password ="";
	
	public BruteForce() {
		
		this.usedCharSet = this.allPossibleCharacters;
		
	}
	
	public void setChars(String chars) {
		
		//TODO: Take characters as string and parse with space
		
		customCharSet = chars.toCharArray();
		
	}
	public void useCharSet(char[] chars){
		
		this.usedCharSet = chars;
		
	}
	
    



	// int cnt;
	// Recursive helper function, adds/removes characters
	// until len is reached
	static void generate(char[] arr, int i, String s, int len) throws Exception
	{
		
		if(HashUtils.sha256Hash(s).matches(inputHash)) {
			System.out.println("password is " + s);
			BruteForce.password = s;
			stop();
		}
		
		
	// base case
	if (i == 0) // when len has been reached
	{
	    // print it out
			//System.out.println(s);
	    // cnt++;
	    return;
	}

	// iterate through the array
	for (int j = 0; j < len; j++)
	{
	
	    // Create new string with next character
	    // Call generate again until string has
	    // reached its len
	    String appended = s + arr[j];
	    generate(arr, i - 1, appended, len);
	    
	}

	return;
	}

	// function to generate all possible passwords
	static void crack(char[] arr, int len) throws Exception
	{
	// call for all required lengths
			for (int i = passwordMinLenght; i <= len; i++)
			{
			    generate(arr, i, "", len);
			}
	}
	
	public void start(int passwordMinLenght, String inputHash) throws Exception {
		
		BruteForce.passwordMinLenght = passwordMinLenght;
		BruteForce.inputHash = inputHash;
	    int charSetLenght = usedCharSet.length;
		crack(usedCharSet, charSetLenght);
	}
	public static void stop() throws Exception {
		throw new Exception();
	}
	
}
