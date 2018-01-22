package ro.ubb.istudent.dto;

public class PreferenceDto implements Dto{

    private int applicantId;
    private int sectionId;
    private int preferenceIndex;

    public PreferenceDto(){}

    public PreferenceDto(int applicantId, int sectionId, int preferenceIndex){
        this.applicantId = applicantId;
        this.sectionId = sectionId;
        this.preferenceIndex = preferenceIndex;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public int getPreferenceIndex() {
        return preferenceIndex;
    }

    public void setPreferenceIndex(int preferenceIndex) {
        this.preferenceIndex= preferenceIndex;
    }

    @Override
    public String toString() {
        return "PreferenceDto{" +
                "applicantId=" + applicantId +
                ", sectionId='" + sectionId + '\'' +
                ", preferenceIndex='" + preferenceIndex + '\'' +
                '}';
    }
}
