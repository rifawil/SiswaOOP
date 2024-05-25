
package sekolahproject.siswa;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import sekolahproject.home.HomeView;
import sekolahproject.utilities.Koneksi;


public class SiswaController {
    
    private final SiswaTableModel stm = new SiswaTableModel();
    
    public void setMaximumFrame(SiswaView sv) {
        try {
            sv.setMaximum(true);
        } catch (Exception error) {
            System.err.println("Error at SiswaController-setMaximumFrame, details : "+ error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-setMaximumFrame, details : "+ error.toString());
        }
    }
    
    public void setUndercoreted(SiswaView sv) {
        try {
            sv.putClientProperty("JInternalFrame.isPallete", Boolean.TRUE);
            BasicInternalFrameUI basicInternalFrameUI = (BasicInternalFrameUI) sv.getUI();
            basicInternalFrameUI.setNorthPane(null);
        } catch (Exception error) {
            System.err.println("Error at SiswaController-setUndercoreted, details : "+ error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-setUndercoreted, details : "+ error.toString());
        }
    }
    
    public void setTableModel(SiswaView sv) {
        try {
            SiswaView.tableSiswa.setModel(stm);
        } catch (Exception error) {
            System.err.println("Error at SiswaController-setTableModel, details : "+ error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-setTableModel, details : "+ error.toString());
        }
    }
    
    public void loadData(SiswaView sv) {
        try {
            Statement statement;
            ResultSet rs;
            
            String sqlSelect = "SELECT * FROM tb_siswa ORDER BY nis ASC";
            
            statement = Koneksi.getConnection().createStatement();
            rs = statement.executeQuery(sqlSelect);
            
            List<Siswa> list = new ArrayList<>();
            while (rs.next()){
                String jenkel = "";
                if (rs.getString("jenkel").equals("L")) {
                    jenkel = "Laki-Laki";
                } else if (rs.getString("jenkel").equals("P")) {
                    jenkel = "Perempuan";
                }
                
                Siswa s = new Siswa();
                s.setNis(rs.getString("nis"));
                s.setNama(rs.getString("nama"));
                s.setJenkel(jenkel);
                s.setKelas(rs.getString("kelas"));
                s.setJurusan(rs.getString("jurusan"));
                s.setAlamat(rs.getString("alamat"));
                list.add(s);
            }
            stm.setList(list);
        } catch (Exception error) {
            System.err.println("Error at SiswaController-loadData, details : "+ error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-loadData, details : "+ error.toString());
        }
    }
    
    public void searchData(SiswaView sv){
           try {
            String parameter = "";
            if (SiswaView.comboCari.getSelectedIndex()== 0){
                parameter = "nis";
            }else if (SiswaView.comboCari.getSelectedIndex()== 1){
                parameter = "nama";
            }else if (SiswaView.comboCari.getSelectedIndex()== 2){
                parameter = "kelas";
            }else if (SiswaView.comboCari.getSelectedIndex()== 3){
                parameter = "jurusan";
            }
            
            String keyword = SiswaView.textCari.getText();
            
            Statement statement;
            ResultSet rs;
            
            String sqlSelect = "SELECT * FROM tb_siswa WHERE " + parameter + " LIKE '%" + keyword + "%' ORDER BY nis ASC";
            
            statement = Koneksi.getConnection().createStatement();
            rs = statement.executeQuery(sqlSelect);
            
            List<Siswa> list = new ArrayList<>();
            while(rs.next()){
                String jenkel = "";
                if(rs.getString("jenkel").equals("L")) {
                    jenkel = "Laki-Laki";
                }else if(rs.getString("jenkel").equals("P")) {
                    jenkel = "Perempuan";
                }
                
                Siswa s = new Siswa();
                s.setNis(rs.getString("nis"));
                s.setNama(rs.getString("nama"));
                s.setJenkel(jenkel);
                s.setKelas(rs.getString("kelas"));
                s.setJurusan(rs.getString("jurusan"));
                s.setAlamat(rs.getString("alamat"));
                list.add(s);
            }
            stm.setList(list);
        } catch (Exception error) {
            System.err.println("Error at SiswaController-searchData, details : "+ error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-searchData, details : "+ error.toString());
        }
    }
    
    public void refresh(SiswaView sv){
        try {
            SiswaView.labelStatus.setText("");
            SiswaView.textNis.setText("");
            SiswaView.textNama.setText("");
            SiswaView.radioJenkel1.setSelected(true);
            SiswaView.comboKelas.setSelectedIndex(0);
            SiswaView.comboJurusan.setSelectedIndex(0);
            SiswaView.textAreaAlamat.setText("");
            SiswaView.comboCari.setSelectedIndex(0);
            SiswaView.textCari.setText("");
            SiswaView.tableSiswa.clearSelection();
            
            SiswaView.textNis.setEnabled(false);
            SiswaView.textNama.setEnabled(false);
            SiswaView.radioJenkel1.setEnabled(false);
            SiswaView.radioJenkel2.setEnabled(false);
            SiswaView.comboKelas.setEnabled(false);
            SiswaView.comboJurusan.setEnabled(false);
            SiswaView.textAreaAlamat.setEnabled(false);
            SiswaView.buttonSimpan.setEnabled(false);
            SiswaView.buttonBatal.setEnabled(false);
            
            SiswaView.comboCari.setEnabled(true);
            SiswaView.textCari.setEnabled(true);
            SiswaView.tableSiswa.setEnabled(true);
            SiswaView.buttonTambah.setEnabled(true);
            SiswaView.buttonUbah.setEnabled(true);
            SiswaView.buttonHapus.setEnabled(true);
            SiswaView.buttonSegarkan.setEnabled(true);
            SiswaView.buttonTutup.setEnabled(true);
            
            loadData(sv);
        } catch (Exception error){
            System.err.println("Error at SiswaController-refresh, details : "+ error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-refresh, details : "+ error.toString());
        }
    }
    
    public void tableSiswaAction(final SiswaView sv){
        SiswaView.tableSiswa.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int row = SiswaView.tableSiswa.getSelectedRow();
            
            if (row != -1){
                Siswa s = stm.get(row);
                
                SiswaView.textNis.setText(s.getNis());
                SiswaView.textNama.setText(s.getNama());
                if (s.getJenkel().equals("Laki-Laki")){
                    SiswaView.radioJenkel1.setSelected(true);
                    SiswaView.radioJenkel2.setSelected(false);
                }else if (s.getJenkel().equals("Perempuan")){
                    SiswaView.radioJenkel1.setSelected(false);
                    SiswaView.radioJenkel2.setSelected(true);
                }
                
                SiswaView.comboKelas.setSelectedItem(s.getKelas());
                SiswaView.comboJurusan.setSelectedItem(s.getJurusan());
                SiswaView.textAreaAlamat.setText(s.getAlamat());
            }
        }
        });
    }
    
    
    private String getNisOtomatis(SiswaView sv) {
    String nis = "";
    try {
        Statement statement;
        ResultSet rs;
        
        String sqlSelect = "SELECT nis FROM tb_siswa ORDER BY nis DESC LIMIT 1";
        
        statement = Koneksi.getConnection().createStatement();
        rs = statement.executeQuery(sqlSelect);
        
        if (rs.next()) {
            String nisOnDB = rs.getString("nis");
            
            int nisTerakhir = Integer.parseInt(nisOnDB.substring(4));
            if ((nisTerakhir >= 1) && (nisTerakhir < 10)) {
                nis = "NIS-000" + (nisTerakhir + 1);
            } else if ((nisTerakhir >= 10) && (nisTerakhir < 100)) {
                nis = "NIS-00" + (nisTerakhir + 1);
            } else if ((nisTerakhir >= 100) && (nisTerakhir < 1000)) {
                nis = "NIS-0" + (nisTerakhir + 1);
            } else if ((nisTerakhir >= 1000) && (nisTerakhir < 10000)) {
                nis = "NIS-" + (nisTerakhir + 1);
            }
        } else {
            nis = "NIS-0001";
        }
        rs.close(); // Close ResultSet when done
    } catch (SQLException | NumberFormatException error) {
        System.err.println("Error at SiswaController-getNisOtomatis, details : "+ error.toString());
        JOptionPane.showMessageDialog(sv, "Error at SiswaController-getNisOtomatis, details : "+ error.toString());
    }
    return nis;
}

    
    public void buttonTambahAction(SiswaView sv){
        try {
            SiswaView.labelStatus.setText("INSERT");
            SiswaView.textNis.setText(getNisOtomatis(sv));
            SiswaView.textNama.setText("");
            SiswaView.radioJenkel1.setSelected(true);
            SiswaView.comboKelas.setSelectedIndex(0);
            SiswaView.comboJurusan.setSelectedIndex(0);
            SiswaView.textAreaAlamat.setText("");
            SiswaView.comboCari.setSelectedIndex(0);
            SiswaView.textCari.setText("");
            SiswaView.tableSiswa.clearSelection();
            
            SiswaView.textNis.setEnabled(true);
            SiswaView.textNama.setEnabled(true);
            SiswaView.radioJenkel1.setEnabled(true);
            SiswaView.radioJenkel2.setEnabled(true);
            SiswaView.comboKelas.setEnabled(true);
            SiswaView.comboJurusan.setEnabled(true);
            SiswaView.textAreaAlamat.setEnabled(true);
            SiswaView.buttonSimpan.setEnabled(true);
            SiswaView.buttonBatal.setEnabled(true);
            
            SiswaView.comboCari.setEnabled(false);
            SiswaView.textCari.setEnabled(false);
            SiswaView.tableSiswa.setEnabled(false);
            SiswaView.buttonTambah.setEnabled(false);
            SiswaView.buttonUbah.setEnabled(false);
            SiswaView.buttonHapus.setEnabled(false);
            SiswaView.buttonSegarkan.setEnabled(false);
            SiswaView.buttonTutup.setEnabled(false);
            
            SiswaView.textNama.requestFocus();
        } catch (Exception error){
            System.err.println("Error at SiswaController-buttonTambahAction, details : "+ error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-buttonTambahAction, details : "+ error.toString());
        }
    }
    
    public void buttonUbahAction(SiswaView sv) {
        try {
            int row = SiswaView.tableSiswa.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(sv, "Silahkan Klik data yang ingin diubah");
            } else {
                SiswaView.labelStatus.setText("UPDATE");
                
                SiswaView.textNis.setEnabled(true);
                SiswaView.textNama.setEnabled(true);
                SiswaView.radioJenkel1.setEnabled(true);
                SiswaView.radioJenkel2.setEnabled(true);
                SiswaView.comboKelas.setEnabled(true);
                SiswaView.comboJurusan.setEnabled(true);
                SiswaView.textAreaAlamat.setEnabled(true);
                SiswaView.buttonSimpan.setEnabled(true);
                SiswaView.buttonBatal.setEnabled(true);

                SiswaView.comboCari.setEnabled(false);
                SiswaView.textCari.setEnabled(false);
                SiswaView.tableSiswa.setEnabled(false);
                SiswaView.buttonTambah.setEnabled(false);
                SiswaView.buttonUbah.setEnabled(false);
                SiswaView.buttonHapus.setEnabled(false);
                SiswaView.buttonSegarkan.setEnabled(false);
                SiswaView.buttonTutup.setEnabled(false);

                SiswaView.textNama.requestFocus();
            }
        } catch (Exception error) {
            System.err.println("Error at SiswaController-buttonUbahAction, details : "+ error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-buttonUbahAction, details : "+ error.toString());
        }
    }
    
    public void buttonHapusAction(SiswaView sv) {
        try {
            int row = SiswaView.tableSiswa.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(sv, "Silahkan Klik data yang ingin dihapus");
            } else {
                String nis = stm.get(row).getNis();
                
                int konfirmasi = JOptionPane.showConfirmDialog(sv, "Apakah anda yakin ingin menghapus data NIS "+nis+"?",
                        "Konfirmasi", JOptionPane.YES_NO_OPTION);
                
                if(konfirmasi == JOptionPane.YES_OPTION) {
                    PreparedStatement ps;
                    
                    String sqlDelete = "DELETE FROM tb_siswa WHERE nis = ?";
                    
                    ps = Koneksi.getConnection().prepareStatement(sqlDelete);
                    ps.setString(1, nis);
                    int isSuccess = ps.executeUpdate();
                    if (isSuccess == 0) {
                        JOptionPane.showMessageDialog(sv, "Data Gagal dihapus");
                    } else {
                        JOptionPane.showMessageDialog(sv, "Data berhasil dihapus");
                    }
                }
                refresh(sv);
            }
        } catch (HeadlessException | SQLException error) {
            System.err.println("Error at SiswaController-buttonHapusAction, details : "+ error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-buttonHapusAction, details : "+ error.toString());
        }
    }
    
    
    public void buttonSegarkanAction(SiswaView sv){
        try {
            refresh(sv);
        } catch (Exception error) {
            System.err.println("Error at SiswaController-buttonSegarkanAction, details : "+ error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-buttonSegarkanAction, details : "+ error.toString());
        }
    }
    
    public void buttonTutupAction(SiswaView sv){
        try {
            sv.dispose();
            HomeView.menuItemSiswa.setEnabled(true);
        } catch (Exception error) {
            System.err.println("Error at SiswaController-buttonTutupAction, details : "+ error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-buttonTutupAction, details : "+ error.toString());
        }
    }
    
    private boolean validasiData(SiswaView sv) {
        boolean b = true;
        try {
            if (SiswaView.textNis.getText().equals("")) {
                JOptionPane.showMessageDialog(sv, "Nis Tidak boleh kosong");
            } else if (SiswaView.textNama.getText().equals("")) {
                JOptionPane.showMessageDialog(sv, "Nama Tidak boleh kosong");
            } else if (SiswaView.textAreaAlamat.getText().equals("")) {
                JOptionPane.showMessageDialog(sv, "Alamat Tidak boleh kosong");
            } else {
                b = false;
            }
        } catch (Exception error) {
            System.err.println("Error at SiswaController-validasiData, details : "+ error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-validasiData, details : "+ error.toString());
        }
        return b;
    }
    
    public void buttonSimpanAction(SiswaView sv) {
        try {
            boolean b = validasiData(sv);
            if (b == false) {
                String jenkel = "";
                if ((SiswaView.radioJenkel1.isSelected() == true) && (SiswaView.radioJenkel2.isSelected() == false)) {
                    jenkel = "L";
                } else if ((SiswaView.radioJenkel1.isSelected() == false) && (SiswaView.radioJenkel2.isSelected() == true)) {
                    jenkel = "P";
                }
                
                Siswa s = new Siswa();
                s.setNis(SiswaView.textNis.getText());
                s.setNama(SiswaView.textNama.getText());
                s.setJenkel(jenkel);
                s.setKelas(SiswaView.comboKelas.getSelectedItem().toString());
                s.setJurusan(SiswaView.comboJurusan.getSelectedItem().toString());
                s.setAlamat(SiswaView.textAreaAlamat.getText());
                
                PreparedStatement ps;
                if (SiswaView.labelStatus.getText().equals("INSERT")) {
                    String sqlInsert = "INSERT INTO tb_siswa VALUES (?, ?, ?, ?, ?, ?)";
                    ps = Koneksi.getConnection().prepareStatement(sqlInsert);
                    ps.setString(1, s.getNis());
                    ps.setString(2, s.getNama());
                    ps.setString(3, s.getJenkel());
                    ps.setString(4, s.getKelas());
                    ps.setString(5, s.getJurusan());
                    ps.setString(6, s.getAlamat());
                    int isSuccess = ps.executeUpdate();
                    
                    if (isSuccess != 1) {
                        JOptionPane.showMessageDialog(sv, "Data Gagal disimpan", "Pesan", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(sv, "Data Berhasil disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                    }
                    refresh(sv);
                } else if (SiswaView.labelStatus.getText().equals("UPDATE")) {
                    String sqlUpdate = "UPDATE tb_siswa SET nama = ?, jenkel = ?, "
                            + "kelas = ?, jurusan = ?, alamat = ? WHERE nis = ?";
                    ps = Koneksi.getConnection().prepareStatement(sqlUpdate);
                    ps.setString(1, s.getNama());
                    ps.setString(2, s.getJenkel());
                    ps.setString(3, s.getKelas());
                    ps.setString(4, s.getJurusan());
                    ps.setString(5, s.getAlamat());
                    ps.setString(6, s.getNis());
                    int isSuccess = ps.executeUpdate();
                    
                    if (isSuccess != 1) {
                        JOptionPane.showMessageDialog(sv, "Data Gagal diUbah", "Pesan", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(sv, "Data Berhasil diUbah", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                    }
                    refresh(sv);
                }
            }
        } catch (SQLException | HeadlessException error) {
            System.err.println("Error at SiswaController-buttonSimpanAction, details : "+ error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-buttonSimpanAction, details : "+ error.toString());
        }
    }
    
     public void buttonBatalAction(SiswaView sv){
        try {
            refresh(sv);
        } catch (Exception error) {
            System.err.println("Error at SiswaController-buttonBatalAction, details : "+ error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-buttonBatalAction, details : "+ error.toString());
        }
    }
    
}
