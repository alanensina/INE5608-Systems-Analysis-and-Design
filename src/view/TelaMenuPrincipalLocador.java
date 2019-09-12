package view;

import static controller.AppController.inicializa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Bicicleta;
import model.Endereco;
import model.Locador;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.awt.event.ActionEvent;

public class TelaMenuPrincipalLocador extends JFrame {

	private JPanel contentPane;
	private Endereco end = new Endereco(0, "logradouro", "numero", "complemento", "cep", "bairro", "cidade", "estado");
	private Locador loc = new Locador(0, "nome", "cpf", this.end, "celular", "login", "senha", LocalDate.now(), null);
	private JDesktopPane desktopPane = new JDesktopPane();
	private String[] args;
	private static JFrame frmMenuLocador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenuPrincipalLocador window = new TelaMenuPrincipalLocador(args);
					window.frmMenuLocador.setVisible(true);
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
		frmMenuLocador = getInstancia();
		frmMenuLocador.getContentPane().setBackground(Color.DARK_GRAY);
		frmMenuLocador.setTitle("Vá de Bike!");
		frmMenuLocador.setResizable(false);
		frmMenuLocador.setExtendedState(JFrame.MAXIMIZED_BOTH); // Open the frame maximized
		frmMenuLocador.setBounds(100, 100, 1280, 720);
		frmMenuLocador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenuLocador.getContentPane().setLayout(new BorderLayout());
		
		
		JLabel labelBackground = new JLabel();
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/backgroundLocador.jpg"));
		Image image = icon.getImage();
		labelBackground.setIcon(icon);
		
		
//		desktopPane = new JDesktopPane() {
//
//			public void paintComponent(Graphics g) {
//				super.paintComponent(g);
//				g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
//				repaint();
//			}
//		};
//		desktopPane.add(labelBackground, BorderLayout.CENTER);
	
		
		desktopPane.setBounds(131, 57, 1, 1);
		frmMenuLocador.getContentPane().add(desktopPane, BorderLayout.CENTER);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 128, 21);
		frmMenuLocador.setJMenuBar(menuBar);

		JMenu mnDados = new JMenu("Meu dados");
		menuBar.add(mnDados);

		JMenuItem mntmCadastrarBicicleta = new JMenuItem("Editar");
		mntmCadastrarBicicleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					TelaEdicaoLocador tela = new TelaEdicaoLocador(args, loc);
					desktopPane.add(tela);
					tela.setVisible(true);
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
				frmMenuLocador.setVisible(false);
				inicializa(args);
			}

		});
		mnDados.add(mntmLogout);

		frmMenuLocador.setVisible(true);
	}

	// Singleton
	public static JFrame getInstancia() {
		if (frmMenuLocador == null) {
			frmMenuLocador = new JFrame();
		}
		return frmMenuLocador;
	}

}
