package hospital.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hospital.enity.DocInfo;
import hospital.enity.User;
import hospital.mapping.CustomerMapper;
import hospital.mapping.DeptMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hospital.mapping.DoctorMapper;
import hospital.enity.Doctor;
import hospital.enity.Yuyue;
import hospital.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService{

	@Autowired
	private DoctorMapper doctorDao;

	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public Doctor queryDoctorInfo(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveDoctorInfo(int deptNo,String tname,int id,String department, String position, String phone, int age, String email, String sex,String name,
			String password) {
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> map2=new HashMap<String, Object>();
		map2.put("uid", id);
		map2.put("position", position);
		map2.put("department", department);
		map2.put("deptNo", deptNo);
		map.put("id", id);
		map.put("phone", phone);
		map.put("age", age);
		map.put("email", email);
		map.put("name", name);
		map.put("password", password);
		map.put("sex", sex);
		map.put("tname", tname);
		doctorDao.saveDoctorInfo(map);
		doctorDao.saveDocInfo(map2);
	}

	@Override
	public List<Yuyue> allYy(String name,String isok) {
		// TODO Auto-generated method stub
		return doctorDao.allYy(name,isok);
	}

	@Override
	public void insertDocter(int uid) {
		doctorDao.insertDoctor(uid);
	}

	@Override
	public void yuyueSuccess(String yybh,String isok) {
		doctorDao.yuyueSuccess(yybh,isok);
	}

	@Override
	public void yuyueFail(String yybh, String isok) {
		doctorDao.yuyueFail(yybh,isok);
	}

	@Override
	public List<Yuyue> searchYyBydt(String name, String date) {
		// TODO Auto-generated method stub
		return doctorDao.searchYyBydt(name,date);
	}

	@Override
	public PageInfo<Yuyue> allJz(String name, String isok, int pageSize, int pageNo) {
		PageHelper.startPage(pageNo,pageSize);
		List<Yuyue> yuyues = doctorDao.allJz(name,isok);
		PageInfo<Yuyue> pageInfo = new PageInfo<Yuyue>(yuyues);
		return pageInfo;

	}

//	@Override
//	public List<Yuyue> allJz(String name, String isok) {
//		// TODO Auto-generated method stub
//		return doctorDao.allJz(name,isok);
//	}

	@Override
	public List<Yuyue> allJiuJz(String name, String isok,String isok2) {
		// TODO Auto-generated method stub
		return doctorDao.allJiuJz(name,isok,isok2);
	}

	@Override
	public List<Doctor> queryByDepartment(String department) {
		return doctorDao.queryByDepartment(department);
	}

	@Override
	public List<Yuyue> allYyByJnumber(int jnumber, String isok) {
		return null;
	}

	@Override
	public PageInfo<Yuyue> allYy1(String name, String isok, int pageSize, int pageNo) {

		PageHelper.startPage(pageNo,pageSize);
		List<Yuyue> yuyues = doctorDao.allYy(name,isok);
		PageInfo<Yuyue> pageInfo = new PageInfo<Yuyue>(yuyues);
		return pageInfo;
	}

	@Override
	public DocInfo queryDoctorInfo1(int id) {
		return doctorDao.queryDoctorInfo(id);
	}

	@Override
	public List<Doctor> queryByDeptNo(int deptNo) {
		ArrayList<Doctor> doctors = new ArrayList<Doctor>();
		List<DocInfo> docInfos = doctorDao.findAllByDeptNo(deptNo);
		for (DocInfo d:docInfos) {
			Doctor doctor = new Doctor();
			User user = customerMapper.searchUserById(d.getUid() + "");
			BeanUtils.copyProperties(user,doctor);
			BeanUtils.copyProperties(d,doctor);
			doctors.add(doctor);
		}
		return doctors;
	}

	@Override
	public PageInfo<Yuyue> allYy2(int id, String isok, int pageSize, int pageNo) {
		PageHelper.startPage(pageNo,pageSize);
		List<Yuyue> yuyues = doctorDao.allYy2(id+"",isok);
		PageInfo<Yuyue> pageInfo = new PageInfo<Yuyue>(yuyues);
		return pageInfo;
	}

	@Override
	public PageInfo<Yuyue> allJz1(int id, String isok, int pageSize, int pageNo) {
		PageHelper.startPage(pageNo,pageSize);
		List<Yuyue> yuyues = doctorDao.allJz1(id+"",isok);
		PageInfo<Yuyue> pageInfo = new PageInfo<Yuyue>(yuyues);
		return pageInfo;
	}

	@Override
	public PageInfo<Yuyue> allJz3(String id, String isok, int pageSize, int pageNo) {
		return null;
	}

	@Override
	public List<Yuyue> allYy3(int jnumber, String s) {
		return doctorDao.allYy3(jnumber+"",s);
	}

}
