package controller;

/**
 *
 * @author HP
 */

import java.util.List;
import DAOmusic.DAOMusic;
import DAOimplement.MusicImplement;
import model.*;
import view.MainView;
import javax.swing.JOptionPane;

public class MusicController {
    MainView frame;
    MusicImplement implMusic;
    List<DataMusic> dm;
    
    private String deleteValue;
    private Double tmpHasil;
    private Double tmpRating;
    private Double tmpRateResult;
    private boolean insertError = false;
    private boolean updateError = false;
    private boolean deleteError = false;

    private boolean isFormValid() {
        return !frame.getJTxtJudul().getText().isEmpty()
            && !frame.getJTxtGenre().getText().isEmpty()
            && !frame.getJTxtArtis().getText().isEmpty()
            && !frame.getJTxtLink().getText().isEmpty();
    }

    public MusicController (MainView frame){
    this.frame = frame; 
    implMusic = new DAOMusic();
    dm = implMusic.getAll();
    }
    
    public void isitable(){
        dm = implMusic.getAll();
        ModelTabelMusic mt = new ModelTabelMusic(dm);
        frame.getTableData().setModel(mt);
    }
    public void insert() {
        if (isFormValid()) {
            try {
                DataMusic music = new DataMusic();
                music.setJudul(frame.getJTxtJudul().getText());
                music.setGenre(frame.getJTxtGenre().getText());
                music.setArtis(frame.getJTxtArtis().getText());
                music.setLink(frame.getJTxtLink().getText());     
                implMusic.insert(music);
                insertError = false;
            } catch (NumberFormatException ex) {
                // Tampilkan pesan bahwa ada input kosong
                JOptionPane.showMessageDialog(null, "Invalid input!", "Warning", JOptionPane.WARNING_MESSAGE);
                ex.printStackTrace();
                insertError = true;
            }
        } else {
            // Tampilkan pesan peringatan bahwa data belum diisi
            JOptionPane.showMessageDialog(null, "Please fill the form!", "Warning", JOptionPane.WARNING_MESSAGE);
            insertError = true;
        }
    }
    
     public void update() {
          if (isFormValid()) {
            try {
                DataMusic music = new DataMusic();
                music.setJudul(frame.getJTxtJudul().getText());
                music.setGenre(frame.getJTxtGenre().getText());
                music.setArtis(frame.getJTxtArtis().getText());
                music.setLink(frame.getJTxtLink().getText());     
                implMusic.update(music);
                updateError = false;
                JOptionPane.showMessageDialog(null, "Data Updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                // Tampilkan pesan bahwa ada input kosong
                JOptionPane.showMessageDialog(null, "Invalid input!", "Warning", JOptionPane.WARNING_MESSAGE);
                ex.printStackTrace();
                updateError = true;
            }
        } else {
            // Tampilkan pesan peringatan bahwa data belum diisi
            JOptionPane.showMessageDialog(null, "Please Fill the form!", "Warning", JOptionPane.WARNING_MESSAGE);
            updateError = true;
        }
    }
    
    public void delete() {
        if (isFormValid()) {
            try {
                DataMusic music = new DataMusic();
                deleteValue = frame.getJTxtJudul().getText();
                implMusic.delete(deleteValue);
                deleteError = false;
                JOptionPane.showMessageDialog(null, "Data Deleted!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                // Tampilkan pesan bahwa ada input kosong
                JOptionPane.showMessageDialog(null, "Invalid Input!", "Warning", JOptionPane.WARNING_MESSAGE);
                ex.printStackTrace();
                deleteError = true;
            }
        } else {
            // Tampilkan pesan peringatan bahwa data belum diisi
            JOptionPane.showMessageDialog(null, "Please fill the form!", "Warning", JOptionPane.WARNING_MESSAGE);
            deleteError = true;
        }
    }

    public boolean isInsertError() {
        return insertError;
    }

    public boolean isUpdateError() {
        return updateError;
    }

    public boolean isDeleteError() {
        return deleteError;
    }
}
