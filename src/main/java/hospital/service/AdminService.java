package hospital.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import hospital.enity.Hospital;
import hospital.enity.News;
import hospital.enity.Yuyue;

public interface AdminService {

	Hospital queryHospitalInfo();

	void updateHospitalInfo(String name, String address, String email, String telephone, String youbian, String header,
			String buildTime);

	PageInfo<Yuyue> huanzheGuanli(int pageSize,int pageNo);

	List<Yuyue> queryBySelect(String isok, String department);

	List<Yuyue> searchByDt(String date);

	List<Yuyue> queryByIsok(String isok);

	List<Yuyue> queryByIsD(String isok, String date);

	List<Yuyue> queryByAll(String isok, String date,String department);

	List<Yuyue> queryByDepartment(String department);

	List<Yuyue> queryByDeD(String department,String date);

	PageInfo<News> newsInfo(int pageSize,int pageNo);

	void deleteNews(int newsId);

	void updateDoctorByJnumber(int pid,String jnumber, String department, String name, String email, String phone, String summary, String sex, String age,String position);

	void deleteDoctor(String dname);

	void deleteUser(String dname);

    void updateUserById(String id, String tname, String email, String phone, String sex, String age);

    void deleteCustomerById(String id);

    int[] countYy();
}
