package cracker;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

	public static String sha256Hash(String input) {
		
		String hex = null;
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			
			//TODO: Make option to change charset
			messageDigest.update(input.getBytes(StandardCharsets.UTF_8));
			byte[] digest = messageDigest.digest();
			
			hex = String.format("%064x", new BigInteger(1, digest)); // For some reason in one tutorial 1st arg was "0x%064x", %064x means generating 64 character hex string
			
		}catch(NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return hex;
	}
	
	
}
