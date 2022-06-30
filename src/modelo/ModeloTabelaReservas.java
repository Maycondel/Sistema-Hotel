package modelo;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModeloTabelaReservas extends AbstractTableModel {
private List<Reservas> reservas;
private String[] columns = { "Nº Reserva", "Data Entrada", "Data saida", "Forma Pagamento", "Valor" };


public ModeloTabelaReservas(List<Reservas> reservas) {
	this.reservas = reservas;
}

	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:
			return  reservas.get(linha).getId();
		case 1:
			return reservas.get(linha).getDataEntrada();
		case 2:
			return reservas.get(linha).getDataSaida();
		case 3:
			return reservas.get(linha).getFormaPagamento();
		case 4:
			return reservas.get(linha).getValor();

		}
		return reservas;
	}
	
	@Override
	public int getRowCount() {
		return reservas.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	};
	
public void DeletaProduto(int linha) {
	reservas.remove(linha);
	fireTableRowsDeleted(linha, linha);

}

}



