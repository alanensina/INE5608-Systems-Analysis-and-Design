package view;

import static controller.AppController.inicializa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import model.Endereco;
import model.Locador;

public class TelaMenuPrincipalLocador extends JFrame {

	private static final long serialVersionUID = 1L;
	private Endereco end = new Endereco(0, "logradouro", "numero", "complemento", "cep", "bairro", "cidade", "estado");
	private Locador loc = new Locador(0, "nome", "cpf", this.end, "celular", "login", "senha", LocalDate.now(), null);
	private JDesktopPane desktopPane = new JDesktopPane();
	private String[] args;
	private JFrame frmMenuLocatario = new JFrame();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenuPrincipalLocador window = new TelaMenuPrincipalLocador(args);
					window.frmMenuLocatario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaMenuPrincipalLocador(Locador loc, String[] args) {
		this.loc = loc;
		this.args = args;
		inicializaTela();
	}

	public TelaMenuPrincipalLocador(String[] args) {
		inicializaTela();
	}

	public void inicializaTela() {
		frmMenuLocatario.getContentPane().setBackground(Color.DARK_GRAY);
		frmMenuLocatario.setTitle("V� de Bike!");
		frmMenuLocatario.setResizable(false);
		frmMenuLocatario.setExtendedState(JFrame.MAXIMIZED_BOTH); // Open the frame maximized
		frmMenuLocatario.setBounds(100, 100, 1280, 720);
		frmMenuLocatario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenuLocatario.getContentPane().setLayout(new BorderLayout());
		
		URL resource = this.getClass().getResource("/images/backgrounds/backgroundLocador.jpg");
		ImageIcon icon = new ImageIcon(resource);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(131, 57, 1, 1);
		desktopPane.add(new BackgroundPanel(icon.getImage()));
		
		frmMenuLocatario.getContentPane().add(desktopPane, BorderLayout.CENTER);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 128, 21);
		frmMenuLocatario.setJMenuBar(menuBar);

		JMenu mnDados = new JMenu("Meu dados");
		menuBar.add(mnDados);

		JMenuItem mntmCadastrarBicicleta = new JMenuItem("Editar");
		
		mntmCadastrarBicicleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					TelaEdicaoLocador tela = new TelaEdicaoLocador(args, loc);
					tela.setVisible(true);
					desktopPane.add(tela);
					tela.setPosition();
					tela.moveToFront();
					tela.setSelected(true);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		mntmCadastrarBicicleta.setHorizontalAlignment(SwingConstants.LEFT);
		mnDados.add(mntmCadastrarBicicleta);

		JMenuItem mntmLogout = new JMenuItem("Sair");
		mntmLogout.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frmMenuLocatario.setVisible(false);
				inicializa(args);
			}

		});
		
		JMenuItem mntmAvaliaes = new JMenuItem("Avalia\u00E7\u00F5es");
		mnDados.add(mntmAvaliaes);
		mnDados.add(mntmLogout);
		
		JMenu mnBicicleta = new JMenu("Bicicleta");
		menuBar.add(mnBicicleta);
		
		JMenuItem mntmCadastrar = new JMenuItem("Cadastrar");
		mntmCadastrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				TelaCadastroBicicleta tela = new TelaCadastroBicicleta();
				desktopPane.add(tela);
				tela.setPosition();
				tela.setVisible(true);			
			}
		});
		
		mnBicicleta.add(mntmCadastrar);
		
		JMenuItem mntmEditar = new JMenuItem("Editar");
		mnBicicleta.add(mntmEditar);
		
		JMenuItem mntmExcluir = new JMenuItem("Excluir");
		mnBicicleta.add(mntmExcluir);
		
		JMenuItem mntmListar = new JMenuItem("Listar");
		mnBicicleta.add(mntmListar);
		
		JMenu mnAluguel = new JMenu("Aluguel");
		menuBar.add(mnAluguel);
		
		JMenuItem mntmSolicitaes = new JMenuItem("Solicita\u00E7\u00F5es pendentes");
		mnAluguel.add(mntmSolicitaes);
		
		JMenuItem mntmT = new JMenuItem("Hist\u00F3rico");
		mnAluguel.add(mntmT);

		frmMenuLocatario.setVisible(true);
	}
}
