package buscaminas;

import java.awt.EventQueue;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainClass extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int filas=0 , columnas=0;
	private JPanel contentPane;
	static int tablero[][];
	static int h,l;
	static JButton[][] botones;
	public static void main(String[] args) {
		rellenamosLasMinas();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainClass frame = new MainClass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainClass() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		botones=new JButton[filas][columnas];
		GridLayout gl_contentPane = new GridLayout(filas, columnas);
		contentPane.setLayout(gl_contentPane);
		for(h=0;h<tablero.length;h++) {
			for(l=0;l<tablero[h].length;l++) {
				botones[h][l]=new JButton(" ");
				add(botones[h][l]);
				botones[h][l].addActionListener(new ActionListener() {
				
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						if(tablero[h][l]==1) {
							ImageIcon mina=new ImageIcon(this.getClass().getResource("/buscaminas/mina.png"));
							Icon i1=new ImageIcon(mina.getImage().getScaledInstance(botones[h][l].getWidth(), botones[h][l].getHeight(), Image.SCALE_DEFAULT));
							botones[h][l].setIcon(i1);
						}
					}
				});
			}
		}
	}

	static public void rellenamosLasMinas() {
		Scanner sc = new Scanner(System.in);
		while (filas == 0 || columnas == 0) {
			System.out.println("Redimensiona el tablero con dos numeros enteros (Filas x columnas)");
			System.out.println("Introduce el numero de filas:");
			if (sc.hasNextInt()) {
				filas = sc.nextInt();
			} else {
				System.out.println("Numero no reconocido");
			}
			System.out.println("Introduce el numero de columnas");
			if (sc.hasNextInt()) {
				columnas = sc.nextInt();
			} else {
				System.out.println("Numero no reconocido");
			}
		}
		sc.close();
		 tablero = new int[filas][columnas];
		double azarFilas = 0;
		double azarColumnas = 0;
		int numeroDeMinas = (filas + columnas) / 3;
		for (int i = 0; i < tablero.length; i++)
			for (int j = 0; j < tablero[i].length; j++)
				tablero[i][j] = 0;

		do {
			azarFilas = Math.random() * filas;
			azarColumnas = Math.random() * columnas;
			azarFilas = (int) azarFilas;
			azarColumnas = (int) azarColumnas;
			if (azarColumnas != 0 && azarFilas != 0 && azarColumnas != columnas - 1 && azarFilas != filas - 1) {
				tablero[(int) azarFilas][(int) azarColumnas] = 1;
				numeroDeMinas--;
			}
		} while (numeroDeMinas > 0);

	
	}
}
