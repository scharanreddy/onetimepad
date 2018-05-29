package com.sricharan.crypto.otp.utils;

import java.security.SecureRandom;

public class KeyUtils 
{
	
	/**
	 * TODO : Add a keygen function using secure random(https://docs.oracle.com/javase/7/docs/api/java/security/SecureRandom.html)
	 * @param keyArray
	 * @return
	 */
	protected static byte[] createKey(int  length)
	{
		final byte[] key = new byte[length];
		new SecureRandom().nextBytes(key);
		return key;
	}

}
