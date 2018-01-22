package ro.ubb.istudent.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Document(collection = "applicant")
public class ApplicantEntity implements Serializable {

    @Id
    private int applicantId;
    private String lastName;
    private String firstName;
    private int age;
    private double grade;

    public ApplicantEntity() {}

    public ApplicantEntity(int applicantId, String lastName, String firstName, int age, double grade){
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicantEntity that = (ApplicantEntity) o;
        return Objects.equals(applicantId, that.applicantId) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(age, that.age) &&
                Objects.equals(grade, that.grade);
    }

    @Override
    public int hashCode() {

        return Objects.hash(applicantId);
    }

    @Override
    public String toString() {
        return "ApplicantEntity{" +
                "applicantId=" + applicantId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age='" + age + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
