
package sekolahproject.siswa;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class SiswaTableModel extends AbstractTableModel {
    
    private List<Siswa> list = new ArrayList<>();
    
    public void insert(Siswa s) {
        list.add(s);
        fireTableDataChanged();
    }
    
    public void update(int row, Siswa s) {
        list.set(row, s);
        fireTableDataChanged();
    }
    
    public void delete(int row) {
        list.remove(row);
        fireTableDataChanged();
    }
    
    public Siswa get(int row) {
        return list.get(row);
    }
    
    public void setList(List<Siswa> list) {
        this.list = list;
       fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }
    
    @Override
    public int getColumnCount() {
        return 7;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return rowIndex + 1;
            case 1:
                return list.get(rowIndex).getNis();
            case 2:
                return list.get(rowIndex).getNama();
            case 3:
                return list.get(rowIndex).getJenkel();
            case 4:
                return list.get(rowIndex).getKelas();
            case 5:
                return list.get(rowIndex).getJurusan();
            case 6:
                return list.get(rowIndex).getAlamat();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "No";
            case 1:
                return "Nis";
            case 2:
                return "Nama";
            case 3:
                return "Jenis Kelamin";
            case 4:
                return "Kelas";
            case 5:
                return "Jurusan";
            case 6:
                return "Alamat";
            default:
                return null;
        }
    }
    
}
