package ro.ubb.istudent.service;

import org.springframework.stereotype.Service;
import ro.ubb.istudent.domain.ApplicantEntity;
import ro.ubb.istudent.domain.PreferenceEntity;
import ro.ubb.istudent.domain.SectionEntity;
import ro.ubb.istudent.dto.ApplicantDto;
import ro.ubb.istudent.dto.EnrollmentDto;
import ro.ubb.istudent.dto.PreferenceDto;
import ro.ubb.istudent.dto.SectionDto;
import ro.ubb.istudent.exception.SqlConnectException;
import ro.ubb.istudent.repository.ApplicantRepository;
import ro.ubb.istudent.repository.PreferenceRepository;
import ro.ubb.istudent.repository.SectionRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollService {

    private final ApplicantRepository applicantRepository;
    private final SectionRepository sectionRepository;
    private final PreferenceRepository preferenceRepository;

    public EnrollService(ApplicantRepository applicantRepository, SectionRepository sectionRepository, PreferenceRepository preferenceRepository) {
        this.applicantRepository = applicantRepository;
        this.sectionRepository = sectionRepository;
        this.preferenceRepository = preferenceRepository;
    }

    public List<EnrollmentDto> enroll() {
        try {
            List<ApplicantDto> applicantDtos = new ArrayList<>();
            List<ApplicantDto> unsuccessfulApplicantDtos = new ArrayList<>();
            List<SectionDto> sectionDtos = new ArrayList<>();
            List<PreferenceDto> preferenceDtos = new ArrayList<>();

            // Get Dtos
            GetApplicants(applicantDtos, applicantRepository.getApplicants());
            GetSections(sectionDtos, sectionRepository.getSections());
            GetPreferences(preferenceDtos, preferenceRepository.getPreferences());

            // Initialize Enrollment Sections
            List<EnrollmentDto> enrollmentDtos = InitializeEnrollmentSections(sectionDtos);

            // Sort Applicants
            SortDescendingGrade(applicantDtos);
            // Sort Preferences
            SortAscendingPreference(preferenceDtos);

            // Go through applicants
            for (ApplicantDto applicantDto : applicantDtos) {
                List<PreferenceDto> tempPreferenceDtos = preferenceDtos.stream().filter((preference) -> preference.getApplicantId() == applicantDto.getApplicantId()).collect(Collectors.toList());

                Boolean assigned = false;
                // Go through applicant's preferences and find one available
                for(PreferenceDto preferenceDto : tempPreferenceDtos){
                    if(!assigned) {
                        EnrollmentDto tempEnrollmentDto = enrollmentDtos.stream().filter((enrollment) -> enrollment.getSection().getSectionId() == preferenceDto.getSectionId()).findFirst().orElse(null);
                        if (tempEnrollmentDto != null) {
                            // Available Spots
                            if (tempEnrollmentDto.getApplicants().size() < tempEnrollmentDto.getSection().getMaxStudents()) {
                                tempEnrollmentDto.getApplicants().add(applicantDto);
                                assigned = true;
                            }
                        }
                    }
                }
                if (!assigned){
                    unsuccessfulApplicantDtos.add(applicantDto);
                }
            }

            return enrollmentDtos;

        } catch (SQLException e) {
            new SqlConnectException(e.getMessage());
            return new ArrayList<>();
        }
    }

    public String GetEnrollmentMessage(List<EnrollmentDto> enrollmentDtos){
        StringBuilder message = new StringBuilder();
        for(EnrollmentDto enrollmentDto : enrollmentDtos){
            message.append("Section #" + enrollmentDto.getSection().getSectionId() + ":");
            message.append("\n");
            for(ApplicantDto applicantDto : enrollmentDto.getApplicants()){
                message.append(applicantDto.getApplicantId());
                message.append('\t');
                message.append(applicantDto.getFirstName());
                message.append('\t');
                message.append(applicantDto.getLastName());
                message.append('\t');
                message.append(applicantDto.getGrade());
                message.append('\t');
                message.append(applicantDto.getAge());
                message.append("\n");
            }
            message.append("\n");
        }
        return message.toString();
    }

    private void GetApplicants(List<ApplicantDto> applicantDtos, List<ApplicantEntity> applicants) throws SQLException {
        for (ApplicantEntity applicantEntity : applicants) {
            applicantDtos.add(applicantToApplicantDTO(applicantEntity));
        }
    }

    private void GetSections(List<SectionDto> sectionDtos, List<SectionEntity> sections) throws SQLException {
        for (SectionEntity sectionEntity : sections) {
            sectionDtos.add(sectionToSectionDTO(sectionEntity));
        }
    }

    private void GetPreferences(List<PreferenceDto> preferenceDtos, List<PreferenceEntity> preferences) throws SQLException {
        for (PreferenceEntity preferenceEntity : preferences) {
            preferenceDtos.add(preferenceToPreferenceDTO(preferenceEntity));
        }
    }

    private void SortDescendingGrade(List<ApplicantDto> applicantDtos){
        Collections.sort(applicantDtos, new Comparator<ApplicantDto>() {
            @Override
            public int compare(ApplicantDto o1, ApplicantDto o2) {
                if(o1.getGrade() >= o2.getGrade()) {
                    return -1;
                }
                return 1;
            }
        });
    }

    private void SortAscendingPreference(List<PreferenceDto> preferenceDtos){
        Collections.sort(preferenceDtos, new Comparator<PreferenceDto>() {
            @Override
            public int compare(PreferenceDto o1, PreferenceDto o2) {
                if(o1.getPreferenceIndex() >= o2.getPreferenceIndex()) {
                    return 1;
                }
                return -1;
            }
        });
    }

    private List<EnrollmentDto> InitializeEnrollmentSections(List<SectionDto> sectionDtos){
        List<EnrollmentDto> enrollmentDtos = new ArrayList<>();

        for(SectionDto sectionDto : sectionDtos){
            enrollmentDtos.add(new EnrollmentDto(sectionDto, new ArrayList<ApplicantDto>()));
        }

        return enrollmentDtos;
    }

    private ApplicantDto applicantToApplicantDTO(ApplicantEntity entity) {
        ApplicantDto dto = new ApplicantDto();
        dto.setLastName(entity.getLastName());
        dto.setFirstName(entity.getFirstName());
        dto.setAge(entity.getAge());
        dto.setGrade(entity.getGrade());;
        dto.setApplicantId(entity.getApplicantId());
        return dto;
    }

    private ApplicantEntity applicantDTOToEntity(ApplicantDto dto) {
        ApplicantEntity entity = new ApplicantEntity(dto.getApplicantId(), dto.getLastName(),
                dto.getFirstName(), dto.getAge(), dto.getGrade());
        return entity;
    }

    private SectionDto sectionToSectionDTO(SectionEntity entity) {
        SectionDto dto = new SectionDto();
        dto.setName(entity.getName());
        dto.setNumberOfYears(entity.getNumberOfYears());
        dto.setMaxStudents(entity.getMaxStudents());
        dto.setSectionId(entity.getSectionId());
        return dto;
    }

    private SectionEntity sectionDTOToEntity(SectionDto dto) {
        SectionEntity entity = new SectionEntity(dto.getSectionId(), dto.getName(),
                dto.getNumberOfYears(), dto.getMaxStudents());
        return entity;
    }

    private PreferenceDto preferenceToPreferenceDTO(PreferenceEntity entity) {
        PreferenceDto dto = new PreferenceDto();
        dto.setSectionId(entity.getSectionId());
        dto.setApplicantId(entity.getApplicantId());
        dto.setPreferenceIndex(entity.getPreferenceIndex());
        return dto;
    }

    private PreferenceEntity preferenceDTOToEntity(PreferenceDto dto) {
        PreferenceEntity entity = new PreferenceEntity(dto.getApplicantId(), dto.getSectionId(),
                dto.getPreferenceIndex());
        return entity;
    }
}
