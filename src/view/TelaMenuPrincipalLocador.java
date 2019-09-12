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
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Locador;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaMenuPrincipalLocador extends JFrame {

	private JPanel contentPane;
	private Locador loc = new Locador();
	private JDesktopPane desktopPane;
	private JFrame frmMenuLocador;
	private String[] args;

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
	
	public TelaMenuPrincipalLocador(String[] args) {
		inicializaTela();
	}
	
	
	public void inicializaTela() {
		frmMenuLocador = new JFrame();
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
		desktopPane = new JDesktopPane() {

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		desktopPane.add(labelBackground, BorderLayout.CENTER);
		desktopPane.setBounds(131, 57, 1, 1);
		frmMenuLocador.getContentPane().add(desktopPane, BorderLayout.CENTER);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 128, 21);
		frmMenuLocador.setJMenuBar(menuBar);
		
		JMenu mnTeste = new JMenu("Meu espa\u00E7o");
		menuBar.add(mnTeste);
		
		JMenuItem mntmCadastrarBicicleta = new JMenuItem("Editar meus dados");
		mntmCadastrarBicicleta.setHorizontalAlignment(SwingConstants.LEFT);
		mnTeste.add(mntmCadastrarBicicleta);
		
		
		JMenuItem mntmLogout = new JMenuItem("Sair...");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			logout();
			}
		});
		mnTeste.add(mntmLogout);
		
		
		
		frmMenuLocador.setVisible(true);
	}
	
	public void logout() {
		frmMenuLocador.dispose();
		frmMenuLocador.setVisible(false);		
		inicializa(args);
	}
}
