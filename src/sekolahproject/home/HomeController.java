
package sekolahproject.home;

import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.JOptionPane;
import sekolahproject.siswa.SiswaView;


public class HomeController {
    
    public void setFullScreen(HomeView hv) {
        try {
            hv.setExtendedState(MAXIMIZED_BOTH);
        } catch (Exception error) {
            System.err.println("Error at HomeController-setFullScreen, details : "+ error.toString());
            JOptionPane.showMessageDialog(hv, "Error at HomeController-setFullScreen, details : "+ error.toString());
        }
    }
    
    public void menuItemKeluarAction(HomeView hv) {
        try {
            int konfirmasi = JOptionPane.showConfirmDialog(hv, "Apakah anda yakin ingin keluar?",
                    "Konfirmasi", JOptionPane.YES_NO_OPTION);
            
            if(konfirmasi == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(hv, "Terima Kasih");
                System.exit(0);
            }
        } catch (Exception error) {
            System.err.println("Error at HomeController-menuItemKeluar, details : "+ error.toString());
            JOptionPane.showMessageDialog(hv, "Error at HomeController-menuItemKeluar, details : "+ error.toString());
        }
    }
    
    public void menuItemSiswaAction(HomeView hv) {
        try {
            HomeView.menuItemSiswa.setEnabled(false);
            SiswaView sv = new SiswaView();
            HomeView.panelHome.add(sv);
            sv.setVisible(true);
        } catch (Exception error) {
            System.err.println("Error at HomeController-menuItemSiswaAction, details : "+ error.toString());
            JOptionPane.showMessageDialog(hv, "Error at HomeController-menuItemSiswaAction, details : "+ error.toString());
        }
    }
    
}
