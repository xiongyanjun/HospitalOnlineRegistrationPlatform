package hospital.mapping;

import hospital.enity.Department;

import java.util.List;

public interface DeptMapper {
    List<Department> deptList();

    Department getByName(String department);

    int numOfAdept(int id);

    Department getDeptById(int id);

    void add(Department department);

    void update(Department department);

    void delDept(String id);
}
