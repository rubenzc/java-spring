package domain;

public class Student {

    private int idStudent;
    private String name;
    private String surname;
    private String phone;
    private String mail;

    public Student(){}

    public Student(int idStudent){
        this.idStudent = idStudent;
    }

    public Student(String name, String surname,
                   String phone, String mail){
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.mail = mail;
    }

    public Student(int idStudent, String name,
                   String surname, String phone, String mail){
        this.idStudent = idStudent;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.mail = mail;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + idStudent +
                ", name=`" + name + '\'' +
                ", surname=`" + surname + '\'' +
                ", phone=`" + phone + '\'' +
                ", mail=`" + mail + '\'' +
                '}';
    }
}
