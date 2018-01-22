package ro.ubb.istudent.service;

import org.springframework.stereotype.Service;
import ro.ubb.istudent.domain.SectionEntity;
import ro.ubb.istudent.dto.SectionDto;
import ro.ubb.istudent.exception.SqlConnectException;
import ro.ubb.istudent.repository.SectionRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    private final SectionRepository repository;

    public SectionService(SectionRepository repository) {
        this.repository = repository;
    }

    public Optional<SectionDto> findSectionById(int sectionId) {
        try {
            return repository.findSectionEntityById(sectionId)
                    .map(this::sectionToSectionDTO);
        } catch (SQLException e) {
            new SqlConnectException(e.getMessage());
            return Optional.ofNullable(null);
        }
    }

    public List<SectionDto> getSections() {
        try {
            List<SectionDto> sections = new ArrayList<SectionDto>();
            for (SectionEntity sectionEntity : repository.getSections()) {
                sections.add(sectionToSectionDTO(sectionEntity));
            }
            return sections;
        } catch (SQLException e) {
            new SqlConnectException(e.getMessage());
            return new ArrayList<>();
        }
    }

    public void createSection(SectionDto sectionDto){
        try {
            repository.createSection(sectionDTOToEntity(sectionDto));
        } catch (SQLException e) {
            new SqlConnectException(e.getMessage());
        }
    }

    public void updateSection(SectionDto sectionDto){
        try {
            repository.updateSection(sectionDTOToEntity(sectionDto));
        } catch (SQLException e) {
            new SqlConnectException(e.getMessage());
        }
    }

    public void deleteSection(int sectionId){
        try {
            repository.deleteSection(sectionId);
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

    private SectionDto sectionToSectionDTO(SectionEntity entity) {
        SectionDto dto = new SectionDto();
        dto.setSectionId(entity.getSectionId());
        dto.setName(entity.getName());
        dto.setNumberOfYears(entity.getNumberOfYears());
        dto.setMaxStudents(entity.getMaxStudents());
        return dto;
    }

    private SectionEntity sectionDTOToEntity(SectionDto dto) {
        SectionEntity entity = new SectionEntity(dto.getSectionId(), dto.getName(),
                dto.getNumberOfYears(), dto.getMaxStudents());
        return entity;
    }
}
