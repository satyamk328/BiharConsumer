package com.digital.utils;

import com.digital.model.vo.SeatDataToOperate;
import com.digital.model.vo.TicketVO;

public interface MailServiceUtils {

	public String HEADER = "<html><body><div id=\"m_1093122822946983907ircp_confirmation_div\">\r\n" + 
			"         <table width=\"100%\">\r\n" + 
			"            <tbody>\r\n" + 
			"               <tr>\r\n" + 
			"                  <td colspan=\"2\" style=\"font:12px arial;color:#333;text-align:justify\">\r\n" + 
			"                     <hr>\r\n" + 
			"                     <b>This is a system generated mail. Please do not reply to this email ID. (1) Call our 24-hour Customer Care (2) Email Us at <a href=\"mailto:www.digitalbihar@gmail.com\" target=\"_blank\">www.digitalbihar@gmail.com</a> </b> <br>\r\n" + 
			"                     <hr>\r\n" + 
			"                  </td>\r\n" + 
			"               </tr>\r\n" + 
			"               <tr>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:12px arial\">\r\n" + 
			"                     <div style=\"font:bold 14px arial;color:#0099ff;margin:0;padding:0\"><span>Ticket Confirmation</span></div>\r\n" + 
			"                  </td>\r\n" + 
			"                  <td style=\"font:bold 14px arial;color:#0099ff;border-bottom:1px solid #ccc\"><img alt=\"DIGITALBIHAR\" style=\"float:right\"></td>\r\n" + 
			"               </tr>\r\n" + 
			"            </tbody>\r\n" + 
			"         </table>";
	public String BODY = "<p><b>Dear ${CUSTOMERNAME},</b></p>\r\n" + 
			"         <p style=\"text-align:justify;color:#000000;text-align:justify\">Congratulations! Thank you for using DIGITALBIHAR's online booking facility. Your booking details are indicated below.</p>\r\n" + 
			"         <table width=\"100%\" cellpadding=\"5\">\r\n" + 
			"            <tbody>\r\n" + 
			"               <tr>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Ticket No. :</b></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>${TICKETID}</span></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Bus No. / Name : </b></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>${BUSNUMBER} / ${TRAVELNAME}</span></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Quota : </b> </td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>Normal </span> </td>\r\n" + 
			"               </tr>\r\n" + 
			"               <tr></tr>\r\n" + 
			"               <tr>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Transaction ID : </b></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"> <span>${TRANSACIONID}</span></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Date &amp; Time of Booking : </b></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>${BOOKINGDATE} HRS</span></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Class : </b></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>Normal</span></td>\r\n" + 
			"               </tr>\r\n" + 
			"               <tr>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>From : </b> </td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>${SOURCENAME}</span></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Date of Journey : </b></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>${DESTINATIONDATE}</span></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>To : </b> </td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>${DESTINATIONNAME}</span></td>\r\n" + 
			"               </tr>\r\n" + 
			"               <tr>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Date Of Boarding : </b> </td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>${DESTINATIONDATE}</span> </td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Scheduled Departure* : </b> </td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>${DESTINATIONDATE}</span></td>\r\n" + 
			"				  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Scheduled Arrival :</b></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>22-Feb-2019 01:01</span></td>\r\n" + 
			"               </tr>\r\n" + 
			"                           \r\n" + 
			"               <tr>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Passenger Mobile No :</b></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>${PHONE}</span></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Distance :</b></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>${DISTANCE} KM</span></td>\r\n" + 
			"                 <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Adult: </td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>${AUDITCOUNT}</span> </td>\r\n" + 
			"               </tr>\r\n" + 
			"               <tr></tr>\r\n" + 
			"            </tbody>\r\n" + 
			"         </table>\r\n" + 
			"         <br>\r\n" + 
			"         <div style=\"font:bold 14px arial;color:#0099ff;margin:0;padding:0\"><span>Passenger Details</span></div>\r\n" + 
			"         <hr>\r\n" + 
			"         <table width=\"100%\" cellpadding=\"5\">\r\n" + 
			"            <tbody>\r\n" + 
			"               <tr align=\"left\" style=\"background-color:#f0f3ff\">\r\n" + 
			"                  <td style=\"font-size:11px\"><b>Sl. No.</b></td>\r\n" + 
			"                  <td style=\"font-size:11px\"><b>Name</b></td>\r\n" + 
			"                  <td style=\"font-size:11px\"><b>Age</b></td>\r\n" + 
			"                  <td style=\"font-size:11px\"><b>Gender</b></td>\r\n" + 
			"                  <td style=\"font-size:11px\"><b>Status</b></td>\r\n" + 
			"                  <td style=\"font-size:11px\"><b>Coach</b></td>\r\n" + 
			"                  <td style=\"font-size:11px\"><b>Seat / Berth / WL No</b></td>\r\n" + 
			"               </tr>";
	public static final String PESSENGER_DETAILS ="<tr>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\">${SNO}</td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\">${CUSTNAME}</td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\">${AGE}</td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\">${GENDER}</td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\">CNF</td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\">${COACH}&nbsp;</td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\">${SEATNO}&nbsp;</td>\r\n" + 
			"               </tr>";
public static final String FOOTER ="</tbody>\r\n" + 
		"         </table>\r\n" + 
		"         <br>\r\n" + 
		"         <div style=\"font:bold 14px arial;color:#0099ff;margin:0;padding:0\"><span>Fare Details (Inclusive of GST)</span></div>\r\n" + 
		"         <hr>\r\n" + 
		"         <table width=\"100%\" cellpadding=\"5\">\r\n" + 
		"            <tbody>\r\n" + 
		"               <tr align=\"left\" style=\"background-color:#f0f3ff\">\r\n" + 
		"                  <td style=\"font-size:11px\"><b>Ticket Fare</b></td>\r\n" + 
		"                  <td style=\"font-size:11px\"><b>Service Charge</b></td>\r\n" + 
		"                  <td style=\"font-size:11px\"><b>Travel Insurance Premium</b></td>\r\n" + 
		"                  <td style=\"font-size:11px\"><b>Total Fare</b></td>\r\n" + 
		"               </tr>\r\n" + 
		"               <tr>\r\n" + 
		"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\">Rs. ${TOTALFARE}.00 </td>\r\n" + 
		"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\">Rs. 0.00</td>\r\n" + 
		"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\">Rs. 0.0 </td>\r\n" + 
		"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\">Rs. ${TOTALFARE} *</td>\r\n" + 
		"               </tr>\r\n" + 
		"            </tbody>\r\n" + 
		"         </table>\r\n" + 
		"         <span style=\"font-size:11px;background-color:white\">\r\n" + 
		"            <b>* Payment Gateway charges as applicable.</b>\r\n" + 
		"            <span>\r\n" + 
		"               <br>\r\n" + 
		"               <hr>\r\n" + 
		"              \r\n" + 
		"               <hr>\r\n" + 
		"               <span style=\"text-align:justify;font:bold 14px arial;color:#0099ff;margin:0;padding:0\">Must Read</span>\r\n" + 
		"               <hr>\r\n" + 
		"               <ul>\r\n" + 
		"                  <li><span style=\"text-align:justify;color:#000000\">Please take a screenshot of ERS i.e. Virtual Reservation Message(VRM) OF YOUR TICKET FROM YOUR Booked Tickets History page .You have to carry this VRM or SMS send to you along with any Govt. authorized ID Card during train journey in original. Both theSMS(or VRM)&amp; original ID will be examined by ticket checking staff on stations/trains for verification purpose. <a href=\"http://contents.irctc.co.in/en/listOfGovtAuthorizedIDCards.pdf\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://contents.irctc.co.in/en/listOfGovtAuthorizedIDCards.pdf&amp;source=gmail&amp;ust=1552710365811000&amp;usg=AFQjCNEuinoLOikZ2PwOy2aReykIxUGrIA\">List of Govt. authorized ID Cards permissible for undertaking journey on reserved tickets.</a></span></li>\r\n" + 
		"                  <li><span style=\"text-align:justify;color:#000000\">This ticket is booked on a personal user ID and can not be sold by an agent. If bought from an agent by any individual, it is at his/her own risk</span></li>\r\n" + 
		"                  <li><span style=\"text-align:justify;color:#000000\">Passengers are advised not to carry inflammable/dangerous/<wbr>explosive articles as part of their luggage and also to desist from smoking in the trains.</span></li>\r\n" + 
		"               </ul>\r\n" + 
		"               <span style=\"text-align:justify;font:bold 14px arial;color:#0099ff;margin:0;padding:0\">Customer Care</span>\r\n" + 
		"               <hr>\r\n" + 
		"               <ul>\r\n" + 
		"                  <li><span style=\"text-align:justify;color:#000000\"> For any further assistance, please contact us at 24*7 Hrs.Customer Support at <b> +91-8800359490, +91-7004525434 (Language: Hindi and English) </b> or mail us at <a href=\"mailto:www.digitalbihar@gmail.com\" target=\"_blank\">www.digitalbihar@gmail.com</a>.</span></li>\r\n" + 
		"                  <li><span style=\"text-align:justify;color:#757575\">For any enquiries or information regarding your transaction with DIGITALBIHAR, do not provide your credit/debit card details by any means to DIGITALBIHAR. All your queries can be replied on the basis of 15 digit DIGITALBIHAR Transaction id/ 10 digit Ticket no./ User id. DIGITALBIHAR does not store the credit/ debit card information in any form during the transaction.</span></li>\r\n" + 
		"               </ul>\r\n" + 
		"               <p style=\"color:#757575\"><b>******************************<wbr>******************************<wbr>**********************</b></p>\r\n" + 
		"               <p style=\"color:#0099ff;font:bold 14px arial\"><b>Please don't print unless extremely necessary.</b></p>\r\n" + 
		"               <p><b>Warm Regards,<br>Customer Care<br>Internet Ticketing<br>DIGITALBIHAR</b></p>\r\n" + 
		"               <div class=\"yj6qo\"></div>\r\n" + 
		"               <div class=\"adL\"><br></div>\r\n" + 
		"            </span>\r\n" + 
		"         </span>\r\n" + 
		"      </div>\r\n" + 
		"   </body>\r\n" + 
		"</html>";
	public static String generateBookingTicketMail(TicketVO ticketVO) {
		StringBuilder builder = new StringBuilder();
		builder.append(HEADER);
		String body = BODY.replace("${CUSTOMERNAME}", ticketVO.getEmail().split("@")[0])
				.replace("${TICKETID}", ticketVO.getPnr())
				.replace("${TRAVELNAME}", ticketVO.getTravelName())
				.replace("${TRANSACIONID}", ticketVO.getTransactionId())
				.replace("${BOOKINGDATE}", ticketVO.getBookingDate().toString())
				.replace("${SOURCENAME}", ticketVO.getSrcCityName())
				.replace("$${DEPARTUREDATE}", ticketVO.getDepartureDate())
				.replace("$${DESTINATIONNAME}", ticketVO.getDestCityName())
				.replace("${DESTINATIONDATE}", ticketVO.getDepartureDate())
				.replace("$${DESTINATIONDATE}", ticketVO.getDepartureDate()+" "+ticketVO.getDepartureTime())
				.replace("$${ARRIVALDATETIME}", ticketVO.getArrivalDate()+" "+ticketVO.getArrivalTime())
				.replace("$${PHONE}", ticketVO.getPhone().toString())
				.replace("$${DISTANCE}", ticketVO.getDistance().toString())
				.replace("$${AUDITCOUNT}", String.valueOf(ticketVO.getSeatDataToOperate().size()));
		builder.append(body);
		int i =1;
		for(SeatDataToOperate operate : ticketVO.getSeatDataToOperate()) {
			String passenger = PESSENGER_DETAILS.replace("${SNO}", String.valueOf(i))
					.replace("${CUSTNAME}", operate.getCustName())
					.replace("${AGE}", String.valueOf(operate.getAge()))
					.replace("${GENDER}", operate.getGender())
					.replace("${COACH}", operate.getIsLowerBerth() ? "Lower" :"Upper")
					.replace("${SEATNO}", operate.getSeatNumber());
			builder.append(passenger);
			
		}
		String footer = FOOTER.replace("${TOTALFARE}", String.valueOf(ticketVO.getTotalFare()));
		builder.append(footer);
		return builder.toString();
	}
}
