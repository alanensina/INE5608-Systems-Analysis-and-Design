package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.LocadorDAO;
import dao.LocatarioDAO;
import model.Locador;
import model.Locatario;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TelaLista extends JFrame {

	private JPanel contentPane;
	private JTable tbLocador;
	private JTable tbLocatario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLista frame = new TelaLista();
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
	public TelaLista() {
		setResizable(false);
		setTitle("Lista de usuários");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1047, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Lista de Locadores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 0, 1012, 195);
		contentPane.add(panel);
		
		tbLocador = new JTable();
		tbLocador.setCellSelectionEnabled(true);
		tbLocador.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "CPF", "Celular", "Login", "Data de Cadastro"
			}
		));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(tbLocador, GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(tbLocador, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Lista de Locat\u00E1rios", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_1.setBounds(12, 207, 1012, 195);
		contentPane.add(panel_1);
		
		tbLocatario = new JTable();
		tbLocatario.setCellSelectionEnabled(true);
		tbLocatario.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"ID", "Nome", "CPF", "Celular", "Login", "Data de Cadastro"
			}
		));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(tbLocatario, GroupLayout.PREFERRED_SIZE, 978, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addComponent(tbLocatario, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(690, 414, 114, 25);
		contentPane.add(btnCancelar);
		
		JButton button = new JButton("Carregar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocadorDAO locadorDAO = new LocadorDAO();
				LocatarioDAO locatarioDAO = new LocatarioDAO();
				carregaTabelaLocadores(locadorDAO.listarLocadores());	
				carregaTabelaLocatarios(locatarioDAO.listarLocatarios());
			}
		});
		button.setBounds(910, 414, 114, 25);
		contentPane.add(button);
	}
	
	public void carregaTabelaLocadores(List<Locador> lista) {
		DefaultTableModel model = (DefaultTableModel) tbLocador.getModel();
		model.setNumRows(0);

		for (Locador loc : lista) {
			model.addRow(new Object[] { loc.getId(), loc.getNome(), loc.getCpf(), loc.getCelular(), loc.getLogin(), loc.getDataCadastro().toString() });
		}
	}
	
	public void carregaTabelaLocatarios(List<Locatario> lista) {
		DefaultTableModel model = (DefaultTableModel) tbLocatario.getModel();
		model.setNumRows(0);

		for (Locatario loc : lista) {
			model.addRow(new Object[] { loc.getId(), loc.getNome(), loc.getCpf(), loc.getCelular(), loc.getLogin(), loc.getDataCadastro().toString() });
		}
	}
}
