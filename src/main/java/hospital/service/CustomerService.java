package hospital.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import hospital.enity.DeptInfo;
import hospital.enity.Doctor;
import hospital.enity.User;
import hospital.enity.Yuyue;

public interface CustomerService {

	void saveUserInfo(String tname, String sex, String phone, int age, String email, String name, String password,Integer id);

	User queryUserInfo(String name, String password);

	PageInfo<Doctor> showdirector(int pageSiz,int pageNo);

	List<Doctor> searchDoctor(String department);

	PageInfo<Doctor> searchAllDoctor(int pageSiz,int pageNo);

	List<Doctor> yuyueSearchDoctor(String dname);

	Doctor queryDoc(int jnumber);

	void insertYuyue(int cid,int jnumber, String dname, String cname, String ydate, String ytime, String sex, String phone,
			String isok, int age, int yyh);

//	List<Yuyue> searchAllYuyue(String cname);

	List<Yuyue> searchYuyueOne(String cname, String isok);

	List<Yuyue> searchByDate(String cname, String date);

	void deleteYy(String yyh);

    Doctor searchDoctorByJnumber(String jnumber);

    PageInfo<User> showCustomer(int pageSize,int pageNo);

	User searchUserById(String id);

	PageInfo<Yuyue> searchAllYuyue(String cname, int pageSize, int pageNo);

    PageInfo<Doctor> showdirector1(int pageSize, int pageNo);

	PageInfo<Yuyue> searchAllYuyue1(String cid, int pageSize, int pageNo);

    List<DeptInfo> showDept();
}
