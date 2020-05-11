package hospital.enity;

public class DocInfo {
    private int jnumber;
    private String department;
    private String summary;
    private String position;
    private int uid;
    private int deptNo;

    public DocInfo() {
    }

    public DocInfo(int jnumber, String department, String summary, String position, int uid, int deptNo) {
        this.jnumber = jnumber;
        this.department = department;
        this.summary = summary;
        this.position = position;
        this.uid = uid;
        this.deptNo = deptNo;
    }

    public int getJnumber() {
        return jnumber;
    }

    public void setJnumber(int jnumber) {
        this.jnumber = jnumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }
}
