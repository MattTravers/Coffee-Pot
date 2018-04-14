package coffeePot;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;


public class TheView extends JFrame implements Observer{
	
	private JButton buttons[];
	private Container orderMenu;
	private JPanel gridPanel = new JPanel();
	private JPanel output = new JPanel();
	private JTextField outputField = new JTextField();
	private JPanel buttonPanel = new JPanel();	
	private JPanel texts = new JPanel();

	/*
	 private JPanel displayPanel = new JPanel();
	    // JPanel displayPanel = new JPanel( new GridLayout( 4, 2 ) );
	    // JPanel displayPanel = new JPanel( new BorderLayout() );
	    // JPanel displayPanel = new JPanel( new GridBagLayout() );

	    JTextField titleText = new JTextField( "title" );

	    titleText.setPreferredSize( new Dimension( 200, 24 ) );

	    // For FlowLayout and GridLayout, uncomment:
	    displayPanel.add( titleText );
	*/

	private String drinks[] = {"Coffee", "Decafe","HotCocoa","Tea","ChickenBroth"};
	
	public TheView(){
		gridPanel.setLayout(new GridLayout(drinks.length,3));
		output.setLayout(new GridLayout(drinks.length,1));
		output.setLayout(new GridLayout(drinks.length,1));

		buttons = new JButton[drinks.length];
		output.add(outputField);
	  
	  for(int i = 0; i < drinks.length; i++) {
		  //text[i] = new JTextField("LMAO");
		  buttons[i] = new JButton(drinks[i]);
		  gridPanel.add(buttons[i]);
		  //texts.add(text[i]);
		  
	  }
	  orderMenu = this.getContentPane();
	  this.setSize( 500, 500 );
	  this.setLocation( 500, 500 );
	  Color WHITE = new Color(250,250,250);
	  orderMenu.setBackground(WHITE);
	  orderMenu.add(texts);

	  
	  orderMenu.add(output,BorderLayout.SOUTH);
	  orderMenu.add(gridPanel,BorderLayout.CENTER);
	 // orderMenu.add(empty,BorderLayout.SOUTH);

	  this.setVisible( true );
	
	}


public static void main(String[] args){
	TheView view = new TheView();	  
	}
}