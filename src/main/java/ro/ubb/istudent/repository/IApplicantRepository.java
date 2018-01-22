package ro.ubb.istudent.repository;

import ro.ubb.istudent.domain.ApplicantEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IApplicantRepository {

    Optional<ApplicantEntity> findApplicantEntityById(int applicantId) throws SQLException;
    List<ApplicantEntity> getApplicants() throws SQLException;
    void createApplicant(ApplicantEntity applicantEntity) throws SQLException;
    void updateApplicant(ApplicantEntity applicantEntity) throws SQLException;
    void deleteApplicant(int applicantId) throws SQLException;

    void truncateTable() throws SQLException;
}
