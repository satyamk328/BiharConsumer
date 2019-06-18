package com.digital.utils;
/**
 * @author Satyam Kumar
 *
 */
public class GlobalConstants {

	private GlobalConstants() {
	}

	public static final String ERROR_EMAIL = "satyamk328@gmail.com";
	public static final String ERROR_HEADER ="Your Application Error Alert";
	public static final String ERROR_BODY = "<html><body><p> Dear Customer,<br/> <br/> Your Error is :- <b> EEROR_VALUES <br/><p>For any clarifications contact us at +918130787891 (Any Time).</p><br/><br/>Thank you,<br>Team Digital Bihar</p></body></html>";

	public static final String OTP_HEADER = "Your OTP for new device sign-in";
	public static final String OTP_BODY = "<html><body><p> Dear USER_NAME,<br> <br> We detected that you are trying to sign-in into a new browser. Your <b>OTP</b> for logging in is : <b>OTP_VALUES</b><br>For security reasons, please do not share this OTP with anyone.<br><br>Thank you,<br>Team Digital Bihar</p></body></html>";
	
	public static final String REQUEST_HEADER_SERVICE_NAME = "X-SERVICE-NAME";
	
	public static final String ERROR_MESSAGE = "Currently this service is unavailable. We regret the inconvenience caused. Please try after some time.";
	public static final String BOOKING_CONFIRMATION_HEADER = "Booking Confirmation on DIGITALBIHAR, Bus Name: ${TRAVELNAME}, ${DEPARTUREDATE}, ${SOURCE} - ${DESTINATION}";
	public static final String CANCEL_TICKET = "Cancel Ticket";
	public static final int MAX_SHEET_ALLOWED_PER_BOOKING = 5;
	
	public static final String SEPARATOR = "::";
}
