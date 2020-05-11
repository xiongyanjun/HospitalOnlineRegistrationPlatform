package hospital.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hospital.enity.*;
import hospital.mapping.DeptMapper;
import hospital.mapping.DoctorMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hospital.mapping.CustomerMapper;
import hospital.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerMapper customerDao;


	@Autowired
	private DeptMapper deptMapper;

	@Override
	public void saveUserInfo(String tname, String sex, String phone, int age, String email, String name,
			String password,Integer id) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("tname", tname);
		map.put("sex", sex);
		map.put("phone", phone);
		map.put("age", age);
		map.put("email", email);
		map.put("name", name);
		map.put("password", password);
		map.put("id", id);
		customerDao.saveUserInfo(map);
	}

	@Override
	public User queryUserInfo(String name, String password) {
		return customerDao.queryUserInfo(name,password);
	}

	@Override
	public PageInfo<Doctor> showdirector(int pageSiz, int pageNo) {
		PageHelper.startPage(pageNo,pageSiz);
		List<Doctor> yuyues = customerDao.showdirector();
		PageInfo<Doctor> pageInfo = new PageInfo<Doctor>(yuyues);
		return pageInfo;
	}

//	@Override
//	public List<Doctor> showdirector() {
//		// TODO Auto-generated method stub
//		return customerDao.showdirector();
//	}

	@Override
	public List<Doctor> searchDoctor(String department) {
		// TODO Auto-generated method stub
		return customerDao.searchDoctor(department);
	}

	@Override
	public PageInfo<Doctor> searchAllDoctor(int pageSiz, int pageNo) {
		PageHelper.startPage(pageNo,pageSiz);
		List<Doctor> yuyues = customerDao.searchAllDoctor();
		PageInfo<Doctor> pageInfo = new PageInfo<Doctor>(yuyues);
		return pageInfo;

	}

//	@Override
//	public List<Doctor> searchAllDoctor() {
//		// TODO Auto-generated method stub
//		return customerDao.searchAllDoctor();
//	}

	@Override
	public List<Doctor> yuyueSearchDoctor(String dname) {
		// TODO Auto-generated method stub
		return customerDao.yuyueSearchDoctor(dname);
	}

	@Override
	public Doctor queryDoc(int jnumber) {
		// TODO Auto-generated method stub
		Doctor doctor1 = new Doctor();
		DocInfo doctor = customerDao.queryDoc(jnumber);
		User user = customerDao.searchUserById(doctor.getUid()+"");
		BeanUtils.copyProperties(user,doctor1);
		BeanUtils.copyProperties(doctor,doctor1);
		return doctor1;
	}

	@Override
	public void insertYuyue(int cid,int jnumber, String dname, String cname, String ydate, String ytime, String sex,
			String phone, String isok,int age,int yyh) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("jnumber", jnumber);
		map.put("dname", dname);
		map.put("cname", cname);
		map.put("ydate", ydate);
		map.put("ytime", ytime);
		map.put("sex", sex);
		map.put("phone", phone);
		map.put("isok", isok);
		map.put("age", age);
		map.put("yyh", yyh);
		map.put("cid", cid);

		customerDao.insertYuyue(map);
	}

	@Override
	public PageInfo<Yuyue> searchAllYuyue(String cname, int pageSiz, int pageNo) {
		PageHelper.startPage(pageNo,pageSiz);
		List<Yuyue> yuyues = customerDao.searchAllYuyue(cname);
		PageInfo<Yuyue> pageInfo = new PageInfo<Yuyue>(yuyues);
		return pageInfo;
	}

	@Override
	public PageInfo<Doctor> showdirector1(int pageSize, int pageNo) {
		PageHelper.startPage(pageNo,pageSize);
		List<Doctor> yuyues = customerDao.showdirector1();
		PageInfo<Doctor> pageInfo = new PageInfo<Doctor>(yuyues);
		return pageInfo;
	}

	@Override
	public PageInfo<Yuyue> searchAllYuyue1(String cid, int pageSize, int pageNo) {
		PageHelper.startPage(pageNo,pageSize);
		List<Yuyue> yuyues = customerDao.searchAllYuyue1(cid);
		PageInfo<Yuyue> pageInfo = new PageInfo<Yuyue>(yuyues);
		return pageInfo;
	}

	@Override
	public List<DeptInfo> showDept() {
		ArrayList<DeptInfo> deptInfos = new ArrayList<DeptInfo>();
		List<Department> departments = deptMapper.deptList();
		for (Department d:departments){
			DeptInfo deptInfo = new DeptInfo();
			int num = deptMapper.numOfAdept(d.getId());
			BeanUtils.copyProperties(d,deptInfo);
			deptInfo.setDocNum(num);
			deptInfos.add(deptInfo);
		}
		return deptInfos;
	}

	//	@Override
//	public List<Yuyue> searchAllYuyue(String cname) {
//		// TODO Auto-generated method stub
//		return customerDao.searchAllYuyue(cname);
//	}
    @Override
	public List<Yuyue> searchYuyueOne(String cname, String isok) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("cname", cname);
		map.put("isok", isok);

		return customerDao.searchYuyueOne(map);
	}

    @Override
	public List<Yuyue> searchByDate(String cname, String date) {

		Map<String, Object> map=new HashMap<String, Object>();
		map.put("cname", cname);
		map.put("date", date);

		return customerDao.searchByDate(map);
	}
    @Override
    public void deleteYy(String yyh) {
        customerDao.deleteYy(yyh);
    }

	@Override
	public Doctor searchDoctorByJnumber(String jnumber) {
		Doctor doctor = customerDao.searchDoctorByJnumber(jnumber);
		return doctor;
	}

	@Override
	public PageInfo<User> showCustomer(int pageSize, int pageNo) {
		PageHelper.startPage(pageNo,pageSize);
		List<User> yuyues = customerDao.searchUserByRole("1");
		PageInfo<User> pageInfo = new PageInfo<User>(yuyues);
		return pageInfo;
	}

//	@Override
//	public List<User> showCustomer() {
//		List<User> list = customerDao.searchUserByRole("1");
//		return list;
//	}

	@Override
	public User searchUserById(String id) {
		User user = customerDao.searchUserById(id);
		return user;
	}


}
