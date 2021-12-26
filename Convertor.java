import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Convertor {
	private JFrame frame;
	private JPanel panel;
	private JTextField original, result;
	private final JComboBox<String> from, to;
	private JButton swap, convert;
	private Convertor() {
		String[] systems = { "Binary", "Octal", "Decimal", "Hexadecimal" };
		frame = new JFrame();
		panel = new JPanel();
		original = new JTextField();
		result = new JTextField();
		from = new JComboBox<String>(systems);
		to = new JComboBox<String>(systems);
		swap = new JButton("Swap Conversion");
		convert = new JButton("Convert");
		swap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tmp1 = from.getSelectedItem().toString();
				from.setSelectedItem(to.getSelectedItem());
				to.setSelectedItem(tmp1);
				if (result.getText() != "" && result.getText() != "Invalid value!") {
					String tmp2 = original.getText();
					original.setText(result.getText());
					result.setText(tmp2);
				} else if(result.getText() == "Invalid value!")
					result.setText("");
			}
		});
		convert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result.setText(convert(from.getSelectedItem().toString(), to.getSelectedItem().toString()));
			}
		});
		panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		panel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		Font font = new Font("SansSerif", Font.PLAIN, 18); // Segue UI
		Object[] obj = { original, result, from, to, swap, convert };
		int[][] pos = { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 }, { 0, 2 }, { 1, 2 } };
		for (int i = 0; i < obj.length; i++) {
			if(i == 0 || i % 2 == 0)
				constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridx = pos[i][0];
			constraints.gridy = pos[i][1];
			int top = 0, left = 0;
			if (pos[i][1] > 0)
				top = 15;
			if (pos[i][0] > 0)
				left = 50;
			constraints.insets = new Insets(top, left, 0, 0);
			((Component) obj[i]).setPreferredSize(new Dimension(200, 30));
			((Component) obj[i]).setFont(font);
			panel.add((Component) obj[i], constraints);
		}
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Numerical System Convertor");
		frame.pack();
		frame.setVisible(true);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - frame.getWidth()) / 2;
		int y = (screenSize.height - frame.getHeight()) / 2;
		frame.setLocation(x, y);
	}
	private String convert(String from, String to) {
		int number;
		String value;
		try {
			switch(from) {
				case "Binary":
					number = Integer.parseInt(original.getText(), 2);
					break;
				case "Octal":
					number = Integer.parseInt(original.getText(), 8);
					break;
				case "Hexadecimal":
					number = Integer.parseInt(original.getText(), 16);
					break;
				default:
					number = Integer.parseInt(original.getText());
			}
		} catch (Exception e){
			return "Invalid value!";
		}
		switch(to) {
				case "Binary":
					value = Integer.toBinaryString(number);
					break;
				case "Octal":
					value = Integer.toOctalString(number);
					break;
				case "Hexadecimal":
					value = Integer.toHexString(number);
					break;
				default:
					value = Integer.toString(number);
		}
		return value;
	}
	public static void main(String[] args) {
		new Convertor();
	}
}