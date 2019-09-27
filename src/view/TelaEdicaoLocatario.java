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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
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
import controller.LocatarioController;
import model.Endereco;
import model.Locador;
import model.Locatario;

public class TelaEdicaoLocatario extends JInternalFrame {

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

	public static void main(String[] args, Locatario locatario) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEdicaoLocatario frame = new TelaEdicaoLocatario(args, locatario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaEdicaoLocatario(String[] args, Locatario locatario) throws Exception {
		setTitle("Editar de LocatÃ¡rio");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1027, 602);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("/home/alan/eclipse-workspace/VaDeBike/icons/locatario.png"));
		lblLogo.setBounds(426, 0, 216, 168);
		contentPane.add(lblLogo);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Dados pessoais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(40, 180, 468, 316);
		contentPane.add(panel);
		panel.setLayout(null);
		
		MaskFormatter mascaraTexto = new MaskFormatter("******************************");
		mascaraTexto.setValidCharacters(" aÃ¡Ã Ã¤bcÃ§deÃ©Ã¨Ã«fghiÃ­Ã¬Ã¯jklmnoÃ³Ã²Ã¶pqrstuÃºÃ¹Ã¼vwxyzAÃ�Ã€Ã„BCÃ‡DEÃ‰ÃˆÃ‹FGHIÃ�ÃŒÃ�JKLMNOÃ’Ã“Ã–PQRSTUÃšÃ™ÃœVWXYZ");		
		
		JLabel lblNome = new JLabel("Nome *");
		lblNome.setBounds(12, 38, 66, 15);
		panel.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF *");
		lblCpf.setBounds(12, 79, 66, 15);
		panel.add(lblCpf);
		
		JLabel lblCelular = new JLabel("Celular *");
		lblCelular.setBounds(12, 120, 66, 15);
		panel.add(lblCelular);
		
		JLabel lblLogin = new JLabel("Login *");
		lblLogin.setBounds(12, 161, 66, 15);
		panel.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha  *");
		lblSenha.setBounds(12, 202, 66, 15);
		panel.add(lblSenha);
		
		txtNome = new JFormattedTextField(mascaraTexto);
		txtNome.setBounds(83, 36, 373, 19);
		txtNome.setText(locatario.getNome());
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		javax.swing.text.MaskFormatter maskCPF = null;
		try {
			maskCPF = new javax.swing.text.MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		txtCPF = new JFormattedTextField(maskCPF);
		txtCPF.setBounds(83, 77, 373, 19);
		txtCPF.setText(locatario.getCpf());
		panel.add(txtCPF);
		txtCPF.setColumns(10);
		
		javax.swing.text.MaskFormatter maskPhone = null;
		try {
			maskPhone = new javax.swing.text.MaskFormatter("(##) #####-####");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		txtCelular = new JFormattedTextField(maskPhone);
		txtCelular.setColumns(10);
		txtCelular.setText(locatario.getCelular());
		txtCelular.setBounds(83, 118, 373, 19);
		panel.add(txtCelular);
		
		txtLogin = new JFormattedTextField(mascaraTexto);
		txtLogin.setEditable(false);
		txtLogin.setColumns(10);
		txtLogin.setText(locatario.getLogin());
		txtLogin.setBounds(83, 159, 373, 19);
		panel.add(txtLogin);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(83, 200, 373, 19);
		panel.add(txtSenha);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_1.setBounds(520, 180, 468, 316);
		contentPane.add(panel_1);
		
		JLabel lblLogradouro = new JLabel("Logradouro *");
		lblLogradouro.setBounds(12, 38, 117, 15);
		panel_1.add(lblLogradouro);
		
		JLabel lblNmero = new JLabel("NÃºmero *");
		lblNmero.setBounds(12, 79, 66, 15);
		panel_1.add(lblNmero);
		
		JLabel lblBairro = new JLabel("Complemento");
		lblBairro.setBounds(12, 120, 117, 15);
		panel_1.add(lblBairro);
		
		JLabel lblBairro_1 = new JLabel("Bairro *");
		lblBairro_1.setBounds(12, 161, 66, 15);
		panel_1.add(lblBairro_1);
		
		JLabel lblCidade = new JLabel("Cidade *");
		lblCidade.setBounds(12, 202, 66, 15);
		panel_1.add(lblCidade);
		
		txtLogradouro = new JFormattedTextField(mascaraTexto);
		txtLogradouro.setColumns(10);
		txtLogradouro.setText(locatario.getEndereco().getLogradouro());
		txtLogradouro.setBounds(134, 36, 322, 19);
		panel_1.add(txtLogradouro);
		
		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setText(locatario.getEndereco().getNumero());
		txtNumero.setBounds(134, 77, 322, 19);
		panel_1.add(txtNumero);
		
		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setText(locatario.getEndereco().getComplemento());
		txtComplemento.setBounds(134, 118, 322, 19);
		panel_1.add(txtComplemento);
		
		txtBairro = new JFormattedTextField(mascaraTexto);
		txtBairro.setColumns(10);
		txtBairro.setText(locatario.getEndereco().getBairro());
		txtBairro.setBounds(134, 159, 322, 19);
		panel_1.add(txtBairro);
		
		txtCidade = new JFormattedTextField(mascaraTexto);
		txtCidade.setBounds(134, 200, 322, 19);
		txtCidade.setText(locatario.getEndereco().getCidade());
		panel_1.add(txtCidade);
		
		JLabel lblEstado = new JLabel("Estado *");
		lblEstado.setBounds(12, 243, 66, 15);
		panel_1.add(lblEstado);
		
		txtEstado = new JFormattedTextField(mascaraTexto);
		txtEstado.setBounds(134, 241, 322, 19);
		txtEstado.setText(locatario.getEndereco().getEstado());
		panel_1.add(txtEstado);
		
		JLabel lblCep = new JLabel("CEP *");
		lblCep.setBounds(12, 284, 66, 15);
		panel_1.add(lblCep);
		
		javax.swing.text.MaskFormatter maskCep = null;
		try {
			maskCep = new javax.swing.text.MaskFormatter("##.###-###");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		txtCEP = new JFormattedTextField(maskCep);
		txtCEP.setBounds(134, 284, 322, 19);
		txtCEP.setText(locatario.getEndereco().getCep());
		panel_1.add(txtCEP);
		
		JButton btnCadastrar = new JButton("Atualizar");
		btnCadastrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				Endereco end = new Endereco();
				Locatario loc = new Locatario();
				LocatarioController controller = new LocatarioController();
				
				end.setId(locatario.getEndereco().getId());
				end.setLogradouro(txtLogradouro.getText());
				end.setBairro(txtBairro.getText());
				end.setNumero(txtNumero.getText());
				end.setCep(txtCEP.getText());
				end.setComplemento(txtComplemento.getText());
				end.setCidade(txtCidade.getText());
				end.setEstado(txtEstado.getText());
				
				loc.setId(locatario.getId());
				loc.setNome(txtNome.getText());
				loc.setCpf(txtCPF.getText());
				loc.setCelular(txtCelular.getText());
				loc.setLogin(txtLogin.getText());
				loc.setSenha(txtSenha.getText());
				
				try {
					if(controller.enviaParaServiceAtualizar(loc,end)) {
						dispose();
						inicializa(args);
					} else {
						JOptionPane.showMessageDialog(null, "NÃ£o foi possÃ­vel atualizar o locatario.");
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
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNome.setText("");
				txtCPF.setText("");
				txtCelular.setText("");
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
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				inicializa(args);
			}
		});
		btnCancelar.setBounds(633, 508, 114, 25);
		contentPane.add(btnCancelar);
		
		JLabel lblCamposObrigatrios = new JLabel("* Campos obrigatÃ³rios");
		lblCamposObrigatrios.setFont(new Font("Dialog", Font.ITALIC, 10));
		lblCamposObrigatrios.setBounds(40, 514, 216, 15);
		contentPane.add(lblCamposObrigatrios);
		
		JButton btnDeletarLocatrio = new JButton("Deletar locatÃ¡rio");
		btnDeletarLocatrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showConfirmDialog(null, "AtenÃ§Ã£o seu login serÃ¡ deletado do sistema. Caso confirme, nÃ£o serÃ¡ possÃ­vel reverter posteriormente.");
				Locatario loc = new Locatario();
				Endereco end = new Endereco();
				loc.setId(locatario.getId());
				end.setId(locatario.getEndereco().getId());
				
				LocatarioController controller = new LocatarioController();
				controller.enviarParaServiceDeletar(loc,end);	
				dispose();
				inicializa(args);
				
				
				
			}
		});
		btnDeletarLocatrio.setForeground(Color.WHITE);
		btnDeletarLocatrio.setBackground(new Color(255, 51, 51));
		btnDeletarLocatrio.setBounds(179, 508, 159, 25);
		contentPane.add(btnDeletarLocatrio);
	}
	
	public void setPosition() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}

}
