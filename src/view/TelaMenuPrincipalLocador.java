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
import service.UtilsService;

public class TelaMenuPrincipalLocador extends JFrame {

	private static final long serialVersionUID = 1L;
	private Endereco end = new Endereco(0, "logradouro", "numero", "complemento", "cep", "bairro", "cidade", "estado");
	private Locador loc = new Locador(0, "nome", "cpf", this.end, "celular", "login", "senha", LocalDate.now(), null);
	private JDesktopPane desktopPane = new JDesktopPane();
	private String[] args;
	private JFrame frmMenuLocatario = new JFrame();
	private static Properties prop = UtilsService.getProp();

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

	/**
	 * @wbp.parser.constructor
	 */
	public TelaMenuPrincipalLocador(String[] args) {
		inicializaTela();
	}

	public void inicializaTela() {
		frmMenuLocatario.getContentPane().setBackground(Color.DARK_GRAY);
		frmMenuLocatario.setTitle(prop.getProperty("MenuLocadorView.Title"));
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

		JMenu menuMeusDados = new JMenu(prop.getProperty("MenuLocadorView.Menu.MeusDados"));
		menuBar.add(menuMeusDados);

		JMenuItem menuItemMeusDadosEditar = new JMenuItem(prop.getProperty("MenuLocadorView.Menu.MenuItem.Editar"));
		
		menuItemMeusDadosEditar.addActionListener(new ActionListener() {
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
		
		menuItemMeusDadosEditar.setHorizontalAlignment(SwingConstants.LEFT);
		menuMeusDados.add(menuItemMeusDadosEditar);

		JMenuItem menuItemMeusDadosSair = new JMenuItem(prop.getProperty("MenuLocadorView.Menu.MenuItem.Sair"));
		menuItemMeusDadosSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frmMenuLocatario.setVisible(false);
				inicializa(args);
			}

		});
		menuMeusDados.add(menuItemMeusDadosSair);
		
		JMenu menuBike = new JMenu(prop.getProperty("MenuLocadorView.Menu.Bike"));
		menuBar.add(menuBike);
		
		JMenuItem menuItemBikeCadastrar = new JMenuItem(prop.getProperty("MenuLocadorView.Bike.MenuItem.Cadastrar"));
		menuItemBikeCadastrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				TelaCadastroBicicleta tela = new TelaCadastroBicicleta(loc,args);
				desktopPane.add(tela);
				tela.setPosition();
				tela.setVisible(true);			
			}
		});
		
		menuBike.add(menuItemBikeCadastrar);
		
		JMenuItem menuItemBikeEditar = new JMenuItem(prop.getProperty("MenuLocadorView.Bike.MenuItem.Editar"));
		menuItemBikeEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TelaEdicaoBicicleta tela = new TelaEdicaoBicicleta(loc, args);
				desktopPane.add(tela);
				tela.setPosition();
				tela.setVisible(true);
				
			}
		});
		menuBike.add(menuItemBikeEditar);
		
		JMenu menuItemAluguel = new JMenu(prop.getProperty("MenuLocadorView.Menu.Aluguel"));
		menuBar.add(menuItemAluguel);
		
		JMenuItem menuItemAluguelSolicitacoes = new JMenuItem(prop.getProperty("MenuLocadorView.Aluguel.MenuItem.Solicitacoes"));
		menuItemAluguelSolicitacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TelaSolicitacaoPendenteLocador tela = new TelaSolicitacaoPendenteLocador(loc, args);
				desktopPane.add(tela);
				tela.setPosition();
				tela.setVisible(true);
				
			}
		});
		menuItemAluguel.add(menuItemAluguelSolicitacoes);
		
		JMenuItem menuItemAlugueisAgendados = new JMenuItem(prop.getProperty("MenuLocadorView.Aluguel.MenuItem.Alugueis.Agendados"));
		menuItemAlugueisAgendados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaAluguelPendenteLocador tela = new TelaAluguelPendenteLocador(loc, args);
				desktopPane.add(tela);
				tela.setPosition();
				tela.setVisible(true);	
			}
		});
		menuItemAluguel.add(menuItemAlugueisAgendados);
		
		JMenuItem mntmAluguisFinalizados = new JMenuItem("Aluguéis finalizados");
		mntmAluguisFinalizados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaFinalizarAluguel tela = new TelaFinalizarAluguel(loc, args);
				desktopPane.add(tela);
				tela.setPosition();
				tela.setVisible(true);	
			}
		});
		menuItemAluguel.add(mntmAluguisFinalizados);

		frmMenuLocatario.setVisible(true);
	}
}
