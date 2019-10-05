package view;

import static controller.AppController.inicializa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import controller.LocadorController;
import model.Endereco;
import model.Locador;
import service.UtilsService;

@SuppressWarnings("serial")
public class TelaCadastroLocador extends JFrame {

	private Properties prop = UtilsService.getProp();
	private JPanel contentPane;
	private JFormattedTextField txtNome;
	private JFormattedTextField txtCPF;
	private JFormattedTextField txtCelular;
	private JFormattedTextField txtLogin;
	private JPasswordField txtSenha;
	private JFormattedTextField txtLogradouro;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JFormattedTextField txtBairro;
	private JFormattedTextField txtCidade;
	private JFormattedTextField txtEstado;
	private JFormattedTextField txtCEP;
	
	private static final String VALIDCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzçÇ áÁàÀÉéÈèÍíÌìÓóÒòÚúÙù";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroLocador frame = new TelaCadastroLocador(args);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCadastroLocador(String[] args) throws Exception {
		setTitle(prop.getProperty("CadLocadorView.Title"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1027, 602);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaCadastroLocador.class.getResource("/images/icons/locador.png")));
		lblLogo.setBounds(426, 0, 216, 168);
		contentPane.add(lblLogo);
		
		JPanel panelDadosPessoais = new JPanel();
		panelDadosPessoais.setBorder(new TitledBorder(null, prop.getProperty("CadLocadorView.BorderTitle.DadosPessoais"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDadosPessoais.setBounds(40, 180, 468, 316);
		contentPane.add(panelDadosPessoais);
		panelDadosPessoais.setLayout(null);
		
		MaskFormatter mascaraTexto = new MaskFormatter("******************************");
		mascaraTexto.setValidCharacters(VALIDCHARS);		
		
		JLabel lblNome = new JLabel(prop.getProperty("CadLocadorView.Label.Nome"));
		lblNome.setBounds(12, 38, 66, 15);
		panelDadosPessoais.add(lblNome);
		
		JLabel lblCpf = new JLabel(prop.getProperty("CadLocadorView.Label.CPF"));
		lblCpf.setBounds(12, 79, 66, 15);
		panelDadosPessoais.add(lblCpf);
		
		JLabel lblCelular = new JLabel(prop.getProperty("CadLocadorView.Label.Celular"));
		lblCelular.setBounds(12, 120, 66, 15);
		panelDadosPessoais.add(lblCelular);
		
		JLabel lblLogin = new JLabel(prop.getProperty("CadLocadorView.Label.Login"));
		lblLogin.setBounds(12, 161, 66, 15);
		panelDadosPessoais.add(lblLogin);
		
		JLabel lblSenha = new JLabel(prop.getProperty("CadLocadorView.Label.Senha"));
		lblSenha.setBounds(12, 202, 66, 15);
		panelDadosPessoais.add(lblSenha);
		
		txtNome = new JFormattedTextField(mascaraTexto);
		txtNome.setBounds(83, 36, 373, 19);
		panelDadosPessoais.add(txtNome);
		txtNome.setColumns(10);
		
		javax.swing.text.MaskFormatter maskCPF = null;
		try {
			maskCPF = new javax.swing.text.MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		txtCPF = new JFormattedTextField(maskCPF);
		txtCPF.setBounds(83, 77, 373, 19);
		panelDadosPessoais.add(txtCPF);
		txtCPF.setColumns(10);
		
		javax.swing.text.MaskFormatter maskPhone = null;
		try {
			maskPhone = new javax.swing.text.MaskFormatter("(##) #####-####");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		txtCelular = new JFormattedTextField(maskPhone);
		txtCelular.setColumns(10);
		txtCelular.setBounds(83, 118, 373, 19);
		panelDadosPessoais.add(txtCelular);
		
		txtLogin = new JFormattedTextField(mascaraTexto);
		txtLogin.setColumns(10);
		txtLogin.setBounds(83, 159, 373, 19);
		panelDadosPessoais.add(txtLogin);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(83, 200, 373, 19);
		panelDadosPessoais.add(txtSenha);
		
		JPanel panelEndereco = new JPanel();
		panelEndereco.setLayout(null);
		panelEndereco.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), prop.getProperty("CadLocadorView.BorderTitle.Endereco"), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelEndereco.setBounds(520, 180, 468, 316);
		contentPane.add(panelEndereco);
		
		JLabel lblLogradouro = new JLabel(prop.getProperty("CadLocadorView.Label.Logradouro"));
		lblLogradouro.setBounds(12, 38, 117, 15);
		panelEndereco.add(lblLogradouro);
		
		JLabel lblNmero = new JLabel(prop.getProperty("CadLocadorView.Label.Numero"));
		lblNmero.setBounds(12, 79, 66, 15);
		panelEndereco.add(lblNmero);
		
		JLabel lblComplemento = new JLabel(prop.getProperty("CadLocadorView.Label.Complemento"));
		lblComplemento.setBounds(12, 120, 117, 15);
		panelEndereco.add(lblComplemento);
		
		JLabel lblBairro = new JLabel(prop.getProperty("CadLocadorView.Label.Bairro"));
		lblBairro.setBounds(12, 161, 66, 15);
		panelEndereco.add(lblBairro);
		
		JLabel lblCidade = new JLabel(prop.getProperty("CadLocadorView.Label.Cidade"));
		lblCidade.setBounds(12, 202, 66, 15);
		panelEndereco.add(lblCidade);
		
		txtLogradouro = new JFormattedTextField(mascaraTexto);
		txtLogradouro.setColumns(10);
		txtLogradouro.setBounds(134, 36, 322, 19);
		panelEndereco.add(txtLogradouro);
		
		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(134, 77, 322, 19);
		panelEndereco.add(txtNumero);
		
		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(134, 118, 322, 19);
		panelEndereco.add(txtComplemento);
		
		txtBairro = new JFormattedTextField(mascaraTexto);
		txtBairro.setColumns(10);
		txtBairro.setBounds(134, 159, 322, 19);
		panelEndereco.add(txtBairro);
		
		txtCidade = new JFormattedTextField(mascaraTexto);
		txtCidade.setBounds(134, 200, 322, 19);
		panelEndereco.add(txtCidade);
		
		JLabel lblEstado = new JLabel(prop.getProperty("CadLocadorView.Label.Estado"));
		lblEstado.setBounds(12, 243, 66, 15);
		panelEndereco.add(lblEstado);
		
		txtEstado = new JFormattedTextField(mascaraTexto);
		txtEstado.setBounds(134, 241, 322, 19);
		panelEndereco.add(txtEstado);
		
		JLabel lblCep = new JLabel(prop.getProperty("CadLocadorView.Label.CEP"));
		lblCep.setBounds(12, 284, 66, 15);
		panelEndereco.add(lblCep);
		
		javax.swing.text.MaskFormatter maskCep = null;
		try {
			maskCep = new javax.swing.text.MaskFormatter("##.###-###");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		txtCEP = new JFormattedTextField(maskCep);
		txtCEP.setBounds(134, 284, 322, 19);
		panelEndereco.add(txtCEP);
		
		JButton btnCadastrar = new JButton(prop.getProperty("CadLocadorView.Button.Cadastrar"));
		btnCadastrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				Endereco end = new Endereco();
				Locador loc = new Locador();
				LocadorController controller = new LocadorController();
				
				end.setLogradouro(txtLogradouro.getText());
				end.setBairro(txtBairro.getText());
				end.setNumero(txtNumero.getText());
				end.setCep(txtCEP.getText());
				end.setComplemento(txtComplemento.getText());
				end.setCidade(txtCidade.getText());
				end.setEstado(txtEstado.getText());
				
				loc.setNome(txtNome.getText());
				loc.setCpf(txtCPF.getText());
				loc.setCelular(txtCelular.getText());
				loc.setLogin(txtLogin.getText());
				loc.setSenha(txtSenha.getText());
				
				try {
					if(controller.enviaParaService(loc,end)) {
						dispose();
						inicializa(args);
					} else {
						JOptionPane.showMessageDialog(null, prop.getProperty("CadLocadorView.Message.CadastroFail"));
					}
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnCadastrar.setBounds(874, 508, 114, 25);
		contentPane.add(btnCadastrar);
		
		JButton btnLimpar = new JButton(prop.getProperty("CadLocadorView.Button.Limpar"));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNome.setText("");
				txtCPF.setText("");
				txtCelular.setText("");
				txtLogin.setText("");
				txtSenha.setText("");
				txtLogradouro.setText("");
				txtNumero.setText("");
				txtComplemento.setText("");
				txtBairro.setText("");
				txtCidade.setText("");
				txtEstado.setText("");
			}
		});
		btnLimpar.setBounds(752, 508, 114, 25);
		contentPane.add(btnLimpar);
		
		JButton btnCancelar = new JButton(prop.getProperty("CadLocadorView.Button.Cancelar"));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				inicializa(args);
			}
		});
		btnCancelar.setBounds(633, 508, 114, 25);
		contentPane.add(btnCancelar);
		
		JLabel lblCamposObrigatrios = new JLabel(prop.getProperty("CadLocadorView.Label.CamposObrigatorios"));
		lblCamposObrigatrios.setFont(new Font("Dialog", Font.ITALIC, 10));
		lblCamposObrigatrios.setBounds(40, 514, 216, 15);
		contentPane.add(lblCamposObrigatrios);
	}
	
}
