package modelo;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import controle.RegistroHospedesControle;
import controle.ReservasControle;

public class ModeloTabelaHospedes extends AbstractTableModel {
private List<RegistraHospedes> hospedes;
private String[] columns = { "Nº Reserva", "Nome", "Sobrenome", "Data Nacimento", "Nacionalidade", "Telefone" };


public ModeloTabelaHospedes(List<RegistraHospedes> hopedes) {
	this.hospedes = hopedes;
}

	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch(coluna) {
		case 0:
			return hospedes.get(linha).getId_reserva();
		case 1:
			return hospedes.get(linha).getNome();
		case 2:
			return hospedes.get(linha).getSobrenome();
		case 3:
			return hospedes.get(linha).getDataNacimento();
		case 4:
			return hospedes.get(linha).getNacionalidade();
		case 5:
			return hospedes.get(linha).getTelefone();
		}
		 
		return hospedes;

	}

	@Override
	public int getRowCount() {
		return hospedes.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	};
	public void DeletaProduto(int linha) {
		hospedes.remove(linha);
		fireTableRowsDeleted(linha, linha);

	}
}



