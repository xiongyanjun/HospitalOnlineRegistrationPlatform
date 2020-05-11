package hospital.service;

import hospital.enity.Department;

import java.util.List;

public interface DeptService {
    List<Department> deptList();

    Department getByName(String department);

    Department getDeptById(int id);

    void save(Department department);

    void delDept(String id);
}
