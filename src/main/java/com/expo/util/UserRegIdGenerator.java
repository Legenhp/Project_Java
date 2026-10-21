package com.expo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class UserRegIdGenerator {
	
	public static int UserIdregGenerator() {

		Random random=new Random();
		int i =random.nextInt(99999);
		return i;
		
		
		
		
	}

}
//"LMS "+LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")