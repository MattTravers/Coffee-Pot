package coffeePot;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * The View class creates a GUI for a coffee pot.
 *
 */
public class View extends JFrame implements Observer {
	// non-view attributes
	private Controller controller;
	private ArrayList<Drink> drinkMenu;

	// view attributes
	private JButton drinkButtons[];
	private JButton coinButtons[];
	private JButton returnCoin = new JButton("RETURN");

	private JFrame orderWindow = new JFrame();

	private Container orderMenu;

	private JPanel coinSelection = new JPanel();
	private JPanel drinkSelection = new JPanel();
	private JPanel outputSection = new JPanel();
	private JPanel balanceSection = new JPanel();
	private JPanel condimentSelection = new JPanel();

	private JLabel OUTPUT = new JLabel("OUTPUT");
	private JLabel BALANCE = new JLabel("BALANCE");
	private JLabel COINSLOT = new JLabel("   COIN SLOT");

	private JTextField outputField = new JTextField(32);
	private JTextField balanceField = new JTextField("$0.00", 8);

	private String coins[] = { "nickel", "dime", "quarter", "dollar", "five" };

	// attributes for condiment menu
	private JButton condimentPlusButtons[];
	private JButton condimentMinusButtons[];
	private JTextField condimentAmount[];
	private JLabel condimentName[];
	private JPanel condimentPanel[];

	WindowListener windowListener = new WindowAdapter() {

		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	};

	public View() {
	}

	/**
	 * initiates the view
	 */
	public void run() {
		drinkSelection.setLayout(new GridLayout(drinkMenu.size(), 3));

		balanceField.setEnabled(false);
		balanceField.setFont(new Font("SansSerif", Font.BOLD, 14));
		balanceField.setHorizontalAlignment(JTextField.CENTER);
		outputField.setEnabled(false);
		outputField.setFont(new Font("SansSerif", Font.BOLD, 14));
		outputField.setHorizontalAlignment(JTextField.CENTER);

		coinButtons = new JButton[coins.length];

		// implement coin buttons
		coinSelection.add(COINSLOT);

		for (int i = 0; i < coins.length; i++) {
			coinButtons[i] = new JButton(coins[i]);
			coinButtons[i].addActionListener(controller.addBalance(coinButtons[i]));
			coinSelection.add(coinButtons[i]);
		}

		returnCoin.addActionListener(controller.returnBalance(returnCoin));
		coinSelection.add(returnCoin);

		outputSection.add(OUTPUT);
		outputSection.add(outputField);
		balanceSection.add(BALANCE);
		balanceSection.add(balanceField);

		orderMenu = orderWindow.getContentPane();
		orderWindow.setTitle("Coffee Pot");
		orderWindow.setSize(500, 500);
		orderWindow.setLocation(700, 200);

		coinSelection.setLayout(new GridLayout(10, 10));

		makeDrinksMenu();
		orderMenu.add(outputSection, BorderLayout.SOUTH);
		orderMenu.add(balanceSection, BorderLayout.NORTH);
		orderMenu.add(coinSelection, BorderLayout.WEST);

		orderWindow.addWindowListener(windowListener);

		orderWindow.setVisible(true);
	}

	/**
	 * makes the drink menu
	 */
	public void makeDrinksMenu() {

		orderWindow.invalidate();
		orderWindow.validate();

		drinkButtons = new JButton[drinkMenu.size()];

		for (int i = 0; i < drinkMenu.size(); i++) {
			drinkButtons[i] = new JButton(drinkMenu.get(i).getName());
			drinkButtons[i].addActionListener(controller.drinkSelect(drinkButtons[i]));
			drinkButtons[i].setFont(new Font("SansSerif", Font.BOLD, 20));
			drinkSelection.add(drinkButtons[i]);
		}

		changeToDrinks();

	}

	/**
	 *  Makes the condiment Menu
	 */
	public void makeCondimentMenu(ArrayList<Condiment> condiment) {

		orderWindow.invalidate();
		orderWindow.validate();

		JButton submit = new JButton("SUBMIT");
		JButton cancel = new JButton("CANCEL");

		submit.addActionListener(controller.submit(submit));
		cancel.addActionListener(controller.cancel(cancel));

		condimentPlusButtons = new JButton[condiment.size()];
		condimentMinusButtons = new JButton[condiment.size()];
		condimentAmount = new JTextField[condiment.size()];
		condimentName = new JLabel[condiment.size()];
		condimentPanel = new JPanel[condiment.size()];
		condimentSelection.add(new JPanel());

		for (int i = 0; i < condiment.size(); i++) {
			condimentName[i] = new JLabel(condiment.get(i).getName());
			condimentName[i].setFont(new Font("SansSerif", Font.BOLD, 14));
			condimentAmount[i] = new JTextField("0", 4);
			condimentAmount[i].setEnabled(false);
			condimentAmount[i].setFont(new Font("SansSerif", Font.BOLD, 14));
			condimentAmount[i].setHorizontalAlignment(JTextField.CENTER);
			condimentMinusButtons[i] = new JButton("-");
			condimentPlusButtons[i] = new JButton("+");
			condimentPlusButtons[i].addActionListener(
					controller.incrementCondiment(condimentPlusButtons[i], condimentName[i].getText()));
			condimentMinusButtons[i].addActionListener(
					controller.decrementCondiment(condimentMinusButtons[i], condimentName[i].getText()));
			condimentPanel[i] = new JPanel();
			condimentPanel[i].add(condimentName[i]);
			condimentPanel[i].add(condimentAmount[i]);
			condimentPanel[i].add(condimentPlusButtons[i]);
			condimentPanel[i].add(condimentMinusButtons[i]);
			condimentSelection.add(condimentPanel[i]);
		}
		JPanel submitPanel = new JPanel();
		submitPanel.add(cancel);
		submitPanel.add(submit);

		condimentSelection.add(submitPanel);
		condimentSelection.setLayout(new GridLayout(condimentPanel.length + 3, 0));

		changeToCondiments();

	}

	/**
	 * changes display view to drink selection
	 */
	public void changeToDrinks() {
		orderWindow.getContentPane().remove(condimentSelection);
		condimentSelection.removeAll();
		orderWindow.invalidate();
		orderWindow.validate();
		orderWindow.add(drinkSelection, BorderLayout.CENTER);
		orderWindow.invalidate();
		orderWindow.validate();
		orderWindow.setSize(501, 501);

	}

	/**
	 * changes display view to condiment selection
	 */
	public void changeToCondiments() {
		orderWindow.getContentPane().remove(drinkSelection);
		drinkSelection.removeAll();
		orderWindow.invalidate();
		orderWindow.validate();
		orderWindow.getContentPane().remove(drinkSelection);
		orderWindow.add(condimentSelection, BorderLayout.CENTER);
		orderWindow.invalidate();
		orderWindow.validate();
		orderWindow.setSize(500, 500);

	}

	/** 
	 *  updates the display amount for the condiment
	 */
	public void updateCondiment(int n, String condiment) {
		for (int i = 0; i < this.condimentName.length; i++) {
			if (this.condimentName[i].getText().equals(condiment)) {
				this.condimentAmount[i].setText("" + n);
			}
		}
	}

	/**
	 *  updates the output string
	 */
	public void updateOutput(String string) {
		outputField.setText(string);
	}

	@Override
	public void updateBalance(String string) {
		balanceField.setText(string);
	}
 
	// setter methods
	public void setDrinkMenu(ArrayList<Drink> drinkMenu) {
		this.drinkMenu = drinkMenu;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

}