import java.util.Random;

class RockPaperScissors {

    static String playRound(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return "Draw";
        }
        if ((playerMove.equals("Rock") && computerMove.equals("Scissors"))
                || (playerMove.equals("Paper") && computerMove.equals("Rock"))
                || (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            return "Player Wins";
        }
        return "Computer Wins";
    }

    public static void main(String[] args) {
        String[] moves = {"Rock", "Paper", "Scissors"};
        String[] playerMoves = {"Rock", "Paper", "Scissors", "Scissors", "Rock"};

        Random random = new Random();
        int rounds = playerMoves.length;

        String[] computerMoves = new String[rounds];
        String[] results = new String[rounds];

        int wins = 0, losses = 0, draws = 0;

        for (int i = 0; i < rounds; i++) {
            computerMoves[i] = moves[random.nextInt(moves.length)];
            results[i] = playRound(playerMoves[i], computerMoves[i]);

            if (results[i].equals("Player Wins")) {
                wins++;
            } else if (results[i].equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
        }

        System.out.println("Round | Player Move | Computer Move | Result");
        for (int i = 0; i < rounds; i++) {
            System.out.println((i + 1) + " | " + playerMoves[i] + " | " + computerMoves[i] + " | " + results[i]);
        }

        double winPercentage = (wins / (double) rounds) * 100;
        System.out.println("Wins: " + wins + " | Losses: " + losses + " | Draws: " + draws
                + " | Win % = " + String.format("%.1f", winPercentage) + "%");
    }
}
