package ro.ubb.istudent.repository;

import ro.ubb.istudent.domain.SectionEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SectionRepository implements ISectionRepository {

    private String connection = "jdbc:mysql://localhost:3306/collegeenrollment";
    private String usr = "root";
    private String pwd = "careva";

    @Override
    public Optional<SectionEntity> findSectionEntityById(int sectionId) throws SQLException {
        Connection myConn = DriverManager.getConnection(connection, usr, pwd);
        PreparedStatement myStmt = myConn.prepareStatement("select * from sections where sectionId = ?");
        myStmt.setInt(1, sectionId);
        ResultSet myRs = myStmt.executeQuery();
        SectionEntity sectionEntity = null;
        while(myRs.next()){
            sectionEntity = new SectionEntity();
            sectionEntity.setSectionId(myRs.getInt("sectionId"));
            sectionEntity.setName(myRs.getString("name"));
            sectionEntity.setNumberOfYears(myRs.getInt("numberOfYears"));
            sectionEntity.setMaxStudents(myRs.getInt("maxStudents"));
        }
        return Optional.ofNullable(sectionEntity);
    }

    @Override
    public List<SectionEntity> getSections() throws SQLException {
        Connection myConn = DriverManager.getConnection(connection, usr, pwd);
        PreparedStatement myStmt = myConn.prepareStatement("select * from sections");
        ResultSet myRs = myStmt.executeQuery();
        List<SectionEntity> sectionEntities = new ArrayList<SectionEntity>();
        while(myRs.next()){
            SectionEntity sectionEntity = new SectionEntity();
            sectionEntity.setSectionId(myRs.getInt("sectionId"));
            sectionEntity.setName(myRs.getString("name"));
            sectionEntity.setNumberOfYears(myRs.getInt("numberOfYears"));
            sectionEntity.setMaxStudents(myRs.getInt("maxStudents"));

            sectionEntities.add(sectionEntity);
        }
        return sectionEntities;
    }

    @Override
    public void createSection(SectionEntity sectionEntity) throws SQLException {
        Connection myConn = DriverManager.getConnection(connection, usr, pwd);
        PreparedStatement myStmt = myConn.prepareStatement("INSERT INTO `collegeenrollment`.`sections` (`sectionId`, `name`, `numberOfYears`, `maxStudents`) VALUES (?, ?, ?, ?);");
        myStmt.setInt(1, sectionEntity.getSectionId());
        myStmt.setString(2, sectionEntity.getName());
        myStmt.setInt(3, sectionEntity.getNumberOfYears());
        myStmt.setInt(4, sectionEntity.getMaxStudents());
        myStmt.executeUpdate();
    }

    @Override
    public void updateSection(SectionEntity sectionEntity) throws SQLException {
        if(findSectionEntityById(sectionEntity.getSectionId()) != null){
            Connection myConn = DriverManager.getConnection(connection, usr, pwd);
            PreparedStatement myStmt = myConn.prepareStatement("UPDATE `collegeenrollment`.`sections` SET `name` = ?, `numberofYears` = ?, `maxStudents` = ? WHERE `sectionId` = ?");
            myStmt.setString(1, sectionEntity.getName());
            myStmt.setInt(2, sectionEntity.getNumberOfYears());
            myStmt.setInt(3, sectionEntity.getMaxStudents());
            myStmt.setInt(4, sectionEntity.getSectionId());
            myStmt.executeUpdate();
        }
    }

    @Override
    public void deleteSection(int sectionId) throws SQLException {
        Connection myConn = DriverManager.getConnection(connection, usr, pwd);
        PreparedStatement myStmt = myConn.prepareStatement("DELETE FROM `collegeenrollment`.`sections` WHERE `sectionId` = ?");
        myStmt.setInt(1, sectionId);
        myStmt.executeUpdate();
    }

    @Override
    public void truncateTable() throws SQLException {
        Connection myConn = DriverManager.getConnection(connection, usr, pwd);
        Statement myStmt = myConn.createStatement();
        myStmt.execute("TRUNCATE TABLE `collegeenrollment`.`sections`");
    }
}
