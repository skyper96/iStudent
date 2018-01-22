package ro.ubb.istudent.repository;

import ro.ubb.istudent.domain.PreferenceEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IPreferenceRepository {

    Optional<PreferenceEntity> findPreferenceByApplicantIdAndPreferenceIndex(int applicantId, int preferenceIndex) throws SQLException;
    List<PreferenceEntity> getPreferences() throws SQLException;
    void createPreference(PreferenceEntity preferenceEntity) throws SQLException;
    void updatePreference(PreferenceEntity preferenceEntity) throws SQLException;
    void deletePreference(int applicantId, int preferenceIndex) throws SQLException;

    void truncateTable() throws SQLException;
}
