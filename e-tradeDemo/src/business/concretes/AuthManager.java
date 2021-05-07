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
			System.out.println("Kay�t i�lemi ba�ar�s�z. L�tfen t�m alanlar� do�ru girdi�inizden emin olunuz.");
			return;
		}
		
		
		if (!BusinessRules.run(checkiIfUserExist(email))) {
			System.out.println("Kay�t i�lemi ba�ar�s�z. Ayn� e-posta ile birden fazla hesap olu�turamazs�n�z.");
		}
		
		
		System.out.println(
				"Ba�ar�yla kay�t oldunuz. �yeli�inizi do�rulamak i�in l�tfen e-posta adresinizi kontrol ediniz.");
		emailService.sendMail(
				"Bu bir do�rulama mesaj�d�r. Hesab�n�z� do�rulamak i�in l�tfen linke t�klay�n. https://www.linkedin.com/in/ahmet-hilmi-%C3%A7ilo%C4%9Flu-884012203/",
				userForRegister.getFirstName());
		userService.add(userForRegister);

	}

	@Override
	public void login(String email, String password) {
		User userForLogin = userService.getByEmailAndPassword(email, password);

		if (!BusinessRules.run(checkIfFieldsFilled(email, password))) {
			System.out.println("Giri� ba�ar�s�z. T�m bilgileri doldurdu�unuzdan emin olun.");
			return;
		} else if (userForLogin == null) {
			System.out.println("Giri� ba�ar�s�z. E-posta adresiniz veya �ifreniz yanl��.");
			return;
		} else if (!checkIsUserVerified(userForLogin)) {
			System.out.println("Giri� ba�ar�s�z. �yeli�inizi do�rulamad�n�z. L�tfen hesab�n�z� do�rulay�n.");
		} else {
			System.out.println(
					"Giri� ba�ar�l�. Ho�geldiniz : " + userForLogin.getFirstName() + " " + userForLogin.getLastName());
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
