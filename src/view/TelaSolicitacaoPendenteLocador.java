package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import controller.AluguelController;
import dao.AluguelDAO;
import model.Aluguel;
import model.Bicicleta;
import model.Locador;

import javax.swing.JButton;

import static service.UtilsService.getProp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;
import java.awt.event.ActionEvent;

public class TelaSolicitacaoPendenteLocador extends JInternalFrame {

	private static final long serialVersionUID = 3165533841059874949L;
	private AluguelDAO aluDAO = new AluguelDAO();
	private AluguelController controller = new AluguelController();
	private static Locador loc;
	private JTextField txtLocatario;
	private JTextField txtBicicleta;
	private JTextField txtDataInicio;
	private JTextField txtDataFim;
	private JTextField txtValor;
	private Properties prop = getProp();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSolicitacaoPendenteLocador frame = new TelaSolicitacaoPendenteLocador(loc, args);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaSolicitacaoPendenteLocador(Locador loc, String[] args) {
		TelaSolicitacaoPendenteLocador.loc = loc;
		setTitle(prop.getProperty("TelaSolicitacaoPendenteLocador.Title"));
		setBounds(100, 100, 450, 516);
		getContentPane().setLayout(null);
		
		JLabel lbSolicitacoes = new JLabel(prop.getProperty("TelaSolicitacaoPendenteLocador.Label.Solicitacoes"));
		lbSolicitacoes.setBounds(12, 167, 114, 15);
		getContentPane().add(lbSolicitacoes);
		
		List<Aluguel> solicitacoes = aluDAO.listarSolicitacoesPendentes(loc);
		JComboBox<Aluguel> cbSolicitacoes = new JComboBox<Aluguel>();
		cbSolicitacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Aluguel aluguel = (Aluguel) cbSolicitacoes.getSelectedItem();
				txtLocatario.setText(aluguel.getLocatario().getNome());
				txtBicicleta.setText(aluguel.getBicicleta().getModelo());
				txtDataInicio.setText(aluguel.getDtInicio().toString());
				txtDataFim.setText(aluguel.getDtFimPrevisto().toString());
				txtValor.setText(String.valueOf(aluguel.getValorPrevisto()));
			}
		});
		cbSolicitacoes.setModel(new DefaultComboBoxModel(solicitacoes.toArray()));
		cbSolicitacoes.setBounds(129, 162, 299, 24);
		getContentPane().add(cbSolicitacoes);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaSolicitacaoPendenteLocador.class.getResource("/images/icons/locadorSolicitacao.png")));
		lblLogo.setBounds(162, 12, 128, 138);
		getContentPane().add(lblLogo);
		
		JLabel lblLocatrio = new JLabel(prop.getProperty("TelaSolicitacaoPendenteLocador.Label.Locatario"));
		lblLocatrio.setBounds(12, 216, 114, 15);
		getContentPane().add(lblLocatrio);
		
		txtLocatario = new JTextField();
		txtLocatario.setEditable(false);
		txtLocatario.setBounds(129, 214, 299, 19);
		getContentPane().add(txtLocatario);
		txtLocatario.setColumns(10);
		
		JLabel lblBicicleta = new JLabel(prop.getProperty("TelaSolicitacaoPendenteLocador.Label.Bike"));
		lblBicicleta.setBounds(12, 258, 114, 15);
		getContentPane().add(lblBicicleta);
		
		txtBicicleta = new JTextField();
		txtBicicleta.setEditable(false);
		txtBicicleta.setColumns(10);
		txtBicicleta.setBounds(129, 256, 299, 19);
		getContentPane().add(txtBicicleta);
		
		JLabel lblIncio = new JLabel(prop.getProperty("TelaSolicitacaoPendenteLocador.Label.Inicio"));
		lblIncio.setBounds(12, 297, 114, 15);
		getContentPane().add(lblIncio);
		
		JLabel lblFim = new JLabel(prop.getProperty("TelaSolicitacaoPendenteLocador.Label.Fim"));
		lblFim.setBounds(12, 338, 114, 15);
		getContentPane().add(lblFim);
		
		txtDataInicio = new JTextField();
		txtDataInicio.setEditable(false);
		txtDataInicio.setColumns(10);
		txtDataInicio.setBounds(129, 295, 299, 19);
		getContentPane().add(txtDataInicio);
		
		txtDataFim = new JTextField();
		txtDataFim.setEditable(false);
		txtDataFim.setColumns(10);
		txtDataFim.setBounds(129, 336, 299, 19);
		getContentPane().add(txtDataFim);
		
		JLabel lblValorTotal = new JLabel(prop.getProperty("TelaSolicitacaoPendenteLocador.Label.ValorTotal"));
		lblValorTotal.setBounds(12, 376, 108, 15);
		getContentPane().add(lblValorTotal);
		
		txtValor = new JTextField();
		txtValor.setEditable(false);
		txtValor.setColumns(10);
		txtValor.setBounds(129, 374, 299, 19);
		getContentPane().add(txtValor);
		
		JButton btnCancelar = new JButton(prop.getProperty("TelaSolicitacaoPendenteLocador.Button.Cancelar"));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(12, 428, 128, 25);
		getContentPane().add(btnCancelar);
		
		JButton btnRecusar = new JButton(prop.getProperty("TelaSolicitacaoPendenteLocador.Button.Recusar"));
		btnRecusar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aluguel solicitacao = (Aluguel) cbSolicitacoes.getSelectedItem();
				
				if(txtBicicleta.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, prop.getProperty("TelaSolicitacaoPendenteLocador.Message.SelecioneUmaSolicitacao"));
					return;
				}
				
				Object[] options = { "Cancelar", "Confirmar" };
				int resp = JOptionPane.showOptionDialog(null,
						prop.getProperty("TelaSolicitacaoPendenteLocador.Message.ConfirmaCancelamento"),
						"ATENÇÃO", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

				if(resp == 1) {
					if(controller.recusarSolicitacao(solicitacao)) {
						dispose();
						return ;
					}
					dispose();
				}
			}
		});
		btnRecusar.setForeground(new Color(255, 255, 240));
		btnRecusar.setBackground(new Color(220, 20, 60));
		btnRecusar.setBounds(163, 428, 114, 25);
		getContentPane().add(btnRecusar);
		
		JButton btnAceitar = new JButton(prop.getProperty("TelaSolicitacaoPendenteLocador.Button.Aceitar"));
		btnAceitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtBicicleta.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, prop.getProperty("TelaSolicitacaoPendenteLocador.Message.SelecioneUmaSolicitacao"));
					return;
				}
				
				Aluguel solicitacao = (Aluguel) cbSolicitacoes.getSelectedItem();
				Object[] options = { "Cancelar", "Confirmar" };
				int resp = JOptionPane.showOptionDialog(null,
						prop.getProperty("TelaSolicitacaoPendenteLocador.Message.AceitaSolicitacao"),
						"ATENÇÃO", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

				if(resp == 1) {
					if(controller.aceitarSolicitacao(solicitacao)) {
						dispose();
						return ;
					}
					dispose();
				}
			}
		});
		btnAceitar.setForeground(new Color(255, 255, 240));
		btnAceitar.setBackground(new Color(60, 179, 113));
		btnAceitar.setBounds(300, 428, 128, 25);
		getContentPane().add(btnAceitar);

	}
	
	public void setPosition() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
}
