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

import javax.swing.ButtonGroup;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

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
		final JRadioButton rdbtnSaturation = new JRadioButton("Saturation");
		final JRadioButton rdbtnBut = new JRadioButton("But");
		final JRadioButton rdbtnProfondeur = new JRadioButton("Profondeur");
		final JRadioButton rdbtnLargeur = new JRadioButton("Largeur");
		final JTextArea textArea = new JTextArea();
		final JTextField textBF = new JTextField();
		textBF.setMaximumSize(new Dimension(6, 20));
		final JTextField textBR = new JTextField();
		textBR.setMaximumSize(new Dimension(6, 20));
		
		ButtonGroup grpTypeFin = new ButtonGroup();
		JLabel lblNewLabel = new JLabel("");
		JLabel lblBr = new JLabel("BR");
		JLabel lblChainage = new JLabel("Chainage ");
		
		JButton btnExplorerBF = new JButton("Explorer");
		JButton btnExplorerBR = new JButton("Explorer");
		JButton btnQuitter = new JButton("Quitter");
		JButton btnValider = new JButton("Valider");
		
		JPanel panelJtextField = new JPanel();
		JPanel panelArea = new JPanel();

		fichierBF="";
		fichierBR="";
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.setBounds(100, 100, 850, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		lblNewLabel.setIcon(new ImageIcon(MainWindow.class.getResource("/img/logo.png")));
		JLabel lblBf = new JLabel("BF");
		lblBf.setForeground(Color.BLUE);
		lblBf.setFont(new Font("Techno Hideo", Font.PLAIN, 19));

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
		choixChainage.addItem("Chainage Arrière");
		
		
		lblChainage.setForeground(Color.BLUE);
		lblChainage.setFont(new Font("Techno Hideo", Font.PLAIN, 19));

		rdbtnSaturation.setSelected(true);
		rdbtnProfondeur.setSelected(true);

		grpTypeFin.add(rdbtnSaturation);
		grpTypeFin.add(rdbtnBut);
		grpTypeParcourt.add(rdbtnProfondeur);
		grpTypeParcourt.add(rdbtnLargeur);
		
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
						if(rdbtnProfondeur.isSelected() && rdbtnSaturation.isSelected())
						{
							//TODO chainage avant profondeur/saturation
							System.out.println("chainage avant profondeur/saturation");
							textArea.setText("chainage avant profondeur/saturation");
						}
						if(rdbtnLargeur.isSelected() && rdbtnSaturation.isSelected())
						{
							//TODO chainage avant largeur/saturation
							System.out.println("chainage avant largeur/saturation");
							textArea.setText("chainage avant largeur/saturation");
						}
						if(rdbtnProfondeur.isSelected() && rdbtnBut.isSelected())
						{
							//TODO chainage avant profondeur/but
							System.out.println("chainage avant profondeur/but");
							textArea.setText("chainage avant profondeur/but");
						}
						if(rdbtnLargeur.isSelected() && rdbtnBut.isSelected())
						{
							//TODO chainage avant largeur/but
							System.out.println("chainage avant largeur/but");
							textArea.setText("chainage avant largeur/but");
						}

					}
					else
					{
						if(rdbtnProfondeur.isSelected() && rdbtnSaturation.isSelected())
						{
							//TODO chainage arrière profondeur/saturation
							System.out.println("chainage arrière profondeur/saturation");
							textArea.setText("chainage arrière profondeur/saturation");
						}
						if(rdbtnLargeur.isSelected() && rdbtnSaturation.isSelected())
						{
							//TODO chainage arrière largeur/saturation
							System.out.println("chainage arrière largeur/saturation");
							textArea.setText("chainage arrière largeur/saturation");
						}
						if(rdbtnProfondeur.isSelected() && rdbtnBut.isSelected())
						{
							//TODO chainage arrière profondeur/but
							System.out.println("chainage arrière profondeur/but");
							textArea.setText("chainage arrière profondeur/but");
						}
						if(rdbtnLargeur.isSelected() && rdbtnBut.isSelected())
						{
							//TODO chainage arrière largeur/but
							System.out.println("chainage arrière largeur/but");
							textArea.setText("chainage arrière largeur/but");
						}
					}
				}
				frame.validate ();
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnQuitter, GroupLayout.PREFERRED_SIZE, 413, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnValider, GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelArea, GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBr)
								.addComponent(lblBf, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblChainage, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
							.addGap(66)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(choixChainage, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(panelJtextField, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(rdbtnProfondeur, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(rdbtnSaturation))
											.addGap(18)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(rdbtnLargeur)
												.addComponent(rdbtnBut)))
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(btnExplorerBR, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btnExplorerBF, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBf, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnExplorerBF, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblBr, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnExplorerBR, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
						.addComponent(panelJtextField, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblChainage, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(choixChainage, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnSaturation)
								.addComponent(rdbtnBut))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnProfondeur)
								.addComponent(rdbtnLargeur))))
					.addGap(18)
					.addComponent(panelArea, GroupLayout.PREFERRED_SIZE, 398, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnValider)
						.addComponent(btnQuitter))
					.addGap(23))
		);
		panelArea.setLayout(new BorderLayout(0, 0));
		
		panelArea.add(textArea, BorderLayout.CENTER);
		

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
