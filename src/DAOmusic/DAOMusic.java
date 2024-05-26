package DAOmusic;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.Connector;
import DAOimplement.MusicImplement;
import model.DataMusic;
import javax.swing.JOptionPane;

public class DAOMusic implements MusicImplement {

    Connection connection;
    final String select = "SELECT * FROM music";
    final String insert = "INSERT INTO music (judul, genre, artis, link) VALUES (?, ?, ?, ?);";
    final String update = "UPDATE music SET genre = ?, artis = ?, link = ? WHERE judul = ?;";
    final String delete = "DELETE FROM music WHERE judul= ?";

    public DAOMusic() {
        connection = Connector.connection();
    }

    public void insert (DataMusic dm){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, dm.getJudul());
            statement.setString(2, dm.getGenre());
            statement.setString(3, dm.getArtis());
            statement.setString(4, dm.getLink());           
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Data gagal ditambahkan: Data dengan primary key tersebut sudah ada.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data gagal ditambahkan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
   
    
    public void update (DataMusic dm){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, dm.getGenre());
            statement.setString(2, dm.getArtis());
            statement.setString(3, dm.getLink());
            statement.setString(4, dm.getJudul());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    public void delete (String judul){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, judul);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void select (DataMusic dm){}
    public List<DataMusic> getAll(){
        List<DataMusic> dm = null;
        try {
            dm = new ArrayList<DataMusic>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            while (resultSet.next()) {
                DataMusic music = new DataMusic();
                music.setId(resultSet.getInt("id"));
                music.setJudul(resultSet.getString("judul"));
                music.setGenre(resultSet.getString("genre"));
                music.setArtis(resultSet.getString("artis"));
                music.setLink(resultSet.getString("link"));
                dm.add(music);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOMusic.class.getName()).log(Level.SEVERE, null, ex);
        }
    return dm;
    }
}
