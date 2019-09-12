package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import dao.LocadorDAO;
import dao.LocatarioDAO;
import model.Locador;
import model.Locatario;

public class TelaEditarUsuario extends JFrame {

	private JPanel contentPane;
	private LocadorDAO locadorDAO = new LocadorDAO();
	private LocatarioDAO locatarioDAO = new LocatarioDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEditarUsuario frame = new TelaEditarUsuario(args);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaEditarUsuario(String[] args) {
		
		List<Locador> locadores = locadorDAO.listarLocadores();
		List<Locatario> locatarios = locatarioDAO.listarLocatarios();
		
		setResizable(false);
		setTitle("Editar usuários");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Editar locador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 12, 426, 117);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblSelecione = new JLabel("Selecione");
		lblSelecione.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSelecione.setBounds(12, 38, 66, 15);
		panel.add(lblSelecione);
		
		JComboBox cbLocador = new JComboBox();
		cbLocador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Locador loc = (Locador)cbLocador.getSelectedItem();
				
				System.out.println(loc.getNome());
			}
		});
		cbLocador.setBounds(96, 33, 280, 24);
		for (Locador locador : locadores) {
			cbLocador.addItem(locador);
		}
		panel.add(cbLocador);
		
		JButton btnEditarLocador = new JButton("Editar");
		btnEditarLocador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Locador locadorSelecionado = (Locador)cbLocador.getSelectedItem();
				TelaEdicaoLocador.main(args, locadorSelecionado);
				dispose();				
			}
		});
		
		
		btnEditarLocador.setBounds(142, 69, 114, 25);
		panel.add(btnEditarLocador);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(155, 334, 114, 25);
		contentPane.add(btnCancelar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Editar locat\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_1.setBounds(12, 160, 426, 117);
		contentPane.add(panel_1);
		
		JLabel label = new JLabel("Selecione");
		label.setFont(new Font("Dialog", Font.PLAIN, 12));
		label.setBounds(12, 38, 66, 15);
		panel_1.add(label);
		
		JComboBox cbLocatario = new JComboBox();
		cbLocatario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Locatario loc = (Locatario)cbLocatario.getSelectedItem();
			}
		});
		cbLocatario.setBounds(96, 33, 283, 24);
		for (Locatario locatario : locatarios) {
			cbLocatario.addItem(locatario);
		}
		panel_1.add(cbLocatario);
		
		JButton btnEditarLocatario = new JButton("Editar");
		btnEditarLocatario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Locatario locatarioSelecionado = (Locatario)cbLocatario.getSelectedItem();
				TelaEdicaoLocatario.main(args, locatarioSelecionado);
				dispose();
				
			}
		});
		btnEditarLocatario.setBounds(142, 69, 114, 25);
		panel_1.add(btnEditarLocatario);
	}
}
