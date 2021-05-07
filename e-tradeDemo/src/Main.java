import business.abstracts.AuthService;
import business.abstracts.UserService;
import business.concretes.AuthManager;
import business.concretes.EmailManager;
import business.concretes.UserManager;
import business.concretes.ValidationManager;
import core.googleUsersAdaptor.GoogleAuthManagerAdapter;
import dataAccess.concretes.InMemoryDao;

public class Main {

	public static void main(String[] args) {
		
		UserService userService = new UserManager(new InMemoryDao());
		AuthService authService = new AuthManager(userService, new EmailManager(), new ValidationManager());
		
		authService.register(1, "hlmclgl@gmail.com", "1234567hlm", "Ahmet Hilmi", "�ilo�lu");
		//authService.register(2, "emiposta@posta.com", "1547", "Bir", "Ki�i");//�ifre do�rulamadan ge�emedi
		//authService.register(3, "emiposta", "15474sd5s", "Bir ba�ka", "Ki�i");//e posta do�rulamadan ge�medi
		//authService.register(4, "hlmclgl@gmail.com", "1234567hlm", "Ahmet Hilmi", "�ilo�lu");//e posta mevcut
		
		authService.login("hlmclgl@gmail.com", "1234567hlm");
		userService.verifyUser(1);
		authService.login("hlmclgl@gmail.com", "1234567hlm");
		
		AuthService google = new GoogleAuthManagerAdapter();
		google.register(5, "hlmclgl1@gmail.com", "154784124", "Ahmet Hilmi", "�ilo�lu");
		google.login("hlmclgl@gmail.com", "154784124");
		

	}

}
