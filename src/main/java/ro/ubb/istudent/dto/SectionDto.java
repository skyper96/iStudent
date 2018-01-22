package ro.ubb.istudent.dto;

public class SectionDto implements Dto {

    private int sectionId;
    private String name;
    private int numberOfYears;
    private int maxStudents;

    public SectionDto(){}

    public SectionDto(int sectionId, String name, int numberOfYears, int maxStudents){
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
    public String toString() {
        return "SectionDto{" +
                "sectionId=" + sectionId +
                ", name='" + name + '\'' +
                ", numberOfYears='" + numberOfYears + '\'' +
                ", maxStudents='" + maxStudents + '\'' +
                '}';
    }
}
