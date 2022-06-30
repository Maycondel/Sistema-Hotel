package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import controle.ReservasControle;
import modelo.RegistraHospedes;
import modelo.Reservas;

public class RegistraHospedesDao {
	private Connection connection;
	private LinkedList<Reservas> listaReserva = new ReservasControle().listar();
	
	
	public RegistraHospedesDao(Connection connection) {
		this.connection = connection;
	} 
	   public void atualizaIdReserva() {
		   
		   String sql = "UPDATE HOSPEDES SET IDRESERVA = ? WHERE ID = ?";
		  
		   try {
			  
			   PreparedStatement pstm = connection.prepareStatement(sql);
		       pstm.setInt(1, listaReserva.getLast().getId());
		       pstm.setInt(2, listar().getLast().getId());
			   pstm.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	   }
	public LinkedList<RegistraHospedes> listar() {
		LinkedList<RegistraHospedes>listaHospedes = new LinkedList<>();
		String sql = "SELECT * FROM HOSPEDES";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.execute();
			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					listaHospedes.add(new  RegistraHospedes(rst.getInt("id"), rst.getString("nome"),rst.getString("sobrenome"),
					rst.getString("dataNacimento"),rst.getString("nacionalidade"), rst.getString("telefone"),rst.getInt("idreserva")));

				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return listaHospedes ;
	}
	public void deletaReservaProduto(int id){
		String sql = "DELETE FROM HOSPEDES WHERE IDRESERVA = ?";
		String sql2 = "DELETE FROM RESERVAS WHERE ID = ?";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			PreparedStatement pstm2 = connection.prepareStatement(sql2,Statement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, id);
			pstm2.setInt(1, id);
			pstm.execute();
			pstm2.execute();
	}catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
	}
	public void Atualizartable(String nome,String sobrenome,String Datanacimento,String nacionalidade,String telefone, int id) {
		String sql = "UPDATE HOSPEDES H SET H.NOME = ?, H.SOBRENOME = ?, H.DATANACIMENTO = ?,"
				+ " H.NACIONALIDADE = ?,H.TELEFONE = ? WHERE IDRESERVA = ?  ";
		try {
			PreparedStatement pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, nome);
			pstm.setString(2, sobrenome);
			pstm.setString(3, Datanacimento);
			pstm.setString(4, nacionalidade);
			pstm.setString(5, telefone);
			pstm.setInt(6, id);
			
			pstm.execute();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void salvar(RegistraHospedes hospedes) {
		String sql = "INSERT INTO HOSPEDES (NOME,SOBRENOME,DATANACIMENTO,NACIONALIDADE"
				+ ",TELEFONE) VALUES(?,?,?,?,?)";
		try {
             PreparedStatement pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
             pstm.setString(1, hospedes.getNome());
             pstm.setString(2, hospedes.getSobrenome());
             pstm.setString(3, hospedes.getDataNacimento());
             pstm.setString(4, hospedes.getNacionalidade());
             pstm.setString(5, hospedes.getTelefone());

             
             
             pstm.execute();
             try(ResultSet rst = pstm.getGeneratedKeys();){
            	 while(rst.next()) {
            		 hospedes.setId(rst.getInt(1));
            		 atualizaIdReserva(); 
            	 }
             }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
