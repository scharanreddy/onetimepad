package com.sricharan.crypto.otp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sricharan.crypto.exception.CryptoException;
import com.sricharan.crypto.otp.utils.OtpUtils;

/**
 * Main Class to test the OTP program
 * @author sricharan
 *
 */

public class OtpMain 

{
	
	public static void main(String args[]) throws CryptoException, IOException
	{
		File f       = new File("Dulles.pdf");
		File outFile = new File("output.otp");
		//File plainFile = new File("decrypt.pdf");
		File keyFile = OtpUtils.encrypt(f, outFile);
	    OtpUtils.decrypt(keyFile, outFile,"decrypt.pdf");
	}

	private static File newDummyFile() 
	{
		byte[] b = new byte[4096];
		File   f = new File("inputfile.txt");
		try (FileOutputStream stream = new FileOutputStream(f))
		{
		    stream.write(b);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  f;
	}

}
