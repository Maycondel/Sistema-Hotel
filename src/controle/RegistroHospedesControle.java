package controle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ConnexaoDeFabriaca.ConnecaoFabrica;
import Dao.RegistraHospedesDao;
import modelo.RegistraHospedes;

public class RegistroHospedesControle {
	private RegistraHospedesDao hospedesDao;
	Connection connection;

	public RegistroHospedesControle() {
		try {
			connection = new ConnecaoFabrica().getDadosConnexao();
			this.hospedesDao = new RegistraHospedesDao(connection);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void salvar(RegistraHospedes hopedes) {
		this.hospedesDao.salvar(hopedes);
	}

	public List<RegistraHospedes> listar() {
		return this.hospedesDao.listar();
	}
	public void deleteProdutohopedes(int idreserva) {
		this.hospedesDao.deletaReservaProduto(idreserva);
	}
	public void atualizaTabela() {
		this.hospedesDao.atualizaIdReserva();
	}
	public void atualizarHopedes(String nome,String sobrenome,String Datanacimento,String nacionalidade,String telefone,int id) {
		this.hospedesDao.Atualizartable(nome, sobrenome, Datanacimento, nacionalidade, telefone,id);
	}
}
