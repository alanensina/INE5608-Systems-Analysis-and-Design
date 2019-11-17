package view;

import static controller.AppController.inicializa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.time.LocalDate;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import model.Endereco;
import model.Locador;
import model.Locatario;
import service.UtilsService;

public class TelaMenuPrincipalLocatario extends JFrame {

	private static final long serialVersionUID = 6001933533567233314L;
	private Endereco end = new Endereco(0, "logradouro", "numero", "complemento", "cep", "bairro", "cidade", "estado");
	private Locatario loc = new Locatario(0, "nome", "cpf", this.end, "celular", "login", "senha", LocalDate.now());
	private JDesktopPane desktopPane = new JDesktopPane();
	private String[] args;
	private JFrame frmMenuLocatario = new JFrame();
	private static Properties prop = UtilsService.getProp();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenuPrincipalLocatario window = new TelaMenuPrincipalLocatario(args);
					window.frmMenuLocatario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaMenuPrincipalLocatario(Locatario loc, String[] args) {
		this.loc = loc;
		this.args = args;
		inicializaTela();
	}

	/**
	 * @wbp.parser.constructor
	 */
	public TelaMenuPrincipalLocatario(String[] args) {
		inicializaTela();
	}

	public void inicializaTela() {
		frmMenuLocatario.getContentPane().setBackground(Color.DARK_GRAY);
		frmMenuLocatario.setTitle(prop.getProperty("MenuLocatarioView.Title"));
		frmMenuLocatario.setResizable(false);
		frmMenuLocatario.setExtendedState(JFrame.MAXIMIZED_BOTH); // Open the frame maximized
		frmMenuLocatario.setBounds(100, 100, 1280, 720);
		frmMenuLocatario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenuLocatario.getContentPane().setLayout(new BorderLayout());

		URL resource = this.getClass().getResource("/images/backgrounds/backgroundLocatario.jpg");
		ImageIcon icon = new ImageIcon(resource);

		desktopPane = new JDesktopPane();
		desktopPane.setBounds(131, 57, 1, 1);
		desktopPane.add(new BackgroundPanel(icon.getImage()));

		frmMenuLocatario.getContentPane().add(desktopPane, BorderLayout.CENTER);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 128, 21);
		frmMenuLocatario.setJMenuBar(menuBar);

		JMenu menuMeusDados = new JMenu(prop.getProperty("MenuLocatarioView.Menu.MeusDados"));
		menuBar.add(menuMeusDados);

		JMenuItem menuItemMeusDadosEditar = new JMenuItem(prop.getProperty("MenuLocatarioView.Menu.MenuItem.Editar"));

		menuItemMeusDadosEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					TelaEdicaoLocatario tela = new TelaEdicaoLocatario(args, loc);
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

		menuItemMeusDadosEditar.setHorizontalAlignment(SwingConstants.LEFT);
		menuMeusDados.add(menuItemMeusDadosEditar);

		JMenuItem menuItemMeusDadosSair = new JMenuItem(prop.getProperty("MenuLocatarioView.Menu.MenuItem.Sair"));
		menuItemMeusDadosSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frmMenuLocatario.setVisible(false);
				inicializa(args);
			}

		});

		JMenuItem menuItemMeusDadosAvaliacoes = new JMenuItem(
				prop.getProperty("MenuLocatarioView.Menu.MenuItem.Avaliacoes"));
		menuMeusDados.add(menuItemMeusDadosAvaliacoes);
		menuMeusDados.add(menuItemMeusDadosSair);

		JMenu mnAluguel = new JMenu("Aluguel");
		menuBar.add(mnAluguel);

		JMenuItem mntmSolicitar = new JMenuItem("Solicitar");
		mntmSolicitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					SolicitarAluguelView tela = new SolicitarAluguelView(loc, args);
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
		mnAluguel.add(mntmSolicitar);

		JMenuItem mntmSolicitaesPendentes = new JMenuItem("Alugueis agendados");
		mntmSolicitaesPendentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
				TelaAluguelPendenteLocatario tela = new TelaAluguelPendenteLocatario(loc, args);
				tela.setVisible(true);
				desktopPane.add(tela);
				tela.setPosition();
				tela.moveToFront();
				tela.setSelected(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnAluguel.add(mntmSolicitaesPendentes);
		
		JMenuItem mntmAluguelEmAndamento = new JMenuItem("Aluguel em andamento");
		mntmAluguelEmAndamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					TelaGerenciamentoAluguel tela = new TelaGerenciamentoAluguel(args, loc);
					tela.setVisible(true);
					desktopPane.add(tela);
					tela.setPosition();
					tela.moveToFront();
					tela.setSelected(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
		mnAluguel.add(mntmAluguelEmAndamento);

		frmMenuLocatario.setVisible(true);
	}
}
