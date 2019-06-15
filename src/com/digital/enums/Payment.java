package com.digital.enums;

public enum Payment {

	SUCCESS("Success"), ABORTED("Aborted"), FAILURE("Failure"), OTHERS("Others");

	private String apiName;

	private Payment(String apiName) {
		this.apiName = apiName;
	}

	public String getApiName() {
		return this.apiName;
	}

	public static String getPaymentMessage(Payment payment) {
		String string = null;
		switch (payment) {
		case SUCCESS:
			string = "<br>Thank you for shopping with us. Your credit card has been charged and your transaction is successful. We will be shipping your order to you soon.";
			break;
		case ABORTED:
			string = "<br>Thank you for shopping with us.We will keep you posted regarding the status of your order through e-mail";
			break;
		case FAILURE:
			string = "<br>Thank you for shopping with us.However,the transaction has been declined.";
			break;
		default:
			string = "<br>Security Error. Illegal access detected";
			break;

		}
		return string;
	}
}
