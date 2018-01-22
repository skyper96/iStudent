package ro.ubb.istudent;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.ubb.istudent.dto.ApplicantDto;
import ro.ubb.istudent.dto.PreferenceDto;
import ro.ubb.istudent.dto.SectionDto;
import ro.ubb.istudent.exception.GeneralException;
import ro.ubb.istudent.repository.ApplicantRepository;
import ro.ubb.istudent.repository.PreferenceRepository;
import ro.ubb.istudent.repository.SectionRepository;
import ro.ubb.istudent.service.*;

@SpringBootApplication
public class IStudentApplication {

	public static void main(String[] args) {
		PubSubService pubSubService = new PubSubService();
		ApplicantService applicantService = new ApplicantService(new ApplicantRepository());
		SectionService sectionService = new SectionService(new SectionRepository());
		PreferenceService preferenceService = new PreferenceService(new PreferenceRepository());
		EnrollService enrollService = new EnrollService(new ApplicantRepository(), new SectionRepository(), new PreferenceRepository());

		//Applicants
		//System.out.println(applicantService.findApplicantById(1).toString());
		//applicantService.getApplicant().forEach(x -> System.out.println(x.toString()));
		//applicantService.createApplicant(new ApplicantDto(3, "Mihai", "Radu", 21, 10));
		//applicantService.updateApplicant(new ApplicantDto(2, "Raul", "Sergiu", 22, 9));
		//applicantService.deleteApplicant(3);

		//Sections
		//System.out.println(sectionService.findSectionById(1).toString());
		//sectionService.getSections().forEach(x -> System.out.println(x.toString()));
		//sectionService.createSection(new SectionDto(1, "UBB Mate-Info", 3, 3));
		//sectionService.createSection(new SectionDto(2, "UBB Info",  3, 2));
		//sectionService.createSection(new SectionDto(3, "UBB Infoo",  4, 3));
		//sectionService.updateSection(new SectionDto(3, "Poli",  4, 4));
		//sectionService.createSection(new SectionDto(4, "UBB Infof",  4, 3));
		//sectionService.deleteSection(4);

		//Preferences
		//System.out.println(preferenceService.findPreferenceById(1,1).toString());
		//preferenceService.getPreferences().forEach(x -> System.out.println(x.toString()));
		//preferenceService.createPreference(new PreferenceDto(2,3,3));
		//preferenceService.updatePreference(new PreferenceDto(2,1,3));
		//preferenceService.deletePreference(2,3);

		//try {
		//	pubSubService.createTopic();
		//} catch (Exception e) {
		//	throw new GeneralException(e.getMessage());
		//}

		preferenceService.truncateTable();
		applicantService.truncateTable();
		sectionService.truncateTable();

        //Enrollment
		applicantService.createApplicant(new ApplicantDto(1, "Abel", "Trif", 21, 9.26));
		applicantService.createApplicant(new ApplicantDto(2, "Betuel", "Pop", 22, 7.83));
		applicantService.createApplicant(new ApplicantDto(3, "Cristi", "Muresan", 22, 5.15));
		applicantService.createApplicant(new ApplicantDto(4, "Andrei", "Popescu", 20, 8.63));
		applicantService.createApplicant(new ApplicantDto(5, "Paul", "Ardelean", 21, 10));
		applicantService.createApplicant(new ApplicantDto(6, "Ciprian", "Anghelescu", 21, 9.21));
		applicantService.createApplicant(new ApplicantDto(7, "Dragos", "Lungu", 21, 6.77));
		applicantService.createApplicant(new ApplicantDto(8, "Miron", "Constantinescu", 21, 7.64));
		applicantService.createApplicant(new ApplicantDto(8, "Miron", "Constantinescu", 21, 7.8));
		applicantService.createApplicant(new ApplicantDto(9, "Beniamin", "Funar", 21, 7.5));
		applicantService.createApplicant(new ApplicantDto(10, "Magda", "Popescu", 20, 9.58));
		applicantService.createApplicant(new ApplicantDto(11, "Anamaria", "Sala", 20, 10));
		applicantService.createApplicant(new ApplicantDto(12, "Sanda", "Ionescu", 20, 7.93));

		sectionService.createSection(new SectionDto(1, "Informatica Engleza", 3, 3));
		sectionService.createSection(new SectionDto(2, "Informatica Romana", 3, 2));

		for(int i = 1; i <= 12; i++){
			preferenceService.createPreference(new PreferenceDto(i, 1, i%2 == 0 ? 1 : 2));
			preferenceService.createPreference(new PreferenceDto(i, 2, i%2 == 0 ? 2 : 1));
		}

		enrollService.enroll();

		System.out.println(enrollService.GetEnrollmentMessage(enrollService.enroll()));

		try {
			//pubSubService.publishMessages(enrollService.GetEnrollmentMessage(enrollService.enroll()));
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}
	}
}
