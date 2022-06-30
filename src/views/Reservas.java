package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.JavaBean;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.xml.crypto.Data;

import com.toedter.calendar.JDateChooser;

import controle.ReservasControle;

@SuppressWarnings("serial")
public class Reservas extends JFrame {

	private JPanel contentPane;
	private JTextField Valor;
	private String resultado;
	ReservasControle reservas = new ReservasControle();
	private JDateChooser FechaE;
	private JDateChooser FechaS;
	private JComboBox<?> FormaPago;
	private boolean salvarEditar = true;
	private ReservasControle reservasControle = new ReservasControle();
	private int idReserva;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservas frame = new Reservas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Reservas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Reservas.class.getResource("/imagens/calendario.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 540);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(0, 0, 900, 502);
		contentPane.add(panel);
		panel.setLayout(null);

		FechaE = new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
		FechaE.setBounds(88, 166, 235, 33);
		panel.add(FechaE);

		JLabel lblNewLabel_1 = new JLabel("Data de Check In");
		lblNewLabel_1.setBounds(88, 142, 133, 14);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Data de Check Out");
		lblNewLabel_1_1.setBounds(88, 210, 133, 14);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1);

		FechaS = new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
		FechaS.setBounds(88, 234, 235, 33);

		FechaS.getCalendarButton().setBackground(Color.WHITE);
		panel.add(FechaS);

		Valor = new JTextField();
		Valor.setBounds(88, 303, 235, 33);
		Valor.setEnabled(false);
		panel.add(Valor);
		Valor.setColumns(10);
		Valor.setText(this.resultado);

		JLabel lblNewLabel_1_1_1 = new JLabel("Valor da Reserva");
		lblNewLabel_1_1_1.setBounds(88, 278, 133, 14);
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1_1);

		FormaPago = new JComboBox();
		FormaPago.setBounds(88, 373, 235, 33);
		FormaPago.setFont(new Font("Arial", Font.PLAIN, 14));
		FormaPago
				.setModel(new DefaultComboBoxModel(new String[] { "Cartão de Crédito", "Cartão de Débito", "Boleto" }));
		panel.add(FormaPago);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Forma de pagamento");
		lblNewLabel_1_1_1_1.setBounds(88, 347, 151, 24);
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_4 = new JLabel("Sistema de Reservas");
		lblNewLabel_4.setBounds(108, 93, 199, 42);
		lblNewLabel_4.setForeground(new Color(65, 105, 225));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(lblNewLabel_4);

//	     botão de Reservar 

		JButton btnReservar = new JButton("Continuar");
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(salvarEditar == true) {
				
					Salvar(FechaE.getDate(), FechaS.getDate(), FormaPago.getSelectedItem());

					}else {
						atualizarData();
					}
				
			}
		});

		FechaE.getDateEditor().addPropertyChangeListener((PropertyChangeListener) new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("date".equals(evt.getPropertyName()) && FechaS.getDate() != null) {
					
					Valor(FechaE.getDate(), FechaS.getDate());
					System.out.println(FechaS.getDate());
				
				}

			}

		});
		FechaS.getDateEditor().addPropertyChangeListener((PropertyChangeListener) new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if ("date".equals(e.getPropertyName()) && FechaE.getDate() != null) {
					System.out.println(FechaE.getDate());
					Valor(FechaE.getDate(), FechaS.getDate());

				}

			}
		});

		btnReservar.setForeground(Color.WHITE);
		btnReservar.setBounds(190, 436, 133, 33);
		btnReservar.setIcon(new ImageIcon(Reservas.class.getResource("/imagens/calendario.png")));
		btnReservar.setBackground(new Color(65, 105, 225));
		btnReservar.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(btnReservar);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(399, 0, 491, 502);

		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, -16, 500, 539);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(Reservas.class.getResource("/imagens/reservas-img-2.png")));

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Reservas.class.getResource("/imagens/Ha-100px.png")));
		lblNewLabel_2.setBounds(15, 6, 104, 107);
		panel.add(lblNewLabel_2);

	}

	public void Salvar(Date entrada, Date saida, Object pagamento) {

		if (entrada != null && saida != null && !pagamento.equals("") && !Valor.getText().equals("")) {

			DateFormat dateFormatE = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat dateFormatS = new SimpleDateFormat("dd/MM/yyyy");
	
			String DataFormatadaE = dateFormatS.format(entrada);
			String DataFormatadaF = dateFormatE.format(saida);
			System.out.println(DataFormatadaE + "na reservas visualizaçao");
			Valor(entrada, saida);
			String numero = this.Valor.getText();
			double valor = Double.parseDouble(numero.substring(3));
			System.out.println(valor);
			this.reservas.Salvar(new modelo.Reservas(DataFormatadaE, DataFormatadaF, pagamento, valor));
			
			RegistroHospede hospede = new RegistroHospede();
			hospede.setVisible(true);
			dispose();
		} else {

			JOptionPane.showMessageDialog(this, "Todos os Campos deve ser Preenchidos");

		}

	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public String getFechaE() {
		SimpleDateFormat fomato = new SimpleDateFormat("dd/MM/yyyy");
		String data = fomato.format(FechaE.getDate());
		return  data;
	}

	public void setFechaE(JDateChooser fechaE) {
		FechaE = fechaE;
	}

	public String getFechaS() {
		SimpleDateFormat fomato = new SimpleDateFormat("dd/MM/yyyy");
		String data = fomato.format(FechaS.getDate());
		return  data;
	}

	public void setFechaS(JDateChooser fechaS) {
		FechaS = fechaS;
	}

	public String getFormaPago() {
		String Formapagamento = String.valueOf(FormaPago.getSelectedItem());
		return Formapagamento;
	}

	public void setFormaPago(JComboBox<?> formaPago) {
		FormaPago = formaPago;
	}

	public void setValor(JTextField valor) {
		Valor = valor;
	}

	public void atualizarData() {
		
		this.reservasControle.editaCamposReservas(getFechaE(), getFechaS(), getFormaPago(), getValor(),getIdReserva());
		JOptionPane.showMessageDialog(null,"Campos alterados com sucesso");
		Buscar frame = new Buscar();
		frame.setVisible(true);
	
	}

	private void Valor(Date entrada, Date saida) {
		LocalDate pdata = entrada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate sdata = saida.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Period periodo = Period.between(pdata, sdata);
		Double Resultado = (double) periodo.getDays() * 50;

		if (Resultado < 0.00) {
			this.Valor.setText("");
		} else {
			this.Valor.setText("RS:" + Resultado);
			System.out.println(Valor.getText() + " reservar viw");
		}

	}
    

	public void setSalvarEditar(boolean salvarEditar) {
		this.salvarEditar = salvarEditar;
	}

	public double getValor() {
		double valorConverte = Double.valueOf(Valor.getText().substring(3));
		return valorConverte;
	}

	public void setValor(Double valor) {
		Valor.setText("RS:" + valor);
	}

	public void setFechaE(Date fechaE) {
		FechaE.setDate(fechaE);
	}

	public void setFechaS(Date fechaS) {

		FechaS.setDate(fechaS);
	}

	public void setFormaPago(String formaPago) {
		FormaPago.setSelectedItem(formaPago);
	}

	public JPanel getContentPane() {
		return contentPane;
	}

}
