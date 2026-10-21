package com.expo.util;

import java.security.SecureRandom;

public class OtpGenerator {
	
	public static String otpgenerator() {	
		
		
	SecureRandom secureRandom = new SecureRandom();
	return String.format("%06d", secureRandom.nextInt(1000000));
	}
}


//return String.valueOf(100000 + secureRandom.nextInt(900000));