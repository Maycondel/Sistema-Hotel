package modelo;

public class Reservas {
	private Integer id;
	private String dataEntrada;
	private String dataSaida;
	private double valor ;
	private String formaPagamento;

	public Reservas(String dataformatE, String dataformatS, Object pagamento, double valor) {
		this.dataEntrada = dataformatE;
		this.dataSaida = dataformatS;
		this.formaPagamento = (String) pagamento;
		this.valor = valor; 
		System.out.println(dataformatS + "Reservas modelo");
	}

	public Reservas() {
		// TODO Auto-generated constructor stub
	}

	public Reservas(Integer id, String dataEntrada, String dataSaida, double valor, String formaPagamento) {
		this.id = id;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

}
