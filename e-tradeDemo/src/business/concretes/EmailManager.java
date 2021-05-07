package business.concretes;

import business.abstracts.EmailService;

public class EmailManager implements EmailService{

	@Override
	public void sendMail(String message, String toWho) {
		System.out.println("Doðrulama e-postasý: " + message + " mesajý " + toWho + " kiþisine gönderildi.");
		
	}

}
