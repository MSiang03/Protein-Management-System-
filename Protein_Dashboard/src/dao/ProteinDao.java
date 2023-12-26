package dao;

import login_register.DatabaseConnection;
import protein.Protein;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProteinDao {

    public static void insert(Protein protein){
        String sql = "INSERT INTO protein(id,proteins,sequences,length,annotations) values(?,?,?,?,?)";
        Connection connDB = DatabaseConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connDB.prepareStatement(sql);
            statement.setObject(1,null);
            statement.setObject(2,protein.getProteins());
            statement.setObject(3,protein.getSequences());
            statement.setObject(4,protein.getSequences().length());
            statement.setObject(5,protein.getAnnotations());
            //statement.setObject(5,null);
            statement.executeUpdate();
            //关闭资源
            statement.close();
            connDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static List<Protein> getAll(){
        Connection connDB = DatabaseConnection.getConnection();
        List<Protein> lists = new ArrayList<>();
        try {
            Statement statement = connDB.createStatement();
            String sql = "select * from protein";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                lists.add(new Protein(rs.getInt("id"),rs.getString("proteins"),rs.getString("sequences"),rs.getInt("length"),rs.getString("annotations")));
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return lists;
    }

    public static List<Protein> selBySeq(String name){
        Connection connDB = DatabaseConnection.getConnection();
        List<Protein> lists = new ArrayList<>();
        try {
            Statement statement = connDB.createStatement();
            String sql = "select * from protein where sequences like '%" + name +"%'";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                lists.add(new Protein(rs.getInt("id"),rs.getString("proteins"),rs.getString("sequences"),rs.getInt("length"),rs.getString("annotations")));
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return lists;
    }

}
