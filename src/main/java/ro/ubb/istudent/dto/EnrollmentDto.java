package ro.ubb.istudent.dto;

import java.util.List;

public class EnrollmentDto implements Dto {

    private SectionDto section;
    private List<ApplicantDto> applicants;

    public EnrollmentDto(){}

    public EnrollmentDto(SectionDto section ,List<ApplicantDto> applicants){
        this.section = section;
        this.applicants = applicants;
    }

    public SectionDto getSection() {
        return section;
    }

    public void setSection(SectionDto section) {
        this.section = section;
    }

    public List<ApplicantDto> getApplicants() {
        return applicants;
    }

    public void addApplicant(ApplicantDto applicant) {
        this.applicants.add(applicant);
    }

}
