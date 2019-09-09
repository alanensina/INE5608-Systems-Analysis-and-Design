package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class TelaCadastroLocatario extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroLocatario frame = new TelaCadastroLocatario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCadastroLocatario() {
		setTitle("Cadastro de Locat�rio");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 961, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogovdebike = new JLabel("LogoV\u00E1DeBike");
		lblLogovdebike.setBounds(424, 48, 79, 14);
		contentPane.add(lblLogovdebike);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Dados da pessoais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(28, 111, 419, 289);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(20, 45, 27, 14);
		panel.add(lblNome);
		
		textField = new JTextField();
		textField.setBounds(99, 42, 310, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(20, 82, 46, 14);
		panel.add(lblCpf);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(99, 79, 310, 20);
		panel.add(textField_1);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(20, 121, 46, 14);
		panel.add(lblTelefone);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(99, 118, 310, 20);
		panel.add(textField_2);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(20, 164, 46, 14);
		panel.add(lblLogin);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(99, 161, 310, 20);
		panel.add(textField_3);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(20, 206, 46, 14);
		panel.add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(99, 206, 310, 20);
		panel.add(passwordField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(470, 111, 419, 289);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setBounds(10, 47, 69, 14);
		panel_1.add(lblLogradouro);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(89, 44, 310, 20);
		panel_1.add(textField_4);
		
		JLabel lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setBounds(10, 84, 46, 14);
		panel_1.add(lblNmero);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(89, 81, 310, 20);
		panel_1.add(textField_5);
		
		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(10, 123, 69, 14);
		panel_1.add(lblComplemento);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(89, 120, 310, 20);
		panel_1.add(textField_6);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(10, 166, 46, 14);
		panel_1.add(lblBairro);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(89, 163, 310, 20);
		panel_1.add(textField_7);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(10, 208, 46, 14);
		panel_1.add(lblCidade);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(10, 246, 46, 14);
		panel_1.add(lblEstado);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(89, 205, 310, 20);
		panel_1.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(89, 243, 310, 20);
		panel_1.add(textField_9);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(800, 411, 89, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(685, 411, 89, 23);
		contentPane.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(573, 411, 89, 23);
		contentPane.add(btnCancelar);
	}
}
