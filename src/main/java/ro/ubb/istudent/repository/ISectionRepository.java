package ro.ubb.istudent.repository;

import ro.ubb.istudent.domain.ApplicantEntity;
import ro.ubb.istudent.domain.SectionEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ISectionRepository {

    Optional<SectionEntity> findSectionEntityById(int sectionId) throws SQLException;
    List<SectionEntity> getSections() throws SQLException;
    void createSection(SectionEntity sectionEntity) throws SQLException;
    void updateSection(SectionEntity sectionEntity) throws SQLException;
    void deleteSection(int sectionId) throws SQLException;

    void truncateTable() throws SQLException;
}
