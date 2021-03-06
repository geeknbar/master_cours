package interfaceGraphique;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MainWindow {

	private JFrame frame;
	private static String fichier;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.setBounds(100, 100, 650, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainWindow.class.getResource("/img/logo.png")));

		JLabel lblBf = new JLabel("BF");
		lblBf.setForeground(Color.BLUE);
		lblBf.setFont(new Font("Techno Hideo", Font.PLAIN, 19));

		final JTextField textBF = new JTextField();
		final JTextField textBR = new JTextField();
		
		JButton btnExplorerBR = new JButton("Explorer");
		btnExplorerBR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoadFromFile(frame,"Ouvrir","","*.txt");
				textBR.setText(fichier);
				frame.validate ();
			}
		});

		JLabel lblBr = new JLabel("BR");
		lblBr.setForeground(Color.BLUE);
		lblBr.setFont(new Font("Techno Hideo", Font.PLAIN, 19));

		

		JButton btnExplorerBF = new JButton("Explorer");
		btnExplorerBF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadFromFile(frame,"Ouvrir","","*.txt");
				textBF.setText(fichier);
				frame.validate ();
			}
		});

		JComboBox<String> choixChainage = new JComboBox<String>();
		choixChainage.addItem("Chainage Avant");
		choixChainage.addItem("Chainage Arri�re");
		
		JLabel lblChainage = new JLabel("Chainage ");
		lblChainage.setForeground(Color.BLUE);
		lblChainage.setFont(new Font("Techno Hideo", Font.PLAIN, 19));

		JRadioButton rdbtnSaturation = new JRadioButton("Saturation");

		JRadioButton rdbtnBut = new JRadioButton("But");

		JRadioButton rdbtnProfondeur = new JRadioButton("Profondeur");

		JRadioButton rdbtnLargeur = new JRadioButton("Largeur");

		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO lancer algooo
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(107)
						.addComponent(lblNewLabel)
						.addContainerGap(705, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
												.addContainerGap()
												.addComponent(btnQuitter, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE))
												.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
														.addGap(18)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(lblBf, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblChainage, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblBr))
																.addPreferredGap(ComponentPlacement.RELATED)
																.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																		.addComponent(textBF, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
																		.addGroup(Alignment.TRAILING, groupLayout.createParallelGroup(Alignment.TRAILING, false)
																				.addComponent(choixChainage, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																				.addComponent(textBR, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)))
																				.addGap(18)
																				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																						.addGroup(groupLayout.createSequentialGroup()
																								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																										.addComponent(rdbtnSaturation)
																										.addComponent(rdbtnProfondeur))
																										.addGap(18)
																										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																												.addComponent(rdbtnLargeur)
																												.addComponent(rdbtnBut)))
																												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
																														.addComponent(btnExplorerBF, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																														.addComponent(btnExplorerBR, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
																														.addGroup(groupLayout.createSequentialGroup()
																																.addContainerGap()
																																.addComponent(btnValider, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
																																.addGap(258)))
																																.addGap(33))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(lblNewLabel)
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(3)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(btnExplorerBR, 0, 0, Short.MAX_VALUE)
												.addComponent(textBF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(18)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(btnExplorerBF, 0, 0, Short.MAX_VALUE)
														.addComponent(textBR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addGap(3))
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(lblBf, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
																.addGap(21)
																.addComponent(lblBr, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
																.addGap(15)
																.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
																		.addComponent(choixChainage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																		.addComponent(lblChainage, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
																		.addComponent(rdbtnSaturation)
																		.addComponent(rdbtnBut))
																		.addPreferredGap(ComponentPlacement.UNRELATED)
																		.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
																				.addComponent(rdbtnProfondeur)
																				.addComponent(rdbtnLargeur))
																				.addGap(28)
																				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
																						.addComponent(btnValider)
																						.addComponent(btnQuitter))
																						.addGap(30))
				);
		frame.getContentPane().setLayout(groupLayout);
	}


	public void LoadFromFile(JFrame frame, String titre, String dossierOuverture,String extention)
	{
		FileDialog fileDialog=new FileDialog(frame, titre, FileDialog.LOAD);
		fileDialog.setFile(extention);
		fileDialog.setDirectory(dossierOuverture);
		fileDialog.setLocation(50, 50);
		fileDialog.setDirectory(dossierOuverture);
		fileDialog.setVisible(true);

		fichier=fileDialog.getDirectory()+fileDialog.getFile();
		
		if(fichier==null)
			fichier="";
	}

	public static String getFichier() {
		return fichier;
	}

	public static void setFichier(String fichier) {
		MainWindow.fichier = fichier;
	}





}
