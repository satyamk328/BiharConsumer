package com.digital.utils;

public interface MailServiceUtils {

	public String HEADER = "<html>\r\n" + "   <body>\r\n"
			+ "      <div id=\"m_1093122822946983907ircp_confirmation_div\">\r\n"
			+ "         <table width=\"100%\">\r\n" + "            <tbody>\r\n" + "               <tr>\r\n"
			+ "                  <td colspan=\"2\" style=\"font:12px arial;color:#333;text-align:justify\">\r\n"
			+ "                     <hr>\r\n"
			+ "                     <b>This is a system generated mail. Please do not reply to this email ID. (1) Call our 24-hour Customer Care (2) Email Us at <a href=\"mailto:www.digitalbihar@gmail.com\" target=\"_blank\">www.digitalbihar@gmail.com</a> </b> <br>\r\n"
			+ "                     <hr>\r\n" + "                  </td>\r\n" + "               </tr>\r\n"
			+ "               <tr>\r\n"
			+ "                  <td style=\"border-bottom:1px solid #ccc;font:12px arial\">\r\n"
			+ "                     <div style=\"font:bold 14px arial;color:#0099ff;margin:0;padding:0\"><span>Ticket Confirmation</span></div>\r\n"
			+ "                  </td>\r\n"
			+ "                  <td style=\"font:bold 14px arial;color:#0099ff;border-bottom:1px solid #ccc\"><img alt=\"DIGITALBIHAR\" style=\"float:right\"></td>\r\n"
			+ "               </tr>\r\n" + "            </tbody>\r\n" + "         </table>";
	public String BODY = "<p><b>Dear ${CUSTOMERNAME},</b></p>\r\n" + 
			"         <p style=\"text-align:justify;color:#000000;text-align:justify\">Congratulations! Thank you for using DIGITALBIHAR's online booking facility. Your booking details are indicated below.</p>\r\n" + 
			"         <table width=\"100%\" cellpadding=\"5\">\r\n" + 
			"            <tbody>\r\n" + 
			"               <tr>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Ticket No. :</b></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>2458453288</span></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Bus No. / Name : </b></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>12398 / Maa Travel</span></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Quota : </b> </td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>Normal </span> </td>\r\n" + 
			"               </tr>\r\n" + 
			"               <tr></tr>\r\n" + 
			"               <tr>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Transaction ID : </b></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"> <span>100001690875412</span></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Date &amp; Time of Booking : </b></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>20-Feb-2019 10:01:44 HRS</span></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Class : </b></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>Normal</span></td>\r\n" + 
			"               </tr>\r\n" + 
			"               <tr>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>From : </b> </td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>NEW DELHI (NDLS)</span></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Date of Journey : </b></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>21-Feb-2019</span></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>To : </b> </td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>SASARAM (SSM)</span></td>\r\n" + 
			"               </tr>\r\n" + 
			"               <tr>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Boarding At : </b> </td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>NDLS</span></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Date Of Boarding : </b> </td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>21-Feb-2019</span> </td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Scheduled Departure* : </b> </td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>21-Feb-2019 12:10</span></td>\r\n" + 
			"               </tr>\r\n" + 
			"               <tr>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Reservation Up to :</b></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>SASARAM ( SSM)</span></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Scheduled Arrival :</b></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>22-Feb-2019 01:01</span></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Adult: </b>&nbsp;2</td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:12px arial\"><b>Child: </b> &nbsp;0</td>\r\n" + 
			"               </tr>\r\n" + 
			"               <tr>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Passenger Mobile No :</b></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>8130787891</span></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Distance :</b></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>886KM</span></td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b>Insurance (No. of Psng) : </b> </td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span>2</span> </td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><b></b> </td>\r\n" + 
			"                  <td style=\"border-bottom:1px solid #ccc;font:11px arial\"><span></span> </td>\r\n" + 
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

	public static String generateBookingTicketMail() {
		StringBuilder builder = new StringBuilder();
		builder.append(HEADER);
		return builder.toString();
	}
}
