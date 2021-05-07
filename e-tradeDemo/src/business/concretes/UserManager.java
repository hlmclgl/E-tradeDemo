package business.concretes;

import java.util.List;

import business.abstracts.UserService;
import dataAccess.abstracts.UserDao;
import entities.concretes.User;

public class UserManager implements UserService{
	
	private UserDao userDao;

	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public void add(User user) {
		userDao.add(user);
		
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
		
	}

	@Override
	public void update(User user) {
		userDao.update(user);
		
	}

	@Override
	public User getbyid(int id) {
		
		return userDao.getbyid(id);
	}

	@Override
	public User getbymail(String mail) {
		// TODO Auto-generated method stub
		return userDao.getbymail(mail);
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userDao.getAll();
	}

	@Override
	public User getByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return userDao.getByEmailAndPassword(email, password);
	}

	@Override
	public void verifyUser(int id) {
		User user = userDao.getbyid(id);
		user.setVerified(true);
		System.out.println("Kullanýcý baþarýyla doðrulandý.. ");
		
	}



}
