package hospital.mapping;

import java.util.List;
import java.util.Map;

import hospital.enity.DocInfo;
import org.apache.ibatis.annotations.Param;

import hospital.enity.Doctor;
import hospital.enity.Yuyue;

public interface DoctorMapper {

	void insertDoctor(int uid);

	DocInfo queryDoctorInfo(@Param(value="id")int id);

	void saveDoctorInfo(Map<String, Object> map);

	List<Yuyue> allYy(@Param(value="name")String name, @Param(value="isok")String isok);

	void yuyueSuccess(@Param(value="yybh")String yybh,@Param(value="isok")String isok);

	void yuyueFail(@Param(value="yybh")String yybh,@Param(value="isok")String isok);

	List<Yuyue> searchYyBydt(@Param(value="name")String name,@Param(value="date") String date);

	List<Yuyue> allJz(@Param(value="name")String name, @Param(value="isok")String isok);

	List<Yuyue> allJiuJz(@Param(value="name")String name,@Param(value="isok") String isok, @Param(value="isok2")String isok2);

	List<Doctor> queryByDepartment(@Param(value="department")String department);

	void saveDocInfo(Map<String, Object> map);

	List<DocInfo> findAllByDeptNo(int deptNo);

	List<Yuyue> allYy2(@Param(value="id")String id, @Param(value="isok")String isok);

	List<Yuyue> allJz1(@Param(value="id")String id, @Param(value="isok")String isok);

	List<Yuyue> allYy3(@Param(value="jnumber")String jnumber, @Param(value="s")String s);
}
