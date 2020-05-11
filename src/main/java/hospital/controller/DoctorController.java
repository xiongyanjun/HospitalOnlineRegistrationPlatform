package hospital.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import hospital.enity.*;
import hospital.service.CustomerService;
import hospital.service.DeptService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import hospital.service.DoctorService;

@Controller
@RequestMapping("/Doctor")
public class DoctorController {
	@Autowired
	private DeptService deptService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private CustomerService customerService;

	private Gson gson = new Gson();

//	public static void main(String[] args) {
//		DocInfo docInfo = new DocInfo(1,"dwad","dwadwa","dwa",1,2);
//		Doctor doctor = new Doctor();
//		BeanUtils.copyProperties(docInfo,doctor);
//		System.out.println();
//	}
	@RequestMapping("/queryDoctorInfo")
	public void queryDoctorInfo(HttpServletResponse response, HttpSession session, Model model) {
		User userSession = (User) session.getAttribute("User");
		int id = userSession.getId();
		User user = customerService.searchUserById(id + "");
		DocInfo doc = doctorService.queryDoctorInfo1(id);
		Doctor doctor = new Doctor();
		BeanUtils.copyProperties(doc,doctor);
		BeanUtils.copyProperties(user,doctor);
		try {
			response.setContentType("text/json; charset=utf-8");
			response.getWriter().print(gson.toJson(doctor));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/saveDoctorInfo")
	public void saveDoctorInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		User userSession = (User) session.getAttribute("User");
		String tname = request.getParameter("tname");
		String sex = request.getParameter("sex");
		String department = request.getParameter("department");
		Department dept = deptService.getByName(department);
		String position = request.getParameter("position");
		String phone = request.getParameter("phone");
		String ageq = request.getParameter("age");
		int age = 0;
		if(ageq!=null&&!"".equals(ageq)){
			age = Integer.valueOf(ageq);
		}else{
			age = userSession.getAge();
		}
		String email = request.getParameter("email");
		String name = userSession.getName();
		String password = userSession.getPassword();
		if(email==null||"".equals(email)){
			email = userSession.getEmail();
		}
		if(phone==null||"".equals(phone)){
			phone = userSession.getPhone();
		}
		try {
			doctorService.saveDoctorInfo(dept.getId(),tname,userSession.getId(),department, position, phone, age, email, sex ,name, password);
			response.getWriter().print("1" + "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping("/allYy")
	public String allYy(HttpServletRequest request,Model model, HttpSession session) {
		int pageSize = 8;
		int pageNo = 1;
		String no = request.getParameter("pageNo");
		if(no!=null&&!"".equals(no)&&!"undefined".equals(no)){
			pageNo = Integer.parseInt(no);
		}

		User userSession = (User) session.getAttribute("User");
		int id = userSession.getId();
		String isok = "预约中";
		PageInfo<Yuyue> list = doctorService.allYy2(id, isok,pageSize,pageNo);

		model.addAttribute("tabledata", list.getList());
		model.addAttribute("pages",list.getPages());
		model.addAttribute("prePage",list.getPrePage());
		model.addAttribute("nextPage",list.getNextPage());

		return "doctor/doctorYyManageTable";
	}

	@RequestMapping("/yySuccess")
	public void yySuccess(HttpServletRequest request, HttpServletResponse response) {
		String yybh = request.getParameter("yybh");
		String isok = "待确诊";
		try {
			doctorService.yuyueSuccess(yybh, isok);
			response.getWriter().print("1" + "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/yyFail")
	public void yyFail(HttpServletRequest request, HttpServletResponse response) {
		String yybh = request.getParameter("yybh");
		String isok = "预约失败";
		try {
			doctorService.yuyueFail(yybh, isok);
			response.getWriter().print("1" + "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/unsureYuyue")
	public String unsureYuyue(HttpServletRequest request,Model model, HttpSession session) {
		int pageSize = 8;
		int pageNo = 1;
		String no = request.getParameter("pageNo");
		if(no!=null&&!"".equals(no)&&!"undefined".equals(no)){
			pageNo = Integer.parseInt(no);
		}

		User userSession = (User) session.getAttribute("User");
		String name = userSession.getName();

		String isok = "预约中";

		PageInfo<Yuyue> list = doctorService.allYy1(name, isok,pageSize,pageNo);

		model.addAttribute("tabledata", list.getList());
		model.addAttribute("pages",list.getPages());
		model.addAttribute("prePage",list.getPrePage());
		model.addAttribute("nextPage",list.getNextPage());

		return "doctor/doctorYyManageTable";
	}

	@RequestMapping("/sureYuyue")
	public String sureYuyue(HttpServletRequest request,Model model, HttpSession session) {
		int pageSize = 8;
		int pageNo = 1;
		String no = request.getParameter("pageNo");
		if(no!=null&&!"".equals(no)&&!"undefined".equals(no)){
			pageNo = Integer.parseInt(no);
		}

		User userSession = (User) session.getAttribute("User");
		String name = userSession.getName();

		String isok = "待确诊";

		PageInfo<Yuyue> list = doctorService.allYy1(name, isok,pageSize,pageNo);

		model.addAttribute("tabledata", list.getList());
		model.addAttribute("pages",list.getPages());
		model.addAttribute("prePage",list.getPrePage());
		model.addAttribute("nextPage",list.getNextPage());

		return "doctor/doctorYyManageTable";
	}

	@RequestMapping("/searchByDt")
	public String searchByDt(HttpServletRequest request, Model model, HttpSession session) {

		User userSession = (User) session.getAttribute("User");
		String name = userSession.getName();

		String date = request.getParameter("dt");

		List<Yuyue> list = doctorService.searchYyBydt(name, date);

		model.addAttribute("tabledata", list);

		return "doctor/doctorYyManageTable";

	}

	@RequestMapping("/allJz")
	public String allJz(HttpServletRequest request,Model model, HttpSession session) {
		int pageSize = 8;
		int pageNo = 1;
		String no = request.getParameter("pageNo");
		if(no!=null&&!"".equals(no)&&!"undefined".equals(no)){
			pageNo = Integer.parseInt(no);
		}

		User userSession = (User) session.getAttribute("User");
		int id = userSession.getId();
		String isok = "待确诊";
		PageInfo<Yuyue> list = doctorService.allJz1(id, isok,pageSize,pageNo);
		model.addAttribute("tabledata", list.getList());
		model.addAttribute("pages",list.getPages());
		model.addAttribute("prePage",list.getPrePage());
		model.addAttribute("nextPage",list.getNextPage());
		return "doctor/doctorJzManageTable";
	}

	@RequestMapping("/sureJz")
	public void sureJz(HttpServletRequest request, HttpServletResponse response) {
		String yybh = request.getParameter("yybh");
		String isok = "已就诊";
		try {
			doctorService.yuyueSuccess(yybh, isok);
			response.getWriter().print("1" + "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/needFz")
	public void needFz(HttpServletRequest request, HttpServletResponse response) {
		String yybh = request.getParameter("yybh");
		String isok = "已就诊，需复诊";
		try {
			doctorService.yuyueFail(yybh, isok);
			response.getWriter().print("1" + "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/unJz")
	public String unJz(HttpServletRequest request,Model model, HttpSession session) {
		int pageSize = 8;
		int pageNo = 1;
		String no = request.getParameter("pageNo");
		if(no!=null&&!"".equals(no)&&!"undefined".equals(no)){
			pageNo = Integer.parseInt(no);
		}

		User userSession = (User) session.getAttribute("User");
		String name = userSession.getName();

		String isok = "待确诊";

		PageInfo<Yuyue> list = doctorService.allJz(name, isok,pageSize,pageNo);

		model.addAttribute("tabledata", list.getList());
		model.addAttribute("pages",list.getPages());
		model.addAttribute("prePage",list.getPrePage());
		model.addAttribute("nextPage",list.getNextPage());

		return "doctor/doctorJzManageTable";
	}

	@RequestMapping("/jz")
	public String jz(Model model, HttpSession session) {

		User userSession = (User) session.getAttribute("User");
		String name = userSession.getName();

		String isok = "已就诊";
		String isok2 = "已就诊，需复诊";

		List<Yuyue> list = doctorService.allJiuJz(name, isok, isok2);

		model.addAttribute("tabledata", list);

		return "doctor/doctorJzManageTable";
	}

	@RequestMapping("/searchJzByDt")
	public String searchJzByDt(HttpServletRequest request, Model model, HttpSession session) {
		User userSession = (User) session.getAttribute("User");
		String name = userSession.getName();
		String date = request.getParameter("dt");
		List<Yuyue> list = doctorService.searchYyBydt(name, date);
		model.addAttribute("tabledata", list);
		return "doctor/doctorJzManageTable";
	}

	@RequestMapping("/allFz")
	public String allFz(HttpServletRequest request,Model model, HttpSession session) {
		int pageSize = 8;
		int pageNo = 1;
		String no = request.getParameter("pageNo");
		if(no!=null&&!"".equals(no)&&!"undefined".equals(no)){
			pageNo = Integer.parseInt(no);
		}
		User userSession = (User) session.getAttribute("User");
		int id = userSession.getId();
		String isok = "已就诊，需复诊";
		PageInfo<Yuyue> list = doctorService.allJz1(id, isok,pageSize,pageNo);
		model.addAttribute("tabledata", list.getList());
		model.addAttribute("pages",list.getPages());
		model.addAttribute("prePage",list.getPrePage());
		model.addAttribute("nextPage",list.getNextPage());
		return "doctor/doctorFzManageTable";
	}

	@RequestMapping("/sureFz")
	public void sureFz(HttpServletRequest request, HttpServletResponse response) {
		String yybh = request.getParameter("yybh");
		String isok = "已复诊，确诊完毕";
		try {
			doctorService.yuyueSuccess(yybh, isok);
			response.getWriter().print("1" + "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/unFz")
	public String unFz(HttpServletRequest request,Model model, HttpSession session) {
		int pageSize = 8;
		int pageNo = 1;
		String no = request.getParameter("pageNo");
		if(no!=null&&!"".equals(no)&&!"undefined".equals(no)){
			pageNo = Integer.parseInt(no);
		}

		User userSession = (User) session.getAttribute("User");
		String name = userSession.getName();
		String isok = "已就诊，需复诊";
		PageInfo<Yuyue> list = doctorService.allJz(name, isok,pageSize,pageNo);

		model.addAttribute("tabledata", list.getList());
		model.addAttribute("pages",list.getPages());
		model.addAttribute("prePage",list.getPrePage());
		model.addAttribute("nextPage",list.getNextPage());
		return "doctor/doctorFzManageTable";
	}

	@RequestMapping("/fz")
	public String fz(HttpServletRequest request,Model model, HttpSession session) {
		int pageSize = 8;
		int pageNo = 1;
		String no = request.getParameter("pageNo");
		if(no!=null&&!"".equals(no)&&!"undefined".equals(no)){
			pageNo = Integer.parseInt(no);
		}

		User userSession = (User) session.getAttribute("User");
		String name = userSession.getName();
		String isok = "已复诊，确诊完毕";
		PageInfo<Yuyue> list = doctorService.allJz(name, isok,pageSize,pageNo);
		model.addAttribute("tabledata", list.getList());
		model.addAttribute("pages",list.getPages());
		model.addAttribute("prePage",list.getPrePage());
		model.addAttribute("nextPage",list.getNextPage());
		return "doctor/doctorFzManageTable";
	}

	@RequestMapping("/searchFzByDt")
	public String searchFzByDt(HttpServletRequest request, Model model, HttpSession session) {
		User userSession = (User) session.getAttribute("User");
		String name = userSession.getName();
		String date = request.getParameter("dt");
		List<Yuyue> list = doctorService.searchYyBydt(name, date);
		model.addAttribute("tabledata", list);
		return "doctor/doctorFzManageTable";

	}

	@RequestMapping("/departmentMembers")
	public String departmentMembers(Model model, HttpSession session) {
		User userSession = (User) session.getAttribute("User");
		int id = userSession.getId();
		DocInfo doc = doctorService.queryDoctorInfo1(id);
		int deptNo = doc.getDeptNo();
		List<Doctor> list = doctorService.queryByDeptNo(deptNo);
		model.addAttribute("tabledata", list);
		return "doctor/departmentTable";
	}

}
