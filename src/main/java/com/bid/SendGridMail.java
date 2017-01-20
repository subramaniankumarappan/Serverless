package com.bid;
/**
* File  : SendGridMail.java
* Description          : This SendGridMail is   
* Revision History :
* Version      Date            	Author       Reason
* 0.1          Jan 12, 2017      	595251  	 Initial version
*/


/**
* File  : SendGridMail.java
* Description          : This SendGridMail is   
* Revision History :
* Version      Date            	Author       Reason
* 0.1          Jan 12, 2017      	595251  	 Initial version
*/

import java.io.IOException;
import java.util.List;

import com.bid.common.BidConstants;
import com.bid.common.util.BidUtil;
import com.bid.vo.BidsLog;
import com.cloudant.client.api.Database;
import com.google.gson.JsonObject;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

/**
 * @author 595251
 *
 */
public class SendGridMail {
	

	public static JsonObject main(JsonObject args) {
		
		System.out.println("..Processing SendMail...."+args);
		Database db = BidUtil.getConnection();
		List <BidsLog> bidsLogList = BidUtil.getBidsLogList(db);
        JsonObject response = new JsonObject();
        try {
        	System.out.println("..args...."+args);
        	
        	for (int i=0; bidsLogList !=null && i < bidsLogList.size() ; i++)
        	{
        		if (bidsLogList.get(i).getBidWinnerEmails() != null &&  !bidsLogList.get(i).getBidWinnerEmails().equals(""))
        				sendMail(bidsLogList.get(i).getBidWinnerEmails());
        				bidsLogList.get(i).setStatus(BidConstants.PROPERTY_STATE_PROCESSED);
        		
        				BidUtil.updateBidsLog(db, bidsLogList.get(i));
        	}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("..in exception....");
			e.printStackTrace();
		}
        response.addProperty(BidConstants.STATUS, BidConstants.STATUS_SUCCESS);
        return response;
	}
		
	
	


	


	/**
	 * @param  
	 * @return void
	 * @throws IOException 
	 *
	 */
	private static void sendMail(String toEmail) throws IOException {
		
		//System.out.println("1="+args.get("From_Email"));
		Email from = new Email(BidConstants.FROM_EMAIL_ID);
	    String subject = BidConstants.MAIL_SUBJECT;
	    Email to = new Email(toEmail);
	    
	    Content content = new Content("text/plain", BidConstants.MAIL_BODY);
	    Mail mail = new Mail(from, subject, to, content);

	    SendGrid sg = new SendGrid(BidConstants.SENDGRID_KEY);
	    System.out.println("..before sending....");
	    Request request = new Request();
	    try {
	      request.method = Method.POST;
	      request.endpoint = "mail/send";
	      request.body = mail.build();
	      Response response = sg.api(request);
	      System.out.println(response.statusCode);
	      System.out.println(response.body);
	      System.out.println(response.headers);
	      System.out.println("..mail sent....");
	    } catch (IOException ex) {
	      throw ex;
	    }
		
	}

}
