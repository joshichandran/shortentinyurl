package com.payment.tiny.paymentlink.externalservice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormat {

	public static void main(String[] args) {

		DateTimeFormatter date = DateTimeFormatter.ofPattern("MM/dd/yyyy, hh:mm:ss a");
		LocalDateTime.parse("01/08/2021, 08:02:29 PM", date);
		
		
	}

}
