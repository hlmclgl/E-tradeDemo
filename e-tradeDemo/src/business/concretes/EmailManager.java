package business.concretes;

import business.abstracts.EmailService;

public class EmailManager implements EmailService{

	@Override
	public void sendMail(String message, String toWho) {
		System.out.println("Do�rulama e-postas�: " + message + " mesaj� " + toWho + " ki�isine g�nderildi.");
		
	}

}
