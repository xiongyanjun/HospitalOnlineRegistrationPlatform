package hospital.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hospital.enity.DocInfo;
import hospital.mapping.CustomerMapper;
import hospital.mapping.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hospital.mapping.AdminMapper;
import hospital.enity.Hospital;
import hospital.enity.News;
import hospital.enity.Yuyue;
import hospital.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public Hospital queryHospitalInfo() {
		// TODO Auto-generated method stub
		return adminMapper.queryHospitalInfo();
	}

	@Override
	public void updateHospitalInfo(String name, String address, String email, String telephone, String youbian,
			String header, String buildTime) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("name", name);
		map.put("address", address);
		map.put("email", email);
		map.put("telephone", telephone);
		map.put("youbian", youbian);
		map.put("header", header);
		map.put("buildTime", buildTime);
		adminMapper.updateHospitalInfo(map);
	}

	@Override
	public PageInfo<Yuyue> huanzheGuanli(int pageSize, int pageNo) {
		PageHelper.startPage(pageNo,pageSize);
		List<Yuyue> yuyues = adminMapper.huanzheGuanli();
		PageInfo<Yuyue> pageInfo = new PageInfo<Yuyue>(yuyues);
		return pageInfo;
	}


	@Override
	public List<Yuyue> queryBySelect(String isok, String department) {
		// TODO Auto-generated method stub
		return adminMapper.queryBySelect(isok,department);
	}

	@Override
	public List<Yuyue> searchByDt(String date) {
		// TODO Auto-generated method stub
		return adminMapper.searchByDt(date);
	}

	@Override
	public List<Yuyue> queryByIsok(String isok) {
		// TODO Auto-generated method stub
		return adminMapper.queryByIsok(isok);
	}

	@Override
	public List<Yuyue> queryByIsD(String isok, String date) {
		// TODO Auto-generated method stub
		return adminMapper.queryByIsD(isok,date);
	}

	@Override
	public List<Yuyue> queryByAll(String isok, String date,String department) {
		// TODO Auto-generated method stub
		return adminMapper.queryByAll(isok,date,department);
	}

	@Override
	public List<Yuyue> queryByDepartment(String department) {
		// TODO Auto-generated method stub
		return adminMapper.queryByDepartment(department);
	}

	@Override
	public List<Yuyue> queryByDeD(String department,String date) {
		// TODO Auto-generated method stub
		return adminMapper.queryByDeD(department,date);
	}

	@Override
	public PageInfo<News> newsInfo(int pageSize, int pageNo) {
		PageHelper.startPage(pageNo,pageSize);
		List<News> yuyues = adminMapper.newsInfo();
		PageInfo<News> pageInfo = new PageInfo<News>(yuyues);
		return pageInfo;
	}

//	@Override
//	public List<News> newsInfo() {
//		// TODO Auto-generated method stub
//		return adminMapper.newsInfo();
//	}

	@Override
	public void deleteNews(int newsId) {
		// TODO Auto-generated method stub
		adminMapper.deleteNews(newsId);
	}

	@Override
	public void updateDoctorByJnumber(int pid,String jnumber, String department, String name, String email, String phone, String summary, String sex, String age,String position) {
		Map<String, Object> map1=new HashMap<String, Object>();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("age", age);
		map.put("tname", name);
		map1.put("jnumber", jnumber);
		map.put("email", email);
		map.put("phone",phone);
		map1.put("department", department);
		map1.put("summary", summary);
		map.put("sex", sex);
		map1.put("position", position);
		map1.put("pid", pid);
		DocInfo docInfo = adminMapper.findDocByJnumber(jnumber);
		map.put("id",docInfo.getUid());
		customerMapper.saveUserInfo(map);

		adminMapper.updateDoctorByJnumber(map1);
	}

	@Override
	public void deleteDoctor(String uid) {
		adminMapper.deleteDoctor(uid);
	}

	@Override
	public void deleteUser(String dname) {
		adminMapper.deleteUser(dname);
	}

	@Override
	public void updateUserById(String id, String tname, String email, String phone, String sex, String age) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("age", age);
		map.put("tname", tname);
		map.put("id", id);
		map.put("email", email);
		map.put("phone",phone);
		map.put("sex", sex);
		adminMapper.updateUserById(map);
	}

	@Override
	public void deleteCustomerById(String id) {
		adminMapper.deleteCustomerById(id);
	}

	@Override
	public int[] countYy() {
		Date date = new Date();
		int currentMonthDay = getCurrentMonthDay();
		int[] ints = new int[currentMonthDay];
		for (int i = 0; i < currentMonthDay; i++) {
			Date date1 = new Date(date.getYear(),date.getMonth(),i+1);
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String format = s.format(date1);
			int n = adminMapper.countYy(format);
			ints[i] = n;
		}
		return ints;
	}

	public static int getCurrentMonthDay() {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

//	public static void main(String[] args) {
//		Date date = new Date();
//		Date date1 = new Date(date.getYear(),date.getMonth(),getCurrentMonthDay());
//		System.out.println(date1);
//		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
//		String format = s.format(date1);
//		System.out.println(format);
//	}
}
