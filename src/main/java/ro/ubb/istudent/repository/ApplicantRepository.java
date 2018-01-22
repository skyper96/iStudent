package ro.ubb.istudent.repository;

import javafx.application.Application;
import ro.ubb.istudent.domain.ApplicantEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApplicantRepository implements IApplicantRepository {

    private String connection = "jdbc:mysql://localhost:3306/collegeenrollment";
    private String usr = "root";
    private String pwd = "careva";

    @Override
    public Optional<ApplicantEntity> findApplicantEntityById(int applicantId) throws SQLException {
        Connection myConn = DriverManager.getConnection(connection, usr, pwd);
        PreparedStatement myStmt = myConn.prepareStatement("select * from applicants where applicantId = ?");
        myStmt.setInt(1, applicantId);
        ResultSet myRs = myStmt.executeQuery();
        ApplicantEntity applicantEntity = null;
        while(myRs.next()){
            applicantEntity = new ApplicantEntity();
            applicantEntity.setApplicantId(myRs.getInt("applicantId"));
            applicantEntity.setLastName(myRs.getString("lastName"));
            applicantEntity.setFirstName(myRs.getString("firstName"));
            applicantEntity.setAge(myRs.getInt("age"));
            applicantEntity.setGrade(myRs.getDouble("grade"));
        }
        return Optional.ofNullable(applicantEntity);
    }

    @Override
    public List<ApplicantEntity> getApplicants() throws SQLException {
        Connection myConn = DriverManager.getConnection(connection, usr, pwd);
        PreparedStatement myStmt = myConn.prepareStatement("select * from applicants");
        ResultSet myRs = myStmt.executeQuery();
        List<ApplicantEntity> applicantEntities = new ArrayList<ApplicantEntity>();
        while(myRs.next()){
            ApplicantEntity applicantEntity = new ApplicantEntity();
            applicantEntity.setApplicantId(myRs.getInt("applicantId"));
            applicantEntity.setLastName(myRs.getString("lastName"));
            applicantEntity.setFirstName(myRs.getString("firstName"));
            applicantEntity.setAge(myRs.getInt("age"));
            applicantEntity.setGrade(myRs.getDouble("grade"));

            applicantEntities.add(applicantEntity);
        }
        return applicantEntities;
    }

    @Override
    public void createApplicant(ApplicantEntity applicantEntity) throws SQLException {
        Connection myConn = DriverManager.getConnection(connection, usr, pwd);
        PreparedStatement myStmt = myConn.prepareStatement("INSERT INTO `collegeenrollment`.`applicants` (`applicantId`, `lastName`, `firstName`, `age`, `grade`) VALUES (?, ?, ?, ?, ?);");
        myStmt.setInt(1, applicantEntity.getApplicantId());
        myStmt.setString(2, applicantEntity.getLastName());
        myStmt.setString(3, applicantEntity.getFirstName());
        myStmt.setInt(4, applicantEntity.getAge());
        myStmt.setDouble(5, applicantEntity.getGrade());
        myStmt.executeUpdate();
    }

    @Override
    public void updateApplicant(ApplicantEntity applicantEntity) throws SQLException {
        if(findApplicantEntityById(applicantEntity.getApplicantId()) != null){
            Connection myConn = DriverManager.getConnection(connection, usr, pwd);
            PreparedStatement myStmt = myConn.prepareStatement("UPDATE `collegeenrollment`.`applicants` SET `lastName` = ?, `firstName` = ?, `age` = ?, `grade` = ? WHERE `applicantId` = ?");
            myStmt.setString(1, applicantEntity.getLastName());
            myStmt.setString(2, applicantEntity.getFirstName());
            myStmt.setInt(3, applicantEntity.getAge());
            myStmt.setDouble(4, applicantEntity.getGrade());
            myStmt.setInt(5, applicantEntity.getApplicantId());
            myStmt.executeUpdate();
        }
    }

    @Override
    public void deleteApplicant(int applicantId) throws SQLException {
        Connection myConn = DriverManager.getConnection(connection, usr, pwd);
        PreparedStatement myStmt = myConn.prepareStatement("DELETE FROM `collegeenrollment`.`applicants` WHERE `applicantId` = ?");
        myStmt.setInt(1, applicantId);
        myStmt.executeUpdate();
    }

    @Override
    public void truncateTable() throws SQLException {
        Connection myConn = DriverManager.getConnection(connection, usr, pwd);
        Statement myStmt = myConn.createStatement();
        myStmt.execute("TRUNCATE TABLE `collegeenrollment`.`applicants`");
    }
}
