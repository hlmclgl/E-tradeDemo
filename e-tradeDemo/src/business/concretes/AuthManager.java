package business.concretes;

import business.abstracts.*;
import core.utils.BusinessRules;
import entities.concretes.User;

public class AuthManager implements AuthService {

	UserService userService;
	EmailService emailService;
	ValidationService validationService;

	public AuthManager(UserService userService, EmailService emailService, ValidationService validationService) {
		super();
		this.userService = userService;
		this.emailService = emailService;
		this.validationService = validationService;
	}

	@Override
	public void register(int id, String email, String password, String firstName, String lastName) {
		// TODO Auto-generated method stub

		User userForRegister = new User(id, email, password, firstName, lastName, false);
		if (validationService.validate(userForRegister)) {
			System.out.println("Kayýt iþlemi baþarýsýz. Lütfen tüm alanlarý doðru girdiðinizden emin olunuz.");
			return;
		}
		
		
		if (!BusinessRules.run(checkiIfUserExist(email))) {
			System.out.println("Kayýt iþlemi baþarýsýz. Ayný e-posta ile birden fazla hesap oluþturamazsýnýz.");
		}
		
		
		System.out.println(
				"Baþarýyla kayýt oldunuz. Üyeliðinizi doðrulamak için lütfen e-posta adresinizi kontrol ediniz.");
		emailService.sendMail(
				"Bu bir doðrulama mesajýdýr. Hesabýnýzý doðrulamak için lütfen linke týklayýn. https://www.linkedin.com/in/ahmet-hilmi-%C3%A7ilo%C4%9Flu-884012203/",
				userForRegister.getFirstName());
		userService.add(userForRegister);

	}

	@Override
	public void login(String email, String password) {
		User userForLogin = userService.getByEmailAndPassword(email, password);

		if (!BusinessRules.run(checkIfFieldsFilled(email, password))) {
			System.out.println("Giriþ baþarýsýz. Tüm bilgileri doldurduðunuzdan emin olun.");
			return;
		} else if (userForLogin == null) {
			System.out.println("Giriþ baþarýsýz. E-posta adresiniz veya þifreniz yanlýþ.");
			return;
		} else if (!checkIsUserVerified(userForLogin)) {
			System.out.println("Giriþ baþarýsýz. Üyeliðinizi doðrulamadýnýz. Lütfen hesabýnýzý doðrulayýn.");
		} else {
			System.out.println(
					"Giriþ baþarýlý. Hoþgeldiniz : " + userForLogin.getFirstName() + " " + userForLogin.getLastName());
		}

	}

	private boolean checkiIfUserExist(String email) {
		return userService.getbymail(email) == null;
	}

	private boolean checkIsUserVerified(User user) {
		return user.isVerified();
	}

	private boolean checkIfFieldsFilled(String email, String password) {

		if (email.length() <= 5 || password.length() <= 5) {
			return false;
		}

		return true;
	}

}
