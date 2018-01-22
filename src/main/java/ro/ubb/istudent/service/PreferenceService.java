package ro.ubb.istudent.service;

import org.springframework.stereotype.Service;
import ro.ubb.istudent.domain.PreferenceEntity;
import ro.ubb.istudent.dto.PreferenceDto;
import ro.ubb.istudent.exception.SqlConnectException;
import ro.ubb.istudent.repository.PreferenceRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PreferenceService {

    private final PreferenceRepository repository;

    public PreferenceService(PreferenceRepository repository) {
        this.repository = repository;
    }

    public Optional<PreferenceDto> findPreferenceById(int applicantId, int preferenceIndex) {
        try {
            return repository.findPreferenceByApplicantIdAndPreferenceIndex(applicantId, preferenceIndex)
                    .map(this::preferenceToPreferenceDTO);
        } catch (SQLException e) {
            new SqlConnectException(e.getMessage());
            return Optional.ofNullable(null);
        }
    }

    public List<PreferenceDto> getPreferences() {
        try {
            List<PreferenceDto> preferences = new ArrayList<PreferenceDto>();
            for (PreferenceEntity preferenceEntity : repository.getPreferences()) {
                preferences.add(preferenceToPreferenceDTO(preferenceEntity));
            }
            return preferences;
        } catch (SQLException e) {
            new SqlConnectException(e.getMessage());
            return new ArrayList<>();
        }
    }

    public void createPreference(PreferenceDto preferenceDto){
        try {
            repository.createPreference(preferenceDTOToEntity(preferenceDto));
        } catch (SQLException e) {
            new SqlConnectException(e.getMessage());
        }
    }

    public void updatePreference(PreferenceDto preferenceDto){
        try {
            repository.updatePreference(preferenceDTOToEntity(preferenceDto));
        } catch (SQLException e) {
            new SqlConnectException(e.getMessage());
        }
    }

    public void deletePreference(int applicantId, int preferenceIndex){
        try {
            repository.deletePreference(applicantId, preferenceIndex);
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

    private PreferenceDto preferenceToPreferenceDTO(PreferenceEntity entity) {
        PreferenceDto dto = new PreferenceDto();
        dto.setApplicantId(entity.getApplicantId());
        dto.setSectionId(entity.getSectionId());
        dto.setPreferenceIndex(entity.getPreferenceIndex());
        return dto;
    }

    private PreferenceEntity preferenceDTOToEntity(PreferenceDto dto) {
        PreferenceEntity entity = new PreferenceEntity(dto.getApplicantId(), dto.getSectionId(),
                dto.getPreferenceIndex());
        return entity;
    }
}
