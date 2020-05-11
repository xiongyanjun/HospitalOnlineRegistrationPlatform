package hospital.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import hospital.enity.DocInfo;
import hospital.enity.Doctor;
import hospital.enity.Yuyue;

public interface DoctorService {

	Doctor queryDoctorInfo(String name, String password);

	void saveDoctorInfo(int deptNo,String tname,int id,String department, String position, String phone, int age, String email,String sex, String name,
			String password);

	List<Yuyue> allYy(String name, String isok);

	void insertDocter(int uid);

	void yuyueSuccess(String yybh, String isok);

	void yuyueFail(String yybh, String isok);

	List<Yuyue> searchYyBydt(String name, String date);

	PageInfo<Yuyue> allJz(String name, String isok, int pageSize, int pageNo);

	List<Yuyue> allJiuJz(String name, String isok, String isok2);

	List<Doctor> queryByDepartment(String department);

    List<Yuyue> allYyByJnumber(int jnumber, String isok);

	PageInfo<Yuyue> allYy1(String name, String isok, int pageSize, int pageNo);

	DocInfo queryDoctorInfo1(int id);

	List<Doctor> queryByDeptNo(int deptNo);

	PageInfo<Yuyue> allYy2(int id, String isok, int pageSize, int pageNo);

	PageInfo<Yuyue> allJz1(int id, String isok, int pageSize, int pageNo);

	PageInfo<Yuyue> allJz3(String id, String isok, int pageSize, int pageNo);

	List<Yuyue> allYy3(int jnumber, String s);
}
