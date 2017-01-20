package com.bid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bid.common.BidConstants;
import com.bid.common.util.BidUtil;
import com.google.gson.JsonObject;

/**
 * File  : Test.java
 * Description          : This Test is   
 * Revision History :
 * Version      Date            	Author       Reason
 * 0.1          Jan 11, 2017      	595251  	 Initial version
 */

/**
 * @author 595251
 *
 */
public class Test {
	
	public static void main (String[] args) throws ParseException{
		JsonObject json = new JsonObject();
		//json.addProperty("name", "Subbu");
		
		System.out.println("  json 1 : " +json);
		//json = CloseBid.main(json);
		//json = SendGridMail.main(json);
		//BidUtil.getKey(BidUtil.getConnection());
		
		System.out.println(new SimpleDateFormat(BidConstants.BID_DATE_FORMAT).format(new Date()));
		
		if(new SimpleDateFormat(BidConstants.BID_DATE_FORMAT).parse("2017-01-19T05:00:00").after(new SimpleDateFormat(BidConstants.BID_DATE_FORMAT).parse("2017-01-19T00:00:00")))
			System.out.println("  YES");
		System.out.println("  json 2 : " +json);
	}

}
