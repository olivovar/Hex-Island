package islands.model.player.student;

import islands.model.*;
import islands.model.player.MinimaxPlayer;

/**
 * A player applying alpha-beta pruning to the minimax algorithm.
 */
public class AlphaBetaPlayer extends MinimaxPlayer {
    private final TranspositionTable table = new TranspositionTable();

    /**
     * Constructs sa simulated player using alpha-beta pruning.
     */
    public AlphaBetaPlayer() {
    }

    @Override
    public String getName() {
        return "Alpha-Beta";
    }

    /**
     * Chooses the next move, searching to the specified depth.
     *
     * @param model     the model
     * @param depth     the depth to search
     * @param tileColor the tile color of the current player
     * @return the move
     */
    public RowColPair chooseNextMove(GameModel model, int depth, TileColor tileColor) {
        // The initial value of alpha is -Double.MAX_VALUE because
        // Double.MIN_VALUE is the smallest *positive* double value.
        Move move = getMyMove(model, depth, -Double.MAX_VALUE, Double.MAX_VALUE, tileColor);
        return move.getPosition();
    }

    @Override
    public Move getMyMove(GameModel model, int depth, TileColor tileColor) {
        return getMyMove(model, depth, -Double.MAX_VALUE, Double.MAX_VALUE, tileColor);
    }

    @Override
    public double getOpponentValue(GameModel model, int depth, TileColor
            tileColor) {
        return getOpponentValue(model, depth, -Double.MAX_VALUE, Double.MAX_VALUE, tileColor);
    }

    // This doesn't override the ordinary getMyMove() method because it adds
    // alpha and beta parameters.
    private Move getMyMove(GameModel model, int depth, double alpha, double beta, TileColor tileColor) {
        if (depth <= 0 || model.isGameOver()) {
            return new Move(getValue(model, tileColor));
        }

        Move bestMove = null;
        for (RowColPair position : getLegalPositions(model)) {
            GameModel newModel = model.deepCopy();
            newModel.makePlay(position.row(), position.column(), tileColor);
            double childValue = getOpponentValue(newModel, depth - 1, alpha, beta, tileColor.getOpposite());

            // Update the best move if necessary
            if (bestMove == null || childValue > bestMove.value()) {
                bestMove = new Move(position.row(), position.column(), childValue);
            }

            // Update alpha
            alpha = Math.max(alpha, childValue);

            // Perform alpha-beta pruning
            if (alpha >= beta) {
                break;
            }
        }
        return bestMove;
    }

    // This doesn't override the ordinary getMyMove() method because it adds
    // alpha and beta parameters.
    private double getOpponentValue(GameModel model, int depth, double alpha, double beta, TileColor tileColor) {
        if (depth <= 0 || model.isGameOver()) {
            return getValue(model, tileColor);
        }

        double bestValue = Double.MAX_VALUE;
        for (RowColPair position : getLegalPositions(model)) {
            GameModel newModel = model.deepCopy();
            newModel.makePlay(position.row(), position.column(), tileColor);
            double childValue = getMyMove(newModel, depth - 1, alpha, beta, tileColor.getOpposite()).value();
            bestValue = Math.min(bestValue, childValue);
            beta = Math.min(beta, bestValue);
            if (alpha >= beta) {
                break; // Beta cutoff
            }
        }
        return bestValue;
    }
}
