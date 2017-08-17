package tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.hssf.util.HSSFColor;

public class SendEmail {

	public static void PostMarkSendEmail() throws Exception {
		
	      String projectName=ExcelUtils.getCellData("MultipleBrowser",0, 1);
			String phase=ExcelUtils.getCellData("MultipleBrowser",1, 1);
			
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String dateMod=dateFormat.format(date);
		String totalsizes="";
		String to=ExcelUtils.getCellData("TOCC",0, 1);
		String cc=ExcelUtils.getCellData("TOCC",1, 1);
		System.out.println(to);
		System.out.println(cc);
		for(int i=4; i<= 13; i++){
			String sizes = ExcelUtils.getCellData("MultipleBrowser",i, 1);
			if(!(sizes.equals("")||sizes.equals(null)))
			{	System.out.println(sizes);
				
				if("<-DoNotDelete".equals(sizes))
				{
					break;
				}

				totalsizes=totalsizes+sizes+" ";
			}
		}
	
System.out.println(totalsizes);

	      
	      String from = "bannerreport@indegene.com"; 
	      String host = "192.0.0.59";//
	  
	     //Get the session object  
	      Properties properties = System.getProperties();  
	      properties.setProperty("mail.smtp.host", host);  
	      Session session = Session.getDefaultInstance(properties);
	  	MimeMessage message = new MimeMessage(session);  
	    message.setFrom(new InternetAddress(from));  
	      if(to.equals("")||to.equals(null))
	      {
	    	  System.out.println("To field is blank");
	      }
	      else
	      {
	    	  String[] toRecipientList =to.split(";");
	    	  InternetAddress[] toRecipientAddress = new InternetAddress[toRecipientList.length];
			  int toCounter = 0;
			  List<InternetAddress> list = new ArrayList<InternetAddress>();
				for (String toRecipient : toRecipientList) {
		      		toRecipientAddress[toCounter] = new InternetAddress(toRecipient.trim());
		      	    list.add(toRecipientAddress[toCounter]);
		      	    toCounter++;
		      	} 
		          message.setFrom(new InternetAddress(from));  
		          message.setRecipients(Message.RecipientType.TO,toRecipientAddress);  
	      }
	        
	        if(cc.equals(null)||cc.equals(""))
	        {
	        	System.out.println("CC field is blank");
	        }
	        else{
			String[] ccRecipientList =cc.split(";");
			InternetAddress[] ccRecipientAddress = new InternetAddress[ccRecipientList.length];
			int ccCounter = 0;
			List<InternetAddress> list = new ArrayList<InternetAddress>();
		 	for (String ccRecipient : ccRecipientList) {
	      		ccRecipientAddress[ccCounter] = new InternetAddress(ccRecipient.trim());
	      	    list.add(ccRecipientAddress[ccCounter]);
	      	    ccCounter++;
	      	}
		
	          message.setRecipients(Message.RecipientType.CC,ccRecipientAddress);
	        }
 	
	      try{  
	          
	          
	          String status;
				if(Filter.flag==1)
	        	  {
	       	      message.setSubject("Banner Automation Status - "+projectName+" - "+phase+" - "+dateMod+" - [FAIL]");
	       	   status="<html><font color='red'>FAIL</font></html>";
	        	  }
	        	  else
	        	  {
	        		  message.setSubject("Banner Automation Status - "+projectName+" - "+phase+" - "+dateMod+" - [PASS]");
	        		  status="<html><font color='green'>PASS</font></html>";
	        	  }
				

	               
				MimeMultipart multipart = new MimeMultipart(); 
	               MimeBodyPart attachPart = new MimeBodyPart();
	               String attachFile = "\\Banner_Auto_Old\\Banner_Automation\\Output\\Output.xls";
	               attachPart.attachFile(attachFile);
	               multipart.addBodyPart(attachPart);
	               
	               String body="Team,<br><br> Validation team has successfully executed all planned regression test scripts for the  Banner <b>"+ projectName+"</b>. Find the detailed insights below.</br><br>Highlights:</br><br>";       
	               body+="<table style=width:50% border=1 cellspacing=0 cellpadding=0><tr><td><b>Banner Name</b></td> <td>"+projectName+"</td></tr><tr><td><b>Phase</b></td> <td>"+phase+"</td></tr><tr><td><b>Execution Date</b></td> <td>"+dateMod+"</td></tr><tr><td><b>Sizes Tested</b></td> <td>"+totalsizes+"</td></tr><tr><td><b>Browsers Tested</b></td> <td>"+ParallelTest.version1+", "+ParallelTest.version2+", "+ParallelTest.version3+"</td></tr><tr><td><b>STATUS</b></td> <td>"+status+"</td></tr></tbody></table>";
	               body+="</br><br>Find the email attachment for the detailed Banner test case report.</br><br><b>Note:</b> This is an automated mail. Do not reply to this mail.</br><br>Regards,<br>Banner Validation Team</br>";

	               BodyPart htmlBodyPart = new MimeBodyPart(); 
	               htmlBodyPart.setContent(body, "text/html"); 
                 multipart.addBodyPart(htmlBodyPart);
                     message.setContent(multipart); 
  
	   
	          Transport.send(message);  
	          System.out.println("message sent successfully....");  
	   
	       }catch (MessagingException mex) {System.out.println("Message failed to deliver");;}  
	}
	
}