package modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistraHospedes {
	private int id;
	private	String nome;
	private	String sobrenome;
	private	String dataNacimento;
	private	String  Nacionalidade;
	private	String telefone;
	private	Integer id_reserva;
	
	
	
	
	public RegistraHospedes(int id, String nome,String sobrenome, String dataNacimento,
			String nacionalidade, String telefone, int id_reserva) {
		
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNacimento = dataNacimento;
		this.Nacionalidade = nacionalidade;
		this.telefone = telefone;
		this.id_reserva = id_reserva;
		
	}
	public RegistraHospedes(String nome,String sobrenome, Date dataNacimento,
			Object nacionalidade, String telefone, String id_reserva) {
		
	
		this.nome = nome; 
		this.sobrenome = sobrenome;
		this.dataNacimento = new SimpleDateFormat("ddMMyyyy").format(dataNacimento);
		this.Nacionalidade = (String)nacionalidade;
		this.telefone = telefone;
		this.id_reserva = Integer.getInteger(id_reserva);
		 
	}


	public RegistraHospedes() {
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setId_reserva(Integer id_reserva) {
		this.id_reserva = id_reserva;
	}

	
	
	public RegistraHospedes(String nome, String sobrenome) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
	}



	public RegistraHospedes(String nome, String sobrenome, String telefone , Date date, Object combo) {
	DateFormat dataFormatada =  new SimpleDateFormat("ddmmyyyy");
	this.nome = nome;
	this.sobrenome = sobrenome;
	this.telefone = telefone;
	this.dataNacimento = dataFormatada.format(date);
	this.Nacionalidade = (String) combo;
		
	}



	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getDataNacimento() {
		return this.dataNacimento;
	}
	public void setDataNacimento(String dataNacimento) {
		this.dataNacimento = dataNacimento;
	}
	public String getNacionalidade() {
		return Nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		Nacionalidade = nacionalidade;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Integer getId_reserva() {
		return id_reserva;
	}

	

	

}
