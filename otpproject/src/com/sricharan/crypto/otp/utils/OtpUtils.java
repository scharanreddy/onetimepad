package com.sricharan.crypto.otp.utils;

import java.io.File;
import org.apache.commons.io.FileUtils;
import com.sricharan.crypto.exception.*;
 
/**
 * A utility class that encrypts or decrypts a file using the one time pad algorithm
 * (http://www.cs.miami.edu/home/burt/learning/Csc609.051/notes/02.html)
 * @author sricharan reddy
 */

public class OtpUtils
{
	
	/**
	 * Default constructor 
	 * TODO: Improve to include parameters
	 * 
	 */
	 public OtpUtils()
	 {
	    	
	 } 
	 
	/**
	 * Static method for external access for encrypting the file using one time pad
	 * @param  inputFile  the file to be encrypted
	 * @param  outputFile the file which has been encrypted
	 * @return the key file
	 * @throws CryptoException
	 * TODO : improve handling of output files etc
	 */
    public static File encrypt(File inputFile, File outputFile)
            throws CryptoException
    {
    	   OtpUtils utils   = new OtpUtils();
       File     keyFile = utils.doCrypto(inputFile, outputFile);
       return   keyFile;
    }
    
    /**
     * Static  method to decrypt the file encrypted using one time pad
     * @param  keyFile   the file containing the key used to encrypt the file
     * @param  inputFile the input file to be decrypted
     * @return the plain text file corresponding to the encrypted file
     * @throws CryptoException
     * TODO : improve handling of output files etc
     */
    public static void decrypt(File keyFile, File inputFile,String plainFile)
            throws CryptoException 
    {
    	    OtpUtils utils   = new OtpUtils();
    	    utils.undoCrypto(keyFile,inputFile,plainFile);
	}
 
    /**
     * Function to decrypt the file encrypted using one time pad
     * @param  keyFile   the file with the key used to encrypt the file
     * @param  inputFile the file with the cipher text
     * @param  the fully qualified file name for the decrypted file
     * @return void
     * @throws CryptoException
     */
    private void undoCrypto(File keyFile, File inputFile,String plainFile) throws CryptoException
    {
      	
    	    try
    	    {
    	    	File   decryptedFile = new File(plainFile);
    	    byte[] inputBytes = new byte[(int) inputFile.length()];
        byte[] outputBytes = new byte[(int) inputFile.length()];
        byte[] keyBytes   = new byte[(int) keyFile.length()];
        inputBytes = FileUtils.readFileToByteArray(inputFile);
        keyBytes = FileUtils.readFileToByteArray(keyFile);
         
        // Decrypt
	    for (int i = 0; i < inputBytes.length; i++)
	    {
	    	outputBytes[i] = (byte) (inputBytes[i] ^ keyBytes[i]);
	    }
	    FileUtils.writeByteArrayToFile(decryptedFile, outputBytes);
        }
    	    catch ( Exception ex) 
            {
                throw new CryptoException("Error decrypting file", ex);
            }
	}

    /**
     * 
     */
	private  File doCrypto(File inputFile,File outputFile) throws CryptoException 
	{
		File keyFile = new File("keyfile.key");
        try 
        {
            byte[] inputBytes  = new byte[(int) inputFile.length()];
            byte[] outputBytes = new byte[(int) inputFile.length()];
            inputBytes = FileUtils.readFileToByteArray(inputFile);
            //final  byte[] key = new byte[inputBytes.length];
            //new SecureRandom().nextBytes(key);
            final  byte[] key = KeyUtils.createKey(inputBytes.length);
			
			 // Encrypt
		    for (int i = 0; i < key.length; i++)
		    {
		    	outputBytes[i] = (byte) (inputBytes[i] ^ key[i]);
		    }
             
		    FileUtils.writeByteArrayToFile(outputFile, outputBytes);
            FileUtils.writeByteArrayToFile(keyFile, key);
            return keyFile;
         } 
        catch ( Exception ex) 
        {
            throw new CryptoException("Error encrypting file", ex);
        }
     }
}
