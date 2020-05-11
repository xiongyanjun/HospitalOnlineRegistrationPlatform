package hospital.mapping;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import hospital.enity.News;
import hospital.enity.User;


public interface LoginMapper {

	List<User> logincheck(Map map);

	void registerUser(User user);

	void insertNews(Map<String, Object> map);

	List<News> allInfo();

	List<News> watchNews(@Param(value="newsId")String newsId);

    void registerUser1(User user);

	User findByName(String name);
}
