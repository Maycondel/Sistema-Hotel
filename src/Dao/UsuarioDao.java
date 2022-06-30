package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ConnexaoDeFabriaca.ConnecaoFabrica;
import modelo.Usuario;

public class UsuarioDao {
	
	private List<Usuario> ListaUsuario = new ArrayList<>();
    public static void main(String[] args) {
    	UsuarioDao usuario ;
    	try {
    		 Connection connection = new ConnecaoFabrica().getDadosConnexao();
    		 usuario = new UsuarioDao(connection);
//    	for ( int i = 0 ; i < usuario.listar().size(); i++) {
//			 
//			 if("joao".equals(usuario.listar().get(i).getNome())) {
//				 System.out.println("acesso liberado");
//				 break;
//			 }else {
//				 System.out.println("acesso negado");
//			 }
//		}
    		 usuario.listar().forEach(u -> {
    			 System.out.println(u);
//    			 
//    			 if("joao".equals(u.getNome())) {
//    				 System.out.println("acesso liberado");
//    			 }else {
//    				 System.out.println("acesso negado");
//    			 }
    			 });
    		 
    		}catch(SQLException e) {
    			throw new RuntimeException(e);
    		}
	}
	private Connection connection ;
		public UsuarioDao(Connection connection) throws SQLException {
			this.connection = connection ;
		}
		public List<Usuario> listar() {
	    LinkedList<Usuario> usuarios = new LinkedList<>();
		String sql = "SELECT * FROM USUARIOS";
		try {
		 PreparedStatement pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		 pstm.execute();
		 try(ResultSet rst = pstm.getResultSet()){
			 while(rst.next()) {
				 usuarios.add(new Usuario(rst.getString("nome"),rst.getString("email"), rst.getString("Senha")));
			 }
		 }
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return usuarios;
		}
		
	public void salvar(Usuario usuario) {

		try {
		String sql = "INSERT INTO USUARIOS (NOME, EMAIL,SENHA) VALUES (?, ?, ?)";
		try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstm.setString(1, usuario.getNome());
			pstm.setString(2, usuario.getEmail());
			pstm.setString(3, usuario.getSenha());
			

			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					usuario.setId(rst.getInt(1));
				
				}
			}
		}
		}catch (SQLException e) {
			throw new RuntimeException();
		}
		
	}
	public boolean AcessoLoginDao(String name, String senha) {
	   String sql = "SELECT * FROM USUARIOS WHERE NOME = ? AND SENHA = ?";
	   boolean verifica = false;
	   try {
		   PreparedStatement pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		   pstm.setString(1, name);
		   pstm.setString(2, senha);
		   try(ResultSet rst = pstm.executeQuery()){
			   if(rst.next()) {
				   verifica = true;
				   }
		   }
	   }catch (SQLException e) {
		throw new RuntimeException(e);
	}
	return verifica;
		
	}


}
