package hospital.mapping;

import java.util.List;
import java.util.Map;

import hospital.enity.DocInfo;
import org.apache.ibatis.annotations.Param;

import hospital.enity.Doctor;
import hospital.enity.User;
import hospital.enity.Yuyue;

public interface CustomerMapper {

	void saveUserInfo(Map map);

	User queryUserInfo(@Param(value="name")String name, @Param(value="password")String password);

	List<Doctor> showdirector();

	List<Doctor> searchDoctor(@Param(value="department")String department);

	List<Doctor> searchAllDoctor();

	List<Doctor> yuyueSearchDoctor(@Param(value="dname")String dname);

	DocInfo queryDoc(@Param(value="jnumber")int jnumber);

	void insertYuyue(Map map);

	List<Yuyue> searchAllYuyue(@Param(value="cname")String cname);

	List<Yuyue> searchYuyueOne(Map map);

	List<Yuyue> searchByDate(Map map);

    void deleteYy(String yyh);

    Doctor searchDoctorByJnumber(String jnumber);

    List<User> searchUserByRole(String s);

	User searchUserById(String id);

    List<Doctor> showdirector1();

    List<Yuyue> searchAllYuyue1(String cid);
}
