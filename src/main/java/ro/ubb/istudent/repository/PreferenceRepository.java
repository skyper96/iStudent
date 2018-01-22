package ro.ubb.istudent.repository;

import ro.ubb.istudent.domain.PreferenceEntity;
import ro.ubb.istudent.domain.SectionEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PreferenceRepository implements IPreferenceRepository {

    private String connection = "jdbc:mysql://localhost:3306/collegeenrollment";
    private String usr = "root";
    private String pwd = "careva";

    @Override
    public Optional<PreferenceEntity> findPreferenceByApplicantIdAndPreferenceIndex(int applicantId, int preferenceIndex) throws SQLException {
        Connection myConn = DriverManager.getConnection(connection, usr, pwd);
        PreparedStatement myStmt = myConn.prepareStatement("select * from `sectionpreferences` where `applicantId` = ? and `preferenceIndex` = ?");
        myStmt.setInt(1, applicantId);
        myStmt.setInt(2, preferenceIndex);
        ResultSet myRs = myStmt.executeQuery();
        PreferenceEntity preferenceEntity = null;
        while(myRs.next()){
            preferenceEntity = new PreferenceEntity();
            preferenceEntity.setApplicantId(myRs.getInt("applicantId"));
            preferenceEntity.setSectionId(myRs.getInt("sectionId"));
            preferenceEntity.setPreferenceIndex(myRs.getInt("preferenceIndex"));
        }
        return Optional.ofNullable(preferenceEntity);
    }

    @Override
    public List<PreferenceEntity> getPreferences() throws SQLException {
        Connection myConn = DriverManager.getConnection(connection, usr, pwd);
        PreparedStatement myStmt = myConn.prepareStatement("select * from `sectionpreferences`");
        ResultSet myRs = myStmt.executeQuery();
        List<PreferenceEntity> preferenceEntities = new ArrayList<PreferenceEntity>();
        while(myRs.next()){
            PreferenceEntity preferenceEntity = new PreferenceEntity();
            preferenceEntity.setSectionId(myRs.getInt("sectionId"));
            preferenceEntity.setApplicantId(myRs.getInt("applicantId"));
            preferenceEntity.setPreferenceIndex(myRs.getInt("preferenceIndex"));

            preferenceEntities.add(preferenceEntity);
        }
        return preferenceEntities;
    }

    @Override
    public void createPreference(PreferenceEntity preferenceEntity) throws SQLException {
        Connection myConn = DriverManager.getConnection(connection, usr, pwd);
        PreparedStatement myStmt = myConn.prepareStatement("INSERT INTO `collegeenrollment`.`sectionpreferences` (`applicantId`, `sectionId`, `preferenceIndex`) VALUES (?, ?, ?);");
        myStmt.setInt(1, preferenceEntity.getApplicantId());
        myStmt.setInt(2, preferenceEntity.getSectionId());
        myStmt.setInt(3, preferenceEntity.getPreferenceIndex());
        myStmt.executeUpdate();
    }

    @Override
    public void updatePreference(PreferenceEntity preferenceEntity) throws SQLException {
        if(findPreferenceByApplicantIdAndPreferenceIndex(preferenceEntity.getApplicantId(), preferenceEntity.getPreferenceIndex()) != null){
            Connection myConn = DriverManager.getConnection(connection, usr, pwd);
            PreparedStatement myStmt = myConn.prepareStatement("UPDATE `collegeenrollment`.`sectionpreferences` SET `sectionId` = ? WHERE `applicantId` = ? and `preferenceIndex` = ?");
            myStmt.setInt(1, preferenceEntity.getSectionId());
            myStmt.setInt(2, preferenceEntity.getApplicantId());
            myStmt.setInt(3, preferenceEntity.getPreferenceIndex());
            myStmt.executeUpdate();
        }
    }

    @Override
    public void deletePreference(int applicantId, int preferenceIndex) throws SQLException {
        Connection myConn = DriverManager.getConnection(connection, usr, pwd);
        PreparedStatement myStmt = myConn.prepareStatement("DELETE FROM `collegeenrollment`.`sectionpreferences` WHERE `applicantId` = ? and `preferenceIndex` = ?");
        myStmt.setInt(1, applicantId);
        myStmt.setInt(2, preferenceIndex);
        myStmt.executeUpdate();
    }

    @Override
    public void truncateTable() throws SQLException {
        Connection myConn = DriverManager.getConnection(connection, usr, pwd);
        Statement myStmt = myConn.createStatement();
        myStmt.execute("TRUNCATE TABLE `collegeenrollment`.`sectionpreferences`");
    }
}
