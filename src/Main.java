import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TicTacToeGame {
    private static final int BOARD_SIZE = 3;
    private JButton[][] buttons;
    private char currentPlayer;
    private JFrame frame;
    private int xWins;
    private int oWins;
    private int draws;
    private JLabel statusLabel;

    public TicTacToeGame() {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 350);

        buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = 'X';
        xWins = 0;
        oWins = 0;
        draws = 0;

        JPanel panel = new JPanel(new GridLayout(3, 3));

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j].setFocusPainted(false);
                final int row = i;
                final int col = j;

                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (buttons[row][col].getText().equals("")) {
                            buttons[row][col].setText(String.valueOf(currentPlayer));
                            if (checkWin(row, col)) {
                                displayWinMessage();
                            } else if (isBoardFull()) {
                                displayDrawMessage();
                            } else {
                                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                                updateStatusLabel();
                            }
                        }
                    }
                });

                panel.add(buttons[i][j]);
            }
        }

        statusLabel = new JLabel("Player X's turn");
        frame.add(panel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void displayWinMessage() {
        JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " wins!");
        if (currentPlayer == 'X') {
            xWins++;
        } else {
            oWins++;
        }
        resetBoard();
        updateStatusLabel();
    }

    private void resetBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = 'X';
    }

    private void displayDrawMessage() {
        JOptionPane.showMessageDialog(frame, "It's a draw!");
        draws++;
        resetBoard();
        updateStatusLabel();
    }

    private boolean checkWin(int row, int col) {
        // Check rows, columns, and diagonals for a win
        return checkRow(row) || checkColumn(col) || checkDiagonals();
    }

    private boolean checkRow(int row) {
        return buttons[row][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[row][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[row][2].getText().equals(String.valueOf(currentPlayer));
    }

    private boolean checkColumn(int col) {
        return buttons[0][col].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][col].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][col].getText().equals(String.valueOf(currentPlayer));
    }

    private boolean checkDiagonals() {
        return (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer))) ||
                (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                        buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                        buttons[2][0].getText().equals(String.valueOf(currentPlayer)));
    }

    private boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void updateStatusLabel() {
        statusLabel.setText("Player " + currentPlayer + "'s turn | X Wins: " + xWins + " | O Wins: " + oWins + " | Draws: " + draws);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacToeGame();
            }
        });
    }
}
