package brouillon;

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

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPanel;

import algorithme.ChainageArriere;
import algorithme.ChainageAvant;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;

public class MainWindow
{

	private JFrame frame;
	private static String fichierBF;
	private static String fichierBR;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() 
	{
		//initialisation de la fenetre et des evenements
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		final JComboBox<String> choixChainage = new JComboBox<String>();
		final ButtonGroup grpTypeParcourt = new ButtonGroup();
		final ButtonGroup grpTypeFin = new ButtonGroup();
		final ButtonGroup grpTraces = new ButtonGroup();
		final JRadioButton rdbtnSaturation = new JRadioButton("Saturation");
		final JRadioButton rdbtnBut = new JRadioButton("But");
		final JRadioButton rdbtnProfondeur = new JRadioButton("Profondeur");
		final JRadioButton rdbtnLargeur = new JRadioButton("Largeur");
		final JRadioButton rdbtnOui = new JRadioButton("Oui");
		final JRadioButton rdbtnNon = new JRadioButton("Non");
		final JTextArea textArea = new JTextArea();
		final JTextField textBF = new JTextField();
		final JTextField textBR = new JTextField();
		
		rdbtnOui.setSelected(true);
		textBF.setEnabled(false);
		textBR.setEditable(false);
		
		
		
		JLabel lblNewLabel = new JLabel("");
		JLabel lblBr = new JLabel("BR");
		JLabel lblChainage = new JLabel("Chainage ");
		JLabel lblTrace = new JLabel("Traces");
		JLabel lblBf = new JLabel("BF");
		
		
		JButton btnExplorerBF = new JButton("Explorer");
		JButton btnExplorerBR = new JButton("Explorer");
		JButton btnQuitter = new JButton("Quitter");
		JButton btnValider = new JButton("Valider");

		JPanel panelJtextField = new JPanel();
		JScrollPane scrollPane = new JScrollPane();
		JScrollPane panBF = new JScrollPane();
		JScrollPane panBR = new JScrollPane();


		fichierBF="";
		fichierBR="";
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		textBF.setMaximumSize(new Dimension(6, 20));
		textBR.setMaximumSize(new Dimension(6, 20));
		
		lblNewLabel.setIcon(new ImageIcon(MainWindow.class.getResource("/img/logo.png")));

		lblBf.setForeground(Color.BLUE);
		lblBf.setFont(new Font("Techno Hideo", Font.PLAIN, 19));

		choixChainage.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!"Chainage Avant".equals(choixChainage.getSelectedItem().toString()))
				{
					rdbtnBut.setEnabled(false);
					rdbtnSaturation.setEnabled(false);
				}
				else
				{
					rdbtnBut.setEnabled(true);
					rdbtnSaturation.setEnabled(true);
				}
			}
		});
		
		
		btnExplorerBF.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				LoadFromFile(frame,"Ouvrir","","*.txt", "BF");
				textBF.setText(fichierBF);
				frame.validate ();
			}
		});

		lblBr.setForeground(Color.BLUE);
		lblBr.setFont(new Font("Techno Hideo", Font.PLAIN, 19));
		
		btnExplorerBR.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				LoadFromFile(frame,"Ouvrir","","*.txt", "BR");
				textBR.setText(fichierBR);
				frame.validate ();
			}
		});

		
		choixChainage.addItem("Chainage Avant");
		choixChainage.addItem("Chainage Arri�re");
		
		
		lblChainage.setForeground(Color.BLUE);
		lblChainage.setFont(new Font("Techno Hideo", Font.PLAIN, 19));

		rdbtnSaturation.setSelected(true);
		rdbtnProfondeur.setSelected(true);

		grpTypeFin.add(rdbtnSaturation);
		grpTypeFin.add(rdbtnBut);
		grpTypeParcourt.add(rdbtnProfondeur);
		grpTypeParcourt.add(rdbtnLargeur);
		grpTraces.add(rdbtnOui);
		grpTraces.add(rdbtnNon);
		
		btnQuitter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
			}
		});

		btnValider.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if("".equals(fichierBF) || "".equals(fichierBR))
				{
					textArea.setText("Veuillez saisir les fichiers BR et/ou BF");
				}
				else
				{
					if("Chainage Avant".equals(choixChainage.getSelectedItem().toString()))
					{
						ChainageAvant ca= new ChainageAvant();
						if(rdbtnProfondeur.isSelected() && rdbtnSaturation.isSelected())
						{
							//TODO chainage avant profondeur/saturation
							ca.verifiationChainageAvant();
							textArea.setText("Chainage avant en profondeur avec saturation de la BF\n\n" + ca.getResult());
						}
						if(rdbtnLargeur.isSelected() && rdbtnSaturation.isSelected())
						{
							//TODO chainage avant largeur/saturation
							System.out.println("Chainage avant en largeur avec saturation de la BF\n\n");
							textArea.setText("Chainage avant en largeur avec saturation de la BF\n\n");
						}
						if(rdbtnProfondeur.isSelected() && rdbtnBut.isSelected())
						{
							//TODO chainage avant profondeur/but
							System.out.println("Chainage avant en profondeur sans saturation de la BF\n\n");
							textArea.setText("Chainage avant en profondeur sans saturation de la BF\n\n");
						}
						if(rdbtnLargeur.isSelected() && rdbtnBut.isSelected())
						{
							//TODO chainage avant largeur/but
							System.out.println("Chainage avant en largeur sans saturation de la BF\n\n");
							textArea.setText("Chainage avant en largeur sans saturation de la BF\n\n");
						}

					}
					else
					{
						String but = JOptionPane.showInputDialog("Quel but voulez vous atteindre?");
						ChainageArriere ca = new ChainageArriere();
						
						if(rdbtnProfondeur.isSelected())
						{
							ca.chainage(but);
							textArea.setText("Chainage arri�re en profondeur\n\n" +ca.getResult());
						}
						else 
						{
							//TODO chainage arri�re largeur
							System.out.println("chainage arri�re largeur");
							textArea.setText("Chainage arri�re en largeur");
						}
					}
				}
				frame.validate ();
			}
		});
		
		
		lblTrace.setForeground(Color.BLUE);
		lblTrace.setFont(new Font("Techno Hideo", Font.PLAIN, 19));
		
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panBR, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 779, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(btnQuitter, GroupLayout.PREFERRED_SIZE, 578, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnValider, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(192)
								.addComponent(lblNewLabel))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(31)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(lblBr)
											.addComponent(lblBf, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblChainage, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblTrace, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
										.addGap(49)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(choixChainage, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(panelJtextField, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(rdbtnOui)
												.addGap(18)
												.addComponent(rdbtnNon)))
										.addGap(81)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addComponent(rdbtnProfondeur)
													.addComponent(rdbtnSaturation))
												.addGap(18)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addComponent(rdbtnBut)
													.addComponent(rdbtnLargeur)))
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(btnExplorerBR, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnExplorerBF, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))))
									.addComponent(panBF, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)))))
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnExplorerBF, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnExplorerBR, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addGap(16)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnProfondeur)
								.addComponent(rdbtnLargeur))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnSaturation)
								.addComponent(rdbtnBut)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(panelJtextField, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addGap(36))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblBf, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
									.addGap(19)
									.addComponent(lblBr, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
									.addGap(16)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblChainage, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
										.addComponent(choixChainage, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTrace, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(rdbtnOui)
								.addComponent(rdbtnNon))))
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 477, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(panBF)
							.addGap(18)
							.addComponent(panBR, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnQuitter)
						.addComponent(btnValider))
					.addContainerGap())
		);
		
		JTextArea textArea_2 = new JTextArea();
		panBR.setViewportView(textArea_2);
		
		JTextArea textArea_1 = new JTextArea();
		panBF.setViewportView(textArea_1);

		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		panelJtextField.setLayout(new BorderLayout(0, 0));
		//deblaration / initialisation des objets de la fenetre
		
		panelJtextField.add(textBF, BorderLayout.NORTH);
		panelJtextField.add(textBR, BorderLayout.SOUTH);
		frame.getContentPane().setLayout(groupLayout);
	}


	public void LoadFromFile(JFrame frame, String titre, String dossierOuverture,String extention, String whatFile)
	{
		String fichier;
		FileDialog fileDialog=new FileDialog(frame, titre, FileDialog.LOAD);
		fileDialog.setFile(extention);
		fileDialog.setDirectory(dossierOuverture);
		fileDialog.setLocation(50, 50);
		fileDialog.setDirectory(dossierOuverture);
		fileDialog.setVisible(true);
		fichier=fileDialog.getDirectory()+fileDialog.getFile();
		if("nullnull".equals(fichier))
			fichier="";
		
		if("BF".equals(whatFile))
		{
			fichierBF=fichier;
		}
		if("BR".equals(whatFile))
		{
			fichierBR=fichier;
		}
		
	}

	public static String getFichierBF() {
		return fichierBF;
	}

	public static void setFichierBF(String fichierBF) {
		MainWindow.fichierBF = fichierBF;
	}

	public static String getFichierBR() {
		return fichierBR;
	}

	public static void setFichierBR(String fichierBR) {
		MainWindow.fichierBR = fichierBR;
	}
}
