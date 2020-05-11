package hospital.service;

import java.util.List;

import hospital.enity.News;
import hospital.enity.User;

//@Transactional
public interface LoginService {

	List<User> logincheck(String name, String password);

	int registerUser(String name, String password, int roleId, String definition);

	void insertNews(String title, String publisher, String body, String time, int newsId);

	List<News> allInfo();

	List<News> watchNews(String newsId);

    int registerUser1(String name, String password, String tname, int roleId, String definition);

    boolean registercheck(String name);
}
