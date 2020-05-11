package hospital.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import hospital.enity.DeptInfo;
import hospital.enity.Doctor;
import hospital.enity.User;
import hospital.enity.Yuyue;
import hospital.service.CustomerService;
import hospital.service.DoctorService;
import hospital.service.LoginService;
import hospital.utilities.YyHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/Customer")
public class CustomerController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DoctorService doctorService;

    private Gson gson = new Gson();

    @RequestMapping("/queryUserInfo")
    public void queryUserInfo(HttpServletResponse response, HttpSession session, Model model) {

        User userSession = (User) session.getAttribute("User");
        String name = userSession.getName();
        String password = userSession.getPassword();

        User user = customerService.queryUserInfo(name, password);

        try {
            /* 设置格式为text/json；字符集为UTF-8 */
            response.setContentType("text/json; charset=utf-8");
            response.getWriter().print(gson.toJson(user));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    //保存信息后,session需要更新
    @RequestMapping("saveUserInfo")
    public void saveUserInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String tname = request.getParameter("tname");
        String sex = request.getParameter("sex");
        String phone = request.getParameter("phone");
        int age = Integer.valueOf(request.getParameter("age"));
        String email = request.getParameter("email");
        User userSession = (User) session.getAttribute("User");
        String name = userSession.getName();
        Integer id = userSession.getId();
        String password = userSession.getPassword();
        try {
            customerService.saveUserInfo(tname, sex, phone, age, email, name, password, id);
            response.getWriter().print("1" + "");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @RequestMapping("/showPage")
    public String showPage(HttpServletRequest request, Model model) {
        int pageSize = 8;
        int pageNo = 1;
        String no = request.getParameter("pageNo");
        if (no != null && !"".equals(no) && !"undefined".equals(no)) {
            pageNo = Integer.parseInt(no);
        }
        PageInfo<Doctor> list = customerService.showdirector1(pageSize, pageNo);
        model.addAttribute("tabledata", list.getList());
        model.addAttribute("pages", list.getPages());
        model.addAttribute("prePage", list.getPrePage());
        model.addAttribute("nextPage", list.getNextPage());
        return "customer/doctorTable";
    }


    @RequestMapping("/department")
    public String department(Model model) {
        int a = 0;
        a += 1;
        List<DeptInfo> list = customerService.showDept();
        model.addAttribute("tabledata", list);
        return "customer/department";
    }

    @RequestMapping("/searchDoctor")
    public String searchDoctor(HttpServletRequest request, Model model) {

        String department = request.getParameter("department");

        List<Doctor> list = customerService.searchDoctor(department);

        model.addAttribute("tabledata", list);

        return "customer/doctorTable";

    }

    @RequestMapping("/yuyueguahao")
    public String yuyueguahao(HttpServletRequest request, Model model) {

        int pageSize = 8;
        int pageNo = 1;
        String no = request.getParameter("pageNo");
        if (no != null && !"".equals(no) && !"undefined".equals(no)) {
            pageNo = Integer.parseInt(no);
        }

        PageInfo<Doctor> list = customerService.showdirector1(pageSize, pageNo);

        model.addAttribute("tabledata", list.getList());
        model.addAttribute("pages", list.getPages());
        model.addAttribute("prePage", list.getPrePage());
        model.addAttribute("nextPage", list.getNextPage());


        return "customer/yuyueguahaoTable";

    }

    @RequestMapping("/yuyueSearchDoctor")
    public String yuyueSearchDoctor(HttpServletRequest request, Model model) {

        String dname = request.getParameter("dname");

        List<Doctor> list = customerService.yuyueSearchDoctor(dname);

        model.addAttribute("tabledata", list);

        return "customer/yuyueguahaoTable";

    }

    @RequestMapping("/yuyue")
    public void yuyue(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ParseException, IOException {
        User userSession = (User) session.getAttribute("User");
        response.setCharacterEncoding("UTF-8");
        List<User> list1 = loginService.logincheck(userSession.getName(), userSession.getPassword());
        if (!list1.isEmpty()) {
            User user = list1.get(0);
            session.setAttribute("User", user);
        }
        userSession = (User) session.getAttribute("User");
        int jnumber = Integer.valueOf(request.getParameter("jnumber"));
        String ydate = request.getParameter("ydate");//2020-01-01
        String ytime = request.getParameter("ytime");//12:10
        Doctor list = customerService.queryDoc(jnumber);
        String dname = list.getTname();
        int uid = list.getUid();
        int yyh = (int) ((Math.random() * 9 + 1) * 100000);
        String isok = "预约中";
        int cid = userSession.getId();
        String cname = userSession.getTname();
        String sex = userSession.getSex();
        String phone = userSession.getPhone();
        System.out.println(cname);
        int age = userSession.getAge();
        if (" ".equals(cname) || " ".equals(phone)) {
            response.getWriter().print("0" + "");
            return;
        }
        try {
            if (ydate.equals("") || ytime.equals("")) {
                response.getWriter().print("2" + "");
                return;
            } else {
                List<Yuyue> yYlist = doctorService.allYy3(uid, "预约中");
                Boolean suitable = YyHelper.isSuitable(yYlist, ydate, ytime);
                if (!suitable) {
                    String s = YyHelper.YyAdvise(yYlist, ydate);
                    response.getWriter().print(s);
                    return;
                }
                customerService.insertYuyue(cid, uid, dname, cname, ydate, ytime, sex, phone, isok, age, yyh);
                response.getWriter().print("1" + "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("yuyueguanli")
    public String yuyueguanli(HttpServletRequest request, Model model, HttpSession session) {
        int pageSize = 8;
        int pageNo = 1;
        String no = request.getParameter("pageNo");
        if (no != null && !"".equals(no) && !"undefined".equals(no)) {
            pageNo = Integer.parseInt(no);
        }
        User userSession = (User) session.getAttribute("User");
        String cid = userSession.getId() + "";
        PageInfo<Yuyue> list = customerService.searchAllYuyue1(cid, pageSize, pageNo);
        model.addAttribute("tabledata", list.getList());
        model.addAttribute("pages", list.getPages());
        model.addAttribute("prePage", list.getPrePage());
        model.addAttribute("nextPage", list.getNextPage());
        return "customer/yyManageTable";
    }

    @RequestMapping("/yuyueIng")
    public String yuyueIng(Model model, HttpSession session) {

        User userSession = (User) session.getAttribute("User");
        String cname = userSession.getTname();

        String isok = "预约中";

        List<Yuyue> list = customerService.searchYuyueOne(cname, isok);

        model.addAttribute("tabledata", list);

        return "customer/yyManageTable";
    }

    @RequestMapping("/yuyueSuc")
    public String yuyueSuc(Model model, HttpSession session) {

        User userSession = (User) session.getAttribute("User");
        String cname = userSession.getTname();

        String isok = "预约成功";

        List<Yuyue> list = customerService.searchYuyueOne(cname, isok);

        model.addAttribute("tabledata", list);

        return "customer/yyManageTable";
    }

    @RequestMapping("/yuyueFai")
    public String yuyueFai(Model model, HttpSession session) {

        User userSession = (User) session.getAttribute("User");
        String cname = userSession.getTname();

        String isok = "预约失败";

        List<Yuyue> list = customerService.searchYuyueOne(cname, isok);

        model.addAttribute("tabledata", list);

        return "customer/yyManageTable";
    }

    @RequestMapping("/searchByDt")
    public String searchByDt(HttpServletRequest request, Model model, HttpSession session) {

        String date = request.getParameter("date");

        User userSession = (User) session.getAttribute("User");
        String cname = userSession.getTname();

        List<Yuyue> list = customerService.searchByDate(cname, date);

        model.addAttribute("tabledata", list);

        return "customer/yyManageTable";

    }

    @RequestMapping("/deleteYy")
    public String deleteYy(String yyh) {
        customerService.deleteYy(yyh);
        return "customer/yyManageTable";
    }

}
