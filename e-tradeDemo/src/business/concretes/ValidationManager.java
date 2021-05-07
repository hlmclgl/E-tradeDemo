package business.concretes;

import java.util.regex.Pattern;

import business.abstracts.ValidationService;
import core.utils.ValidationRules;
import entities.concretes.User;

public class ValidationManager implements ValidationService{
	
	// w3schools java regular expressions yazarak ulaþabilirsin.
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	

	@Override
	public boolean validate(User user) {
		boolean result = ValidationRules.run(isEmailFormatValidated(user.getEmail()), isFirstNameFormatValidated(user.getFirstName()), 
				isLastNameFormatValidated(user.getLastName()), isPasswordFormatValidated(user.getPassword()));
		return result;
	}
	
	private boolean isEmailFormatValidated(String email) {
		return VALID_EMAIL_ADDRESS_REGEX.matcher(email).find();
	}
	
	private boolean isPasswordFormatValidated(String password) {
		return password.length() > 6; 
	}
	
	private boolean isFirstNameFormatValidated(String firstName) {
		return firstName.length() > 2;
	}
	
	private boolean isLastNameFormatValidated(String lastName) {
		return lastName.length() > 2;
	}

}
