package views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.UsuariosController;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtusuario;
	private JPasswordField txtSenha;
	private UsuariosController acesso = new UsuariosController();
	public String SenhaPassada = "";
	private JFrame jframe = new JFrame();

	/** 
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imagens/perfil-do-usuario.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/imagens/hotel.png")));
		lblNewLabel.setBounds(-53, 0, 422, 499);
		contentPane.add(lblNewLabel);

		txtusuario = new JTextField();
		txtusuario.setColumns(10);
		txtusuario.setBounds(409, 181, 234, 33);
		contentPane.add(txtusuario);

		JLabel lblNewLabel_1_1_1 = new JLabel("Usuario");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(409, 156, 57, 14);
		contentPane.add(lblNewLabel_1_1_1);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(409, 261, 234, 33);
		txtSenha.setEchoChar('º');
		contentPane.add(txtSenha);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Senha");
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(409, 236, 133, 14);
		contentPane.add(lblNewLabel_1_1_1_1);

		JButton btnLogin = new JButton("Login");

		btnLogin.setIcon(new ImageIcon(Login.class.getResource("/imagens/perfil-do-usuario.png")));

		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				SenhaPassada = new String(txtSenha.getPassword());

				if (!txtusuario.getText().equals("") && !SenhaPassada.equals("")) {
					verificarLogin(txtusuario.getText(), SenhaPassada);
				} else {
					JOptionPane.showMessageDialog(jframe, "Precisa prencher todos os campos");
				}
			}
		});
		btnLogin.setBounds(409, 322, 109, 33);
		contentPane.add(btnLogin);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(Login.class.getResource("/imagens/cerrar-24px.png")));
		btnCancelar.setBounds(528, 322, 115, 33);
		contentPane.add(btnCancelar);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/imagens/Ha-100px.png")));
		lblNewLabel_1.setBounds(470, 30, 103, 94);
		contentPane.add(lblNewLabel_1);

	}
 
	private void verificarLogin(String textoUsuario, String senhaPassada2) {
     if(acesso.AcessoLoginControle(textoUsuario, senhaPassada2)) {
			MenuUsuario usuario = new MenuUsuario();
			usuario.setVisible(true);
			dispose();
     }else {
    	 JOptionPane.showMessageDialog(this, "Usuario ou senha Errada");
     }

	}
}
