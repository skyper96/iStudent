package ro.ubb.istudent.service;

import org.springframework.stereotype.Service;
import ro.ubb.istudent.domain.ApplicantEntity;
import ro.ubb.istudent.dto.ApplicantDto;
import ro.ubb.istudent.exception.SqlConnectException;
import ro.ubb.istudent.repository.ApplicantRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicantService {

    private final ApplicantRepository repository;

    public ApplicantService(ApplicantRepository repository) {
        this.repository = repository;
    }

    public Optional<ApplicantDto> findApplicantById(int applicantId) {
        try {
            return repository.findApplicantEntityById(applicantId)
                    .map(this::applicantToApplicantDTO);
        } catch (SQLException e) {
            new SqlConnectException(e.getMessage());
            return Optional.ofNullable(null);
        }
    }

    public List<ApplicantDto> getApplicants() {
        try {
            List<ApplicantDto> applicants = new ArrayList<ApplicantDto>();
            for (ApplicantEntity applicantEntity : repository.getApplicants()) {
                applicants.add(applicantToApplicantDTO(applicantEntity));
            }
            return applicants;
        } catch (SQLException e) {
            new SqlConnectException(e.getMessage());
            return new ArrayList<>();
        }
    }

    public void createApplicant(ApplicantDto applicantDto){
        try {
            repository.createApplicant(applicantDTOToEntity(applicantDto));
        } catch (SQLException e) {
            new SqlConnectException(e.getMessage());
        }
    }

    public void updateApplicant(ApplicantDto applicantDto){
        try {
            repository.updateApplicant(applicantDTOToEntity(applicantDto));
        } catch (SQLException e) {
            new SqlConnectException(e.getMessage());
        }
    }

    public void deleteApplicant(int applicantId){
        try {
            repository.deleteApplicant(applicantId);
        } catch (SQLException e) {
            new SqlConnectException(e.getMessage());
        }
    }

    public void truncateTable(){
        try {
            repository.truncateTable();
        } catch (SQLException e) {
            new SqlConnectException(e.getMessage());
        }
    }

    public ApplicantDto applicantToApplicantDTO(ApplicantEntity entity) {
        ApplicantDto dto = new ApplicantDto();
        dto.setApplicantId(entity.getApplicantId());
        dto.setLastName(entity.getLastName());
        dto.setFirstName(entity.getFirstName());
        dto.setAge(entity.getAge());
        dto.setGrade(entity.getGrade());
        return dto;
    }

    public ApplicantEntity applicantDTOToEntity(ApplicantDto dto) {
        ApplicantEntity entity = new ApplicantEntity(dto.getApplicantId(), dto.getLastName(),
                dto.getFirstName(), dto.getAge(), dto.getGrade());
        return entity;
    }
}
