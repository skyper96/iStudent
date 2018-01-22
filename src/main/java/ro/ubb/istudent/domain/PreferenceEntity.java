package ro.ubb.istudent.domain;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Objects;

public class PreferenceEntity implements Serializable{

    @Id
    private int applicantId;
    private int sectionId;
    private int preferenceIndex;

    public PreferenceEntity() {}

    public PreferenceEntity(int applicantId, int sectionId, int preferenceIndex){
        this.applicantId = applicantId;
        this.sectionId = sectionId;
        this.preferenceIndex = preferenceIndex;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int id) {
        this.sectionId = id;
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
        this.preferenceIndex = preferenceIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreferenceEntity that = (PreferenceEntity) o;
        return Objects.equals(sectionId, that.sectionId) &&
                Objects.equals(applicantId, that.applicantId) &&
                Objects.equals(preferenceIndex, that.preferenceIndex);
    }

    @Override
    public int hashCode() {

        return Objects.hash(applicantId + "|" + preferenceIndex);
    }

    @Override
    public String toString() {
        return "PreferenceEntity{" +
                "sectionId=" + sectionId +
                ", applicantId='" + applicantId + '\'' +
                ", preferenceIndex='" + preferenceIndex + '\'' +
                '}';
    }
}
