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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TheView extends JFrame implements Observer {
	// non-view attributes
	private TheController controller;
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
	private JPanel ingredientSelection = new JPanel();

	private JLabel OUTPUT = new JLabel("OUTPUT");
	private JLabel BALANCE = new JLabel("BALANCE");
	private JLabel COINSLOT = new JLabel("   COIN SLOT");

	private JTextField outputField = new JTextField(32);
	private JTextField balanceField = new JTextField("$0.00", 8);

	private String coins[] = { "penny", "nickel", "dime", "quarter","dollar","five"};

	// attributes for ingredient menu
	private JButton ingriedentPlusButtons[];
	private JButton ingriedentMinusButtons[];
	private JTextField ingredientAmount[];
	private JLabel ingredientName[];
	private JPanel ingredientPanel[];

	WindowListener windowListener = new WindowAdapter() {

		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	};

	public TheView() {
	}

	/// MAIN INSTANTIATING RUN///
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

	/// Make Drinks Menu///
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

	/// Make Ingredients Menu///
	public void makeIngredientsMenu(ArrayList<Ingredient> ingredients) {

		orderWindow.invalidate();
		orderWindow.validate();

		JButton submit = new JButton("SUBMIT");
		JButton cancel = new JButton("CANCEL");

		submit.addActionListener(controller.submit(submit));
		cancel.addActionListener(controller.cancel(cancel));

		ingriedentPlusButtons = new JButton[ingredients.size()];
		ingriedentMinusButtons = new JButton[ingredients.size()];
		ingredientAmount = new JTextField[ingredients.size()];
		ingredientName = new JLabel[ingredients.size()];
		ingredientPanel = new JPanel[ingredients.size()];
		ingredientSelection.add(new JPanel());

		for (int i = 0; i < ingredients.size(); i++) {
			ingredientName[i] = new JLabel(ingredients.get(i).getName());
			ingredientName[i].setFont(new Font("SansSerif", Font.BOLD, 14));
			ingredientAmount[i] = new JTextField("0", 4);
			ingredientAmount[i].setEnabled(false);
			ingredientAmount[i].setFont(new Font("SansSerif", Font.BOLD, 14));
			ingredientAmount[i].setHorizontalAlignment(JTextField.CENTER);
			ingriedentMinusButtons[i] = new JButton("-");
			ingriedentPlusButtons[i] = new JButton("+");
			ingriedentPlusButtons[i].addActionListener(
					controller.incrementIngredient(ingriedentPlusButtons[i], ingredientName[i].getText()));
			ingriedentMinusButtons[i].addActionListener(
					controller.decrementIngredient(ingriedentMinusButtons[i], ingredientName[i].getText()));
			ingredientPanel[i] = new JPanel();
			ingredientPanel[i].add(ingredientName[i]);
			ingredientPanel[i].add(ingredientAmount[i]);
			ingredientPanel[i].add(ingriedentPlusButtons[i]);
			ingredientPanel[i].add(ingriedentMinusButtons[i]);
			ingredientSelection.add(ingredientPanel[i]);
		}
		JPanel submitPanel = new JPanel();
		submitPanel.add(cancel);
		submitPanel.add(submit);

		ingredientSelection.add(submitPanel);
		ingredientSelection.setLayout(new GridLayout(ingredientPanel.length + 3, 0));

		changeToIngredients();

	}

	public void changeToDrinks() {
		orderWindow.getContentPane().remove(ingredientSelection);
		ingredientSelection.removeAll();
		orderWindow.invalidate();
		orderWindow.validate();
		orderWindow.add(drinkSelection, BorderLayout.CENTER);
		orderWindow.invalidate();
		orderWindow.validate();
		orderWindow.setSize(501, 501);

	}

	public void changeToIngredients() {
		orderWindow.getContentPane().remove(drinkSelection);
		drinkSelection.removeAll();
		orderWindow.invalidate();
		orderWindow.validate();
		orderWindow.getContentPane().remove(drinkSelection);
		orderWindow.add(ingredientSelection, BorderLayout.CENTER);
		orderWindow.invalidate();
		orderWindow.validate();
		orderWindow.setSize(500, 500);

	}

	public void updateIngredient(int n, String ingredient) {
		for (int i = 0; i < this.ingredientName.length; i++) {
			if (this.ingredientName[i].getText().equals(ingredient)) {
				this.ingredientAmount[i].setText("" + n);
			}
		}
	}

	public void updateOutput(String string) {
		outputField.setText(string);
	}

	@Override
	public void updateBalance(String string) {
		balanceField.setText(string);
	}

	public void setDrinkMenu(ArrayList<Drink> drinkMenu) {
		this.drinkMenu = drinkMenu;
	}

	public void setController(TheController controller) {
		this.controller = controller;
	}

}