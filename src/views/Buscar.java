package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controle.RegistroHospedesControle;
import controle.ReservasControle;
import modelo.ModeloTabelaHospedes;
import modelo.ModeloTabelaReservas;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes, tbReservas;
	private JScrollPane scrollH, ScrollR;
	private ReservasControle reservasControle = new ReservasControle();
	private RegistroHospedesControle registro = new RegistroHospedesControle();

	ModeloTabelaHospedes modeloH = new ModeloTabelaHospedes(registro.listar());
	ModeloTabelaReservas modeloR = new ModeloTabelaReservas(reservasControle.listar());

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
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
	public Buscar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagens/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(647, 85, 158, 31);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JButton btnBuscar = new JButton("");

		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setIcon(new ImageIcon(Buscar.class.getResource("/imagens/lupa2.png")));
		btnBuscar.setBounds(815, 75, 54, 41);

		contentPane.add(btnBuscar);

		JButton btnEditar = new JButton("");

		btnEditar.setIcon(new ImageIcon(Buscar.class.getResource("/imagens/editar-texto.png")));
		btnEditar.setBackground(SystemColor.menu);
		btnEditar.setBounds(587, 416, 54, 41);
		contentPane.add(btnEditar);

		JLabel lblNewLabel_4 = new JLabel("Sistema de Busca");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_4.setBounds(155, 42, 212, 42);
		contentPane.add(lblNewLabel_4);

		JButton btnSair = new JButton("");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
		});
		btnSair.setIcon(new ImageIcon(Buscar.class.getResource("/imagens/encerrar-sessao-32-px.png")));
		btnSair.setForeground(Color.WHITE);
		btnSair.setBackground(Color.WHITE);
		btnSair.setBounds(815, 416, 54, 41);
		contentPane.add(btnSair);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBounds(10, 127, 874, 265);
		contentPane.add(panel);

		tbHospedes = new JTable(modeloH);
		tbHospedes.setFont(new Font("Arial", Font.PLAIN, 14));
		scrollH = new JScrollPane(tbHospedes);

		final TableRowSorter<TableModel> filtroh = new TableRowSorter<TableModel>(modeloH);
		tbHospedes.setRowSorter(filtroh);
		panel.addTab("Hóspedes", new ImageIcon(Buscar.class.getResource("/imagens/pessoa.png")), scrollH, null);

		tbReservas = new JTable(modeloR);
		tbReservas.setFont(new Font("Arial", Font.PLAIN, 14));
		tbReservas.setBounds(100, 200, 400, 400);

		ScrollR = new JScrollPane(tbReservas);

		final TableRowSorter<TableModel> filtror = new TableRowSorter<TableModel>(modeloR);
		tbReservas.setRowSorter(filtror);
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagens/calendario.png")), ScrollR, null);

		JButton btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon(Buscar.class.getResource("/imagens/deletar.png")));
		btnDelete.setBackground(SystemColor.menu);
		btnDelete.setBounds(651, 416, 54, 41);
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object objetoDaLinhaH = (Object) modeloH.getValueAt(tbHospedes.getSelectedRow(),
						tbHospedes.getSelectedColumn());
				Object objetoDaLinhaR = (Object) modeloR.getValueAt(tbReservas.getSelectedRow(),
						tbReservas.getSelectedColumn());
				if (panel.getSelectedIndex() == 0) {
					if (objetoDaLinhaH instanceof Integer) {
						int idreserva = (int) objetoDaLinhaH;
						registro.deleteProdutohopedes(idreserva);
						modeloH.DeletaProduto(tbHospedes.getSelectedRow());
						JOptionPane.showMessageDialog(null, "Deletado com Sucesso");
					} else {
						JOptionPane.showMessageDialog(null, "Selecione Id para efetuar a remoção");
					}
				} else {
					if (objetoDaLinhaR instanceof Integer) {
						int idreserva = (int) objetoDaLinhaR;
						modeloR.DeletaProduto(tbReservas.getSelectedRow());
						reservasControle.deletaHospedes(idreserva);
						
						
						JOptionPane.showMessageDialog(null, "Deletado com Sucesso");
					} else {
						JOptionPane.showMessageDialog(null, "Selecione Id para efetuar a remoção");
					}
				}
			}

		});
		contentPane.add(btnDelete);

		JButton btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(Buscar.class.getResource("/imagens/cancelar.png")));
		btnCancelar.setBackground(SystemColor.menu);
		btnCancelar.setBounds(713, 416, 54, 41);
		contentPane.add(btnCancelar);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagens/Ha-100px.png")));
		lblNewLabel_2.setBounds(25, 10, 104, 107);
		contentPane.add(lblNewLabel_2);
		setResizable(false);
		btnEditar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					if (panel.getSelectedIndex() == 0) {
						System.out.println("painel 1");
						alteraHospedes();

					} else {
						System.out.println("painel 2");
						alterar();
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String texto = txtBuscar.getText();
				if (panel.getSelectedIndex() == 0) {

					if (texto.length() == 0) {
						filtroh.setRowFilter(null);
					} else {
						filtroh.setRowFilter(RowFilter.regexFilter(texto));
					}

				} else {
					if (texto.length() == 0) {
						filtror.setRowFilter(null);
					} else {
						filtror.setRowFilter(RowFilter.regexFilter(texto));
					}
				}
			}
		});
		txtBuscar.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				String texto = txtBuscar.getText();
				String chave = String.valueOf(e.getKeyChar());
				if(panel.getSelectedIndex() == 0) {
					if (texto.length() == 0) {
						filtroh.setRowFilter(null);
					} else {
						filtroh.setRowFilter(RowFilter.regexFilter(chave));
					}
				}else{
				if (texto.length() == 0) {
					filtror.setRowFilter(null);
				} else {
					filtror.setRowFilter(RowFilter.regexFilter(chave));
				}
				}

			}
		});
	}

	private void alterar() throws ParseException {
		Object objetoDaLinha = (Object) modeloR.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn());
		if (objetoDaLinha instanceof Object) {
			Integer id = (Integer) objetoDaLinha;

			String dataentrada = (String) modeloR.getValueAt(tbReservas.getSelectedRow(), 1);
			String datasaida = (String) modeloR.getValueAt(tbReservas.getSelectedRow(), 2);
			String formaPagamento = (String) modeloR.getValueAt(tbReservas.getSelectedRow(), 3);
			Double Valor = Double.parseDouble(String.valueOf(modeloR.getValueAt(tbReservas.getSelectedRow(), 4)));
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date datae = formato.parse(dataentrada);
			java.util.Date datas = formato.parse(datasaida);

			Reservas frame = new Reservas();
			frame.setFechaE(datae);
			frame.setFechaS(datas);
			frame.setFormaPago(formaPagamento);
			frame.setValor(Valor);
			frame.setSalvarEditar(false);
			frame.setIdReserva(id);
			frame.setVisible(true);

		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}

	private void alteraHospedes() throws ParseException {
		Object objetoDaLinha = (Object) modeloH.getValueAt(tbHospedes.getSelectedRow(), tbHospedes.getSelectedColumn());
		if (objetoDaLinha instanceof Integer) {
			Integer id = (Integer) objetoDaLinha;
			String nome = (String) modeloH.getValueAt(tbHospedes.getSelectedRow(), 1);
			String Sobrenome = (String) modeloH.getValueAt(tbHospedes.getSelectedRow(), 2);
			String dataNacimento = (String) modeloH.getValueAt(tbHospedes.getSelectedRow(), 3);
			String Nacionalidade = (String) modeloH.getValueAt(tbHospedes.getSelectedRow(), 4);
			String Telefone = (String) modeloH.getValueAt(tbHospedes.getSelectedRow(), 5);
			SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");

			java.util.Date dataN = formato.parse(dataNacimento);

			RegistroHospede frame = new RegistroHospede();
			frame.setTxtNome(nome);
			frame.setTxtSobrenome(Sobrenome);
			frame.setTxtTelefone(Telefone);
			frame.setComboNacionalidade(Nacionalidade);
			frame.setTxtDataN(dataN);
			frame.setTxtNreserva(id);
			frame.setSalvarEdicao(false);
			frame.setVisible(true);

		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}

	public void buscarReservar(int id) {
		this.reservasControle.buscaReserva(id);

	}

}
