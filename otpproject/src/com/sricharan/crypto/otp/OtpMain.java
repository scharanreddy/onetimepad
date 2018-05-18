package com.sricharan.crypto.otp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sricharan.crypto.exception.CryptoException;
import com.sricharan.crypto.otp.utils.OtpUtils;

public class OtpMain 

{
	
	public static void main(String args[]) throws CryptoException
	{
		File f       = new File("inputfile.txt");
		File outFile = new File("outfile.txt");
		File keyFile = OtpUtils.encrypt(f, outFile);
		File plainFile = OtpUtils.decrypt(keyFile, outFile);
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
