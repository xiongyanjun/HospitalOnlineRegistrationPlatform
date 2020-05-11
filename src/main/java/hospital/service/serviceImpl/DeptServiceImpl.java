package hospital.service.serviceImpl;

import hospital.enity.Department;
import hospital.mapping.DeptMapper;
import hospital.mapping.LoginMapper;
import hospital.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Department> deptList() {
        return deptMapper.deptList();
    }

    @Override
    public Department getByName(String department) {
        return deptMapper.getByName(department);
    }

    @Override
    public Department getDeptById(int id) {
        return deptMapper.getDeptById(id);
    }

    @Override
    public void save(Department department) {
        if (department.getId()==0){
            deptMapper.add(department);
        }else{
            deptMapper.update(department);
        }
    }

    @Override
    public void delDept(String id) {
        deptMapper.delDept(id);
    }
}
