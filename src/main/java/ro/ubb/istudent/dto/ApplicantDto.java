package ro.ubb.istudent.dto;

import java.util.Comparator;

public class ApplicantDto implements Dto {

    private int applicantId;
    private String lastName;
    private String firstName;
    private int age;
    private double grade;

    public ApplicantDto(){}

    public ApplicantDto(int applicantId, String lastName, String firstName, int age, double grade){
        this.applicantId = applicantId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.grade = grade;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int id) {
        this.applicantId = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "ApplicantDto{" +
                "applicantId=" + applicantId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age='" + age + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
