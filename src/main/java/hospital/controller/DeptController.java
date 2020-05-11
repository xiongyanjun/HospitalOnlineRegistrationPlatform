package hospital.controller;

import hospital.enity.Department;
import hospital.enity.Doctor;
import hospital.mapping.LoginMapper;
import hospital.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @RequestMapping("/list")
    @ResponseBody
    public List<Department> searchDoctor() {
        return deptService.deptList();
    }
}
