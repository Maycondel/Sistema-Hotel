package controle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ConnexaoDeFabriaca.ConnecaoFabrica;
import Dao.UsuarioDao;
import modelo.Usuario;

public class UsuariosController {
private UsuarioDao usuario;
public static void main(String[] args) {
  UsuariosController controle = new UsuariosController();
  System.out.println(controle.AcessoLoginControle("maicon", "Maicon21546"));

}
public UsuariosController() {
	try {
	 Connection connection = new ConnecaoFabrica().getDadosConnexao();
	 this.usuario = new UsuarioDao(connection);
	}catch(SQLException e) {
		throw new RuntimeException(e);
	}
}
public List<Usuario>listar() {
     return this.usuario.listar();
}
public boolean AcessoLoginControle(String name , String senha) {
  return this.usuario.AcessoLoginDao(name,senha);
}
}
