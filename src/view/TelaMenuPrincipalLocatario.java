package view;

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

import model.Locatario;

public class TelaMenuPrincipalLocatario extends JFrame {

	private JPanel contentPane;
	private Locatario loc = new Locatario();
	private JDesktopPane desktopPane;
	private JFrame frmMenuLocatario;
	private String[] args;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenuPrincipalLocatario window = new TelaMenuPrincipalLocatario();
					window.frmMenuLocatario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TelaMenuPrincipalLocatario(Locatario loc) {
		this.loc = loc;
		inicializaTela();
	}
	
	public TelaMenuPrincipalLocatario( ) {
		inicializaTela();
	}
	
	public void inicializaTela( ) {
		frmMenuLocatario = new JFrame();
		frmMenuLocatario.getContentPane().setBackground(Color.DARK_GRAY);
		frmMenuLocatario.setTitle("Vá de Bike!");
		frmMenuLocatario.setResizable(false);
		frmMenuLocatario.setExtendedState(JFrame.MAXIMIZED_BOTH); // Open the frame maximized
		frmMenuLocatario.setBounds(100, 100, 1280, 720);
		frmMenuLocatario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenuLocatario.getContentPane().setLayout(new BorderLayout());

		JLabel labelBackground = new JLabel();
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/backgroundLocatario.jpg"));
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
		frmMenuLocatario.getContentPane().add(desktopPane, BorderLayout.CENTER);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 128, 21);
		frmMenuLocatario.setJMenuBar(menuBar);
		
		JMenu mnTeste = new JMenu("Cadastro");
		menuBar.add(mnTeste);
		
		JMenuItem mntmCadastrarBicicleta = new JMenuItem("Cadastrar bicicleta");
		mntmCadastrarBicicleta.setHorizontalAlignment(SwingConstants.LEFT);
		mnTeste.add(mntmCadastrarBicicleta);
		
		
		frmMenuLocatario.setVisible(true);
		
	}

}
