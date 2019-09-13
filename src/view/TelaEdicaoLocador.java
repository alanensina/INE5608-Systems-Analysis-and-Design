package view;

import static controller.AppController.inicializa;

import java.awt.Color;
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
import model.Endereco;
import model.Locador;

public class TelaEdicaoLocador extends JInternalFrame {

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

	public static void main(String[] args, Locador locador) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEdicaoLocador frame = new TelaEdicaoLocador(args, locador);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaEdicaoLocador(String[] args, Locador locador) throws Exception {
		setTitle("Editar de Locador");
		setResizable(false);
		setBounds(100, 100, 1027, 602);
		getContentPane().setLayout(null);
		
		
		
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaEdicaoLocador.class.getResource("/images/icons/locador.png")));
		lblLogo.setBounds(426, 0, 216, 168);
		getContentPane().add(lblLogo);
		
		JPanel panelDadosPessoais = new JPanel();
		panelDadosPessoais.setBorder(new TitledBorder(null, "Dados pessoais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDadosPessoais.setBounds(40, 180, 468, 316);
		getContentPane().add(panelDadosPessoais);
		panelDadosPessoais.setLayout(null);
		
		MaskFormatter mascaraTexto = new MaskFormatter("******************************");
		mascaraTexto.setValidCharacters(" aÃ¡Ã Ã¤bcÃ§deÃ©Ã¨Ã«fghiÃ­Ã¬Ã¯jklmnoÃ³Ã²Ã¶pqrstuÃºÃ¹Ã¼vwxyzAÃ�Ã€Ã„BCÃ‡DEÃ‰ÃˆÃ‹FGHIÃ�ÃŒÃ�JKLMNOÃ’Ã“Ã–PQRSTUÃšÃ™ÃœVWXYZ");		
		
		JLabel lblNome = new JLabel("Nome *");
		lblNome.setBounds(12, 38, 66, 15);
		panelDadosPessoais.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF *");
		lblCpf.setBounds(12, 79, 66, 15);
		panelDadosPessoais.add(lblCpf);
		
		JLabel lblCelular = new JLabel("Celular *");
		lblCelular.setBounds(12, 120, 66, 15);
		panelDadosPessoais.add(lblCelular);
		
		JLabel lblLogin = new JLabel("Login *");
		lblLogin.setBounds(12, 161, 66, 15);
		panelDadosPessoais.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha  *");
		lblSenha.setBounds(12, 202, 66, 15);
		panelDadosPessoais.add(lblSenha);
		
		txtNome = new JFormattedTextField(mascaraTexto);
		txtNome.setBounds(83, 36, 373, 19);
		txtNome.setText(locador.getNome());
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
		txtCPF.setText(locador.getCpf());
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
		txtCelular.setText(locador.getCelular());
		txtCelular.setBounds(83, 118, 373, 19);
		panelDadosPessoais.add(txtCelular);
		
		txtLogin = new JFormattedTextField(mascaraTexto);
		txtLogin.setEditable(false);
		txtLogin.setColumns(10);
		txtLogin.setText(locador.getLogin());
		txtLogin.setBounds(83, 159, 373, 19);
		panelDadosPessoais.add(txtLogin);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(83, 200, 373, 19);
		panelDadosPessoais.add(txtSenha);
		
		JPanel panelEndereco = new JPanel();
		panelEndereco.setLayout(null);
		panelEndereco.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelEndereco.setBounds(520, 180, 468, 316);
		getContentPane().add(panelEndereco);
		
		JLabel lblLogradouro = new JLabel("Logradouro *");
		lblLogradouro.setBounds(12, 38, 117, 15);
		panelEndereco.add(lblLogradouro);
		
		JLabel lblNmero = new JLabel("NÃºmero *");
		lblNmero.setBounds(12, 79, 66, 15);
		panelEndereco.add(lblNmero);
		
		JLabel lblBairro = new JLabel("Complemento");
		lblBairro.setBounds(12, 120, 117, 15);
		panelEndereco.add(lblBairro);
		
		JLabel lblBairro_1 = new JLabel("Bairro *");
		lblBairro_1.setBounds(12, 161, 66, 15);
		panelEndereco.add(lblBairro_1);
		
		JLabel lblCidade = new JLabel("Cidade *");
		lblCidade.setBounds(12, 202, 66, 15);
		panelEndereco.add(lblCidade);
		
		txtLogradouro = new JFormattedTextField(mascaraTexto);
		txtLogradouro.setColumns(10);
		txtLogradouro.setText(locador.getEndereco().getLogradouro());
		txtLogradouro.setBounds(134, 36, 322, 19);
		panelEndereco.add(txtLogradouro);
		
		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setText(locador.getEndereco().getNumero());
		txtNumero.setBounds(134, 77, 322, 19);
		panelEndereco.add(txtNumero);
		
		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setText(locador.getEndereco().getComplemento());
		txtComplemento.setBounds(134, 118, 322, 19);
		panelEndereco.add(txtComplemento);
		
		txtBairro = new JFormattedTextField(mascaraTexto);
		txtBairro.setColumns(10);
		txtBairro.setText(locador.getEndereco().getBairro());
		txtBairro.setBounds(134, 159, 322, 19);
		panelEndereco.add(txtBairro);
		
		txtCidade = new JFormattedTextField(mascaraTexto);
		txtCidade.setBounds(134, 200, 322, 19);
		txtCidade.setText(locador.getEndereco().getCidade());
		panelEndereco.add(txtCidade);
		
		JLabel lblEstado = new JLabel("Estado *");
		lblEstado.setBounds(12, 243, 66, 15);
		panelEndereco.add(lblEstado);
		
		txtEstado = new JFormattedTextField(mascaraTexto);
		txtEstado.setBounds(134, 241, 322, 19);
		txtEstado.setText(locador.getEndereco().getEstado());
		panelEndereco.add(txtEstado);
		
		JLabel lblCep = new JLabel("CEP *");
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
		txtCEP.setText(locador.getEndereco().getCep());
		panelEndereco.add(txtCEP);
		
		JButton btnCadastrar = new JButton("Atualizar");
		btnCadastrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				Endereco end = new Endereco();
				Locador loc = new Locador();
				LocadorController controller = new LocadorController();
				
				end.setId(locador.getEndereco().getId());
				end.setLogradouro(txtLogradouro.getText());
				end.setBairro(txtBairro.getText());
				end.setNumero(txtNumero.getText());
				end.setCep(txtCEP.getText());
				end.setComplemento(txtComplemento.getText());
				end.setCidade(txtCidade.getText());
				end.setEstado(txtEstado.getText());
				
				loc.setId(locador.getId());
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
						JOptionPane.showMessageDialog(null, "NÃ£o foi possÃ­vel atualizar o locador.");
					}
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnCadastrar.setBounds(874, 508, 114, 25);
		getContentPane().add(btnCadastrar);
		
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
		getContentPane().add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(633, 508, 114, 25);
		getContentPane().add(btnCancelar);
		
		JLabel lblCamposObrigatrios = new JLabel("* Campos obrigatÃ³rios");
		lblCamposObrigatrios.setFont(new Font("Dialog", Font.ITALIC, 10));
		lblCamposObrigatrios.setBounds(40, 514, 216, 15);
		getContentPane().add(lblCamposObrigatrios);
		
		JButton btnDeletarUsurio = new JButton("Deletar locador");
		btnDeletarUsurio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showConfirmDialog(null, "AtenÃ§Ã£o seu login serÃ¡ deletado do sistema. Caso confirme, nÃ£o serÃ¡ possÃ­vel reverter posteriormente.");
				Locador loc = new Locador();
				Endereco end = new Endereco();
				loc.setId(locador.getId());
				end.setId(locador.getEndereco().getId());
				
				LocadorController controller = new LocadorController();
				controller.enviarParaServiceDeletar(loc,end);	
				dispose();
				inicializa(args);
			}
		});
		btnDeletarUsurio.setBackground(new Color(255, 51, 51));
		btnDeletarUsurio.setForeground(Color.WHITE);
		btnDeletarUsurio.setBounds(167, 508, 159, 25);
		getContentPane().add(btnDeletarUsurio);
	}

}
