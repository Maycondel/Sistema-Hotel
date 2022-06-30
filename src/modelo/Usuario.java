package modelo;

public class Usuario {
  private Integer id;
  private String nome;
  private String Email;
  private String Senha;
  
public Usuario(String nome, String email) {

	this.nome = nome;
	Email = email;
}
public Usuario(String nome, String email, String senha) {

	this.nome = nome;
	Email = email;
	Senha = senha;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}
public String getSenha() {
	return Senha;
}
public void setSenha(String senha) {
	Senha = senha;
}
 public Integer getId() {
	return id;
}
 public void setId(Integer id) {
	this.id = id;
}
 @Override
	public String toString() {
		return this.getNome();
	}
}
