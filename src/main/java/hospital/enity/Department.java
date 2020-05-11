package hospital.enity;

public class Department {
    private int id;
    private String deptName;
    private String info;
    private int date;
    private String floor;

    public Department() {
    }

    public Department(int id, String deptName, String info, int date, String floor) {
        this.id = id;
        this.deptName = deptName;
        this.info = info;
        this.date = date;
        this.floor = floor;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
