package com.sricharan.crypto.otp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import com.sricharan.crypto.exception.CryptoException;
 
/**
 * A utility class that encrypts or decrypts a file using the one time pad
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
    public static File decrypt(File keyFile, File inputFile)
            throws CryptoException 
    {
    	    OtpUtils utils   = new OtpUtils();
        File plainFile   = utils.undoCrypto(keyFile, inputFile);
		return plainFile;
    }
 
    /**
     * Function to decrypt the file encrypted using one time pad
     * @param  keyFile   the file with the key used to encrypt the file
     * @param  inputFile  
     * @return
     * @throws CryptoException
     */
    private File undoCrypto( File keyFile, File inputFile) throws CryptoException
    {
      	File plainFile = new File("plainfile.txt");
    	    try(FileInputStream inputStream = new FileInputStream(inputFile);
    	    		FileInputStream keyStream = new FileInputStream(keyFile);
    	    		FileOutputStream outputStream = new FileOutputStream(plainFile);)
    	    {
    	    byte[] inputBytes = new byte[(int) inputFile.length()];
        byte[] outputBytes = new byte[(int) inputFile.length()];
        byte[] keyBytes   = new byte[(int) keyFile.length()];
        inputStream.read(inputBytes);
        keyStream.read(keyBytes);
         
        // Decrypt
	    for (int i = 0; i < inputBytes.length; i++)
	    {
	    	outputBytes[i] = (byte) (inputBytes[i] ^ keyBytes[i]);
	    }
        outputStream.write(outputBytes);
        return plainFile;
    	    }
    	    catch ( Exception ex) 
            {
                throw new CryptoException("Error decrypting file", ex);
            }
	}

	private  File doCrypto( File inputFile,File outputFile) throws CryptoException 
	{
		
		File keyFile = new File("keyfile.key");
        try(FileInputStream inputStream = new FileInputStream(inputFile);
        		 FileOutputStream outputStream = new FileOutputStream(outputFile);
                FileOutputStream keyStream = new FileOutputStream(keyFile);) 
        {
            
            byte[] inputBytes  = new byte[(int) inputFile.length()];
            byte[] outputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
            final  byte[] key = new byte[inputBytes.length];
			new SecureRandom().nextBytes(key);
			
			 // Encrypt
		    for (int i = 0; i < key.length; i++)
		    {
		    	outputBytes[i] = (byte) (inputBytes[i] ^ key[i]);
		    }
             
            outputStream.write(outputBytes);
            keyStream.write(key);
            return keyFile;
         } 
        catch ( Exception ex) 
        {
            throw new CryptoException("Error encrypting file", ex);
        }
     }
}
