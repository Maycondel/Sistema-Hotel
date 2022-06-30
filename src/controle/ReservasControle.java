package controle;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import ConnexaoDeFabriaca.ConnecaoFabrica;
import Dao.RegistraHospedesDao;
import Dao.ReservasDao;
import modelo.Reservas;

public class ReservasControle {
	
	private ReservasDao reservasDao;
	private RegistraHospedesDao hospedesDao;
	
	public ReservasControle()  {
		try {
			 Connection connection = new ConnecaoFabrica().getDadosConnexao(); 
			  this.reservasDao = new ReservasDao(connection);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	 
	}

	public void Salvar(Reservas reservas) {
		this.reservasDao.salvarReservar(reservas);
	} 
	public LinkedList<Reservas> listar(){
		return this.reservasDao.listar();
	}
	public void buscaReserva(int idreserva) {
		this.reservasDao.buscarReservas(idreserva);
	}
	public Reservas getReserva() {
		return this.reservasDao.pegarReservar();
	}
	
	public void editaCamposReservas(String dataentrada, String dataSaida, String formapagamento, double valor, int id) {
		this.reservasDao.editarcamposReservas(dataentrada, dataSaida, formapagamento, valor, id);	
		}
	public void deletaHospedes(int idreserva) {
	   this.reservasDao.deletaReservas(idreserva);
	}

}
