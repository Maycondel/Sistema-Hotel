package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import modelo.RegistraHospedes;
import modelo.Reservas;

public class ReservasDao {
	private Connection connection;
	private Reservas reservas = new Reservas();

	public ReservasDao() {
	}

	public ReservasDao(Connection connection) {
		this.connection = connection;
	}

	public void salvarReservar(Reservas reserva) {
		try {
			String sql = "INSERT INTO RESERVAS (DATAENTRADA,DATASAIDA,VALOR,FORMAPAGAMENTO)" + "VALUES (?,?,?,?)";
			PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			pstm.setString(1, reserva.getDataEntrada());
			pstm.setString(2, reserva.getDataSaida());
			pstm.setDouble(3, reserva.getValor());
			pstm.setString(4, reserva.getFormaPagamento());
			System.out.println(reserva.getValor() + "Reserva dao");
			pstm.execute();
			try (ResultSet rst = pstm.getGeneratedKeys()) {

				while (rst.next()) {
					reserva.setId(rst.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void buscarReservas(int idreserva) {

		String sql = "SELECT * FROM RESERVAS WHERE ID = ? ";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, idreserva);
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				if (rst.next()) {
					reservas.setId(rst.getInt("id"));
					reservas.setDataEntrada(rst.getString("dataentrada"));
					reservas.setDataSaida(rst.getString("datasaida"));
					reservas.setFormaPagamento(rst.getString("formapagamento"));
					reservas.setValor(rst.getDouble("valor"));

				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public Reservas pegarReservar() {

		return this.reservas;

	}

	public LinkedList<Reservas> listar() {
		LinkedList<Reservas> listaReservas = new LinkedList<Reservas>();
		String sql = "SELECT * FROM RESERVAS";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.execute();
			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {

					listaReservas.add(new Reservas(rst.getInt("ID"), rst.getString("Dataentrada"),
							rst.getString("DataSaida"), rst.getDouble("Valor"), rst.getString("formapagamento")));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return listaReservas;
	}

	public void editarcamposReservas(String dataentrada, String dataSaida, String formapagamento, double valor,int idreserva) {
		String sql = " UPDATE RESERVAS R SET R.DATAENTRADA = ?, R.DATASAIDA = ?, R.FORMAPAGAMENTO = ?, VALOR = ? WHERE ID = ? ";

		try {
			PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, dataentrada);
			pstm.setString(2, dataSaida);
			pstm.setString(3, formapagamento);
			pstm.setDouble(4, valor);
			pstm.setInt(5, idreserva);
			pstm.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deletaReservas(int idreserva) {
	   String sql = "DELETE FROM RESERVAS WHERE ID = ?";
	   String sql2 = "DELETE FROM HOSPEDES WHERE IDRESERVA = ?";
	   try {
		PreparedStatement pstm2 = connection.prepareStatement(sql2);
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm2.setInt(1, idreserva);
		pstm.setInt(1, idreserva);
		pstm2.execute();
		pstm.execute();
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
		
	}

}
