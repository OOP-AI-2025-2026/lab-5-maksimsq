package ua.opnu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MainFrame extends JFrame implements ActionListener {
    private static final Random RANDOM = new Random();

    public MainFrame(String title) throws HeadlessException {
        super(title);

        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        ((JComponent) getContentPane()).setBorder(
                BorderFactory.createMatteBorder(10, 10, 10, 10, Color.WHITE));

        JButton rockButton = new JButton("Камінь");
        rockButton.addActionListener(this);
        rockButton.setActionCommand("rock");
        JButton paperButton = new JButton("Папір");
        paperButton.addActionListener(this);
        paperButton.setActionCommand("paper");
        JButton scissorsButton = new JButton("Ножиці");
        scissorsButton.addActionListener(this);
        scissorsButton.setActionCommand("scissors");

        this.add(rockButton);
        this.add(paperButton);
        this.add(scissorsButton);

        this.pack();
        this.setVisible(true);
    }

    private GameShape generateShape() {

        // TODO: написати логіку методу

        // Метод повертає об'єкт ігрової фігури (камінь, ножиці чи папір)
        // випадковим чином

        int random = RANDOM.nextInt(3);

        switch (random) {
            case 0:
                return new Rock();
                case 1:
                    return new Paper();
                    case 2:
                        return new Scissors();
                        default:
                            return new Rock(); // на всяк випадок
        }
    }

    private int checkWinner(GameShape player, GameShape computer) {

        if (player.getClass() == computer.getClass()) {
            return 0; // нічия
        }

        if (player instanceof Rock && computer instanceof Scissors) {
            return 1;
        }

        if (player instanceof Scissors && computer instanceof Paper) {
            return 1;
        }

        if (player instanceof Paper && computer instanceof Rock) {
            return 1;
        }

        return -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Генерується ход комп'ютеру
        GameShape computerShape = generateShape();


        GameShape playerShape;
        switch (e.getActionCommand()) {
            case "rock":
                playerShape = new Rock();
                break;
            case "paper":
                playerShape = new Paper();
                break;
            case "scissors":
                playerShape = new Scissors();
                break;
                default:
                    playerShape = new Rock();
        }

        // Визначити результат гри
        int gameResult = checkWinner(playerShape, computerShape);

        // Сформувати повідомлення
        String message = "Player shape: " + playerShape + ". Computer shape: " + computerShape + ". ";
        switch (gameResult) {
            case -1:
                message += "Computer has won!";
                break;
            case 0:
                message += "It's a tie!";
                break;
            case 1:
                message += "Player has won!";
                break;
            default:
                break;
        }

        // Вивести діалогове вікно з повідомленням
        JOptionPane.showMessageDialog(null, message);
    }
}
