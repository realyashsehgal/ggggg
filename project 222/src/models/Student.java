package src.models;

public class Student {
    String erpId;
    String name;
    String course;

    public Student(String erpId, String name, String course)
    {
        this.erpId = erpId;
        this.name = name;
        this.course = course;
    }

    public String getErpId(){ return erpId; }
    public String getName(){ return name; }
    public String getCourse() { return course; }

    @Override
    public String toString()
    {
        return "ERP_ID: " + erpId + ", Name: " + name + ", Course: " + course + "\n";
    }
}
