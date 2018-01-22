package ro.ubb.istudent.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Document(collection = "section")
public class SectionEntity implements Serializable {

    @Id
    private int sectionId;
    private String name;
    private int numberOfYears;
    private int maxStudents;

    public SectionEntity() {}

    public SectionEntity(int sectionId, String name, int numberOfYears, int maxStudents){
        this.sectionId = sectionId;
        this.name = name;
        this.numberOfYears = numberOfYears;
        this.maxStudents = maxStudents;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int id) {
        this.sectionId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectionEntity that = (SectionEntity) o;
        return Objects.equals(sectionId, that.sectionId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(numberOfYears, that.numberOfYears) &&
                Objects.equals(maxStudents, that.maxStudents);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sectionId);
    }

    @Override
    public String toString() {
        return "SectionEntity{" +
                "sectionId=" + sectionId +
                ", name='" + name + '\'' +
                ", numberOfYears='" + numberOfYears + '\'' +
                ", maxStudents='" + maxStudents + '\'' +
                '}';
    }
}
