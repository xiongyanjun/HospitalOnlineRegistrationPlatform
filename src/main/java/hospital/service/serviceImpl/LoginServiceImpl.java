package hospital.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hospital.mapping.LoginMapper;
import hospital.enity.News;
import hospital.enity.User;
import hospital.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginMapper loginDao;

	@Override
	public List<User> logincheck(String name, String password) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("name", name);
		map.put("password", password);
		return loginDao.logincheck(map);
	}

	@Override
	public int registerUser(String name, String password, int roleId, String definition) {
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setRoleId(roleId);
		user.setDefinition(definition);
		loginDao.registerUser(user);
		return user.getId();
	}

	@Override
	public void insertNews(String title, String publisher, String body, String time, int newsId) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("title",title );
		map.put("publisher",publisher );
		map.put("body",body );
		map.put("time", time);
		map.put("newsId", newsId);
		loginDao.insertNews(map);
	}

	@Override
	public List<News> allInfo() {
		return loginDao.allInfo();
	}

	@Override
	public List<News> watchNews(String newsId) {
		return loginDao.watchNews(newsId);
	}

	@Override
	public int registerUser1(String name, String password, String tname, int roleId, String definition) {
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setRoleId(roleId);
		user.setDefinition(definition);
		user.setTname(tname);

		loginDao.registerUser1(user);
		return user.getId();
	}

	@Override
	public boolean registercheck(String name) {
		User user = loginDao.findByName(name);
		if (user==null){
			return false;
		}
		return true;
	}

}
