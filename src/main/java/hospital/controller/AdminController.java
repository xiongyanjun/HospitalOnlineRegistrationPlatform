package hospital.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import hospital.enity.*;
import hospital.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Admin")
public class AdminController {
	@Autowired
	private DeptService deptService;
	@Autowired
	private LoginService loginService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private CustomerService customerService;

	private Gson gson = new Gson();

	@RequestMapping("/showPage")
	public String showPage(HttpServletRequest request, Model model) {
		int pageSize = 8;
		int pageNo = 1;
		String no = request.getParameter("pageNo");
		if(no!=null&&!"".equals(no)&&!"undefined".equals(no)){
			pageNo = Integer.parseInt(no);
		}
		PageInfo<Doctor> list = customerService.showdirector1(pageSize,pageNo);
		model.addAttribute("tabledata", list.getList());
		model.addAttribute("pages",list.getPages());
		model.addAttribute("prePage",list.getPrePage());
		model.addAttribute("nextPage",list.getNextPage());
//		List<Doctor> list = customerService.showdirector();
//		model.addAttribute("tabledata", list);
		return "admin/doctorTable";
	}

	@RequestMapping("/customerTable")
	public String customerTable(HttpServletRequest request, Model model) {

		int pageSize = 8;
		int pageNo = 1;
		String no = request.getParameter("pageNo");
		if(no!=null&&!"".equals(no)&&!"undefined".equals(no)){
			pageNo = Integer.parseInt(no);
		}
		PageInfo<User> list = customerService.showCustomer(pageSize,pageNo);
		model.addAttribute("tabledata", list.getList());
		model.addAttribute("pages",list.getPages());
		model.addAttribute("prePage",list.getPrePage());
		model.addAttribute("nextPage",list.getNextPage());




//		List<User> list = customerService.showCustomer();
//		model.addAttribute("tabledata", list);
		return "admin/customerTable";
	}

	@RequestMapping("/searchDoctor")
	public String searchDoctor(HttpServletRequest request, Model model) {

		String department = request.getParameter("department");

		List<Doctor> list = customerService.searchDoctor(department);

		model.addAttribute("tabledata", list);

		return "admin/doctorTable";

	}

	@RequestMapping("/dept")
	public String dept(Model model) {
		List<Department> list = deptService.deptList();
		model.addAttribute("tabledata", list);
		return "admin/dept";
	}

	@RequestMapping("/getDeptById")
	@ResponseBody
	public Department getDeptById(int id) {
		Department list = deptService.getDeptById(id);
		return list;
	}

	@RequestMapping("/updateDept")
	@ResponseBody
	public JSONObject updateDept(HttpServletRequest request) {
		String id = request.getParameter("id");
		String deptName = request.getParameter("deptName");
		String info = request.getParameter("info");
		String date = request.getParameter("date");
		String floor = request.getParameter("floor");
		Department department = new Department(Integer.parseInt(id),deptName,info,Integer.parseInt(date),floor);
		deptService.save(department);
		return new JSONObject();
	}

	@RequestMapping("/delDept")
	@ResponseBody
	public JSONObject delDept(HttpServletRequest request) {
		String id = request.getParameter("id");
		deptService.delDept(id);
		return new JSONObject();
	}

	@RequestMapping("/queryHospitalInfo")
	public void queryHospitalInfo(HttpServletResponse response, Model model) {
		Hospital hospital = adminService.queryHospitalInfo();
		try {
			/* 设置格式为text/json；字符集为UTF-8 */
			response.setContentType("text/json; charset=utf-8");
			response.getWriter().print(gson.toJson(hospital));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@RequestMapping("/SaveDoctorInfo")
	public void SaveDoctorInfo(HttpServletRequest request, HttpServletResponse response) {
		String jnumber = request.getParameter("jnumber");
		String department = request.getParameter("department");
		Department dept = deptService.getByName(department);
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String summary = request.getParameter("summary");
		String position = request.getParameter("position");
		try {
		    if ("".equals(jnumber)||jnumber==null){

            }else
            {
                adminService.updateDoctorByJnumber(dept.getId(),jnumber,department,name,email,phone,summary,sex,age,position);
            }
			response.getWriter().print("1" + "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping("/SaveCustomerInfo")
	public void SaveCustomerInfo(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
//		String roleId = request.getParameter("roleId");
//		String name = request.getParameter("name");
		String tname = request.getParameter("tname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		try {
			adminService.updateUserById(id,tname,email,phone,sex,age);
			response.getWriter().print("1" + "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping("/addDoc")
	public void addDoc(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String password = request.getParameter("pw");
		String tname = request.getParameter("tname");
		int roleId = 2;
		String definition = "医生";
		int i = loginService.registerUser1(name, password, tname,roleId, definition);
		doctorService.insertDocter(i);
		try {
			response.getWriter().print("1" + "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/saveHospitalInfo")
	public void saveHospitalInfo(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String youbian = request.getParameter("youbian");
		String header = request.getParameter("header");
		String buildTime = request.getParameter("buildTime");
		try {
			adminService.updateHospitalInfo(name, address, email, telephone, youbian, header, buildTime);
			response.getWriter().print("1" + "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/huanzheGuanli")
	public String huanzheGuanli(HttpServletRequest request,Model model) {

		int pageSize = 8;
		int pageNo = 1;
		String no = request.getParameter("pageNo");
		if(no!=null&&!"".equals(no)&&!"undefined".equals(no)){
			pageNo = Integer.parseInt(no);
		}
		PageInfo<Yuyue> list = adminService.huanzheGuanli(pageSize,pageNo);
		model.addAttribute("tabledata", list.getList());
		model.addAttribute("pages",list.getPages());
		model.addAttribute("prePage",list.getPrePage());
		model.addAttribute("nextPage",list.getNextPage());
		return "admin/huanzheManageTable";
	}

	@RequestMapping("/yyCount")
	@ResponseBody
	public JSONObject yyCount() {
		JSONObject jsonObject = new JSONObject();

		int[] data = adminService.countYy();
		jsonObject.put("data",data);
		return jsonObject;
	}

	@RequestMapping("/isokSelect")
	public String isokSelect(HttpServletRequest request, Model model) {

		String isok = request.getParameter("isok");
		String department = request.getParameter("department");
		String date = request.getParameter("date");

		if (department.equals("") && date.equals("")) {
			List<Yuyue> list = adminService.queryByIsok(isok);
			model.addAttribute("tabledata", list);
		}

		if (date.equals("") && !department.equals("")) {
			List<Yuyue> list = adminService.queryBySelect(isok, department);
			model.addAttribute("tabledata", list);
		}

		if (department.equals("") && !date.equals("")) {
			List<Yuyue> list = adminService.queryByIsD(isok, date);
			model.addAttribute("tabledata", list);
		}
		if (!department.equals("") && !date.equals("")) {
			List<Yuyue> list = adminService.queryByAll(isok, date, department);
			model.addAttribute("tabledata", list);
		}

		return "admin/huanzheManageTable";

	}

	@RequestMapping("/departmentSelect")
	public String departmentSelect(HttpServletRequest request, Model model) {

		String isok = request.getParameter("isok");
		String department = request.getParameter("department");
		String date = request.getParameter("date");

		if (isok.equals("") && date.equals("")) {
			List<Yuyue> list = adminService.queryByDepartment(department);
			model.addAttribute("tabledata", list);
		}

		if (isok.equals("") && !date.equals("")) {
			List<Yuyue> list = adminService.queryByDeD(department, date);
			model.addAttribute("tabledata", list);
		}

		if (date.equals("") && !isok.equals("")) {
			List<Yuyue> list = adminService.queryBySelect(isok, department);
			model.addAttribute("tabledata", list);
		}

		if (!date.equals("") && !isok.equals("")) {
			List<Yuyue> list = adminService.queryByAll(isok, date, department);
			model.addAttribute("tabledata", list);
		}

		return "admin/huanzheManageTable";

	}

	@RequestMapping("/searchByDt")
	public String searchByDt(HttpServletRequest request, Model model) {

		String isok = request.getParameter("isok");
		String department = request.getParameter("department");
		String date = request.getParameter("date");

		if (isok.equals("") && department.equals("")) {
			List<Yuyue> list = adminService.searchByDt(date);
			model.addAttribute("tabledata", list);
		}

		if (isok.equals("") && !department.equals("")) {
			List<Yuyue> list = adminService.queryByDeD(department, date);
			model.addAttribute("tabledata", list);
		}

		if (department.equals("") && !isok.equals("")) {
			List<Yuyue> list = adminService.queryByIsD(isok, date);
			model.addAttribute("tabledata", list);
		}

		if (!department.equals("") && !isok.equals("")) {
			List<Yuyue> list = adminService.queryByAll(isok, date, department);
			model.addAttribute("tabledata", list);
		}

		return "admin/huanzheManageTable";
	}

	@RequestMapping("/newsInfo")
	public String newsInfo(Model model,HttpServletRequest request) {

		int pageSize = 8;
		int pageNo = 1;
		String no = request.getParameter("pageNo");
		if(no!=null&&!"".equals(no)&&!"undefined".equals(no)){
			pageNo = Integer.parseInt(no);
		}
		PageInfo<News> list = adminService.newsInfo(pageSize,pageNo);
		model.addAttribute("tabledata", list.getList());
		model.addAttribute("pages",list.getPages());
		model.addAttribute("prePage",list.getPrePage());
		model.addAttribute("nextPage",list.getNextPage());

//		List<News> list = adminService.newsInfo();
//		model.addAttribute("tabledata", list);
		return "admin/newsManageTable";
	}

	@RequestMapping("/deleteNews")
	public void deleteNews(HttpServletRequest request, Model model, HttpServletResponse response) {
		int newsId = Integer.parseInt(request.getParameter("newsId"));
		try {
			adminService.deleteNews(newsId);
			response.getWriter().print("1" + "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @RequestMapping("/deleteDoctor")
    public void deleteDoctor(HttpServletRequest request, HttpServletResponse response) {
        String uid = request.getParameter("uid").trim();
        try {
            adminService.deleteDoctor(uid);
			adminService.deleteCustomerById(uid);
            response.getWriter().print("1" + "");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


	@RequestMapping("/deleteUserById")
	public void deleteCustomer(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id").trim();
		try {
			adminService.deleteCustomerById(id);
			response.getWriter().print("1" + "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/searchDoctorByJnumber")
	@ResponseBody
	public Doctor searchDoctorByJnumber(String jnumber) {
//		DocInfo doctor = doctorService.queryDoctorInfo1(Integer.parseInt(jnumber));
		Doctor doctor1 = customerService.queryDoc(Integer.parseInt(jnumber));
		return doctor1;
	}


	@RequestMapping("/searchUserById")
	@ResponseBody
	public User searchUserById(String id) {
		User user = customerService.searchUserById(id);
		return user;
	}
}
