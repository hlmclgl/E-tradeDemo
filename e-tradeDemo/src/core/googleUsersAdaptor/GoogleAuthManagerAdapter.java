package core.googleUsersAdaptor;

import business.abstracts.AuthService;

public class GoogleAuthManagerAdapter implements AuthService {

	@Override
	public void register(int id, String email, String password, String firstName, String lastName) {
		GoogleAuthManager manager = new GoogleAuthManager();
		manager.register(email, password);
		
	}

	@Override
	public void login(String email, String password) {
		GoogleAuthManager manager = new GoogleAuthManager();
		manager.login(email, password);
		
	}

}
