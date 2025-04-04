package islands.model.player.student;

import islands.model.GameModel;
import islands.model.Move;
import islands.model.TileColor;
import islands.model.player.MinimaxPlayer;

/**
 * A player that uses caching to improve on the minimax algorithm.
 *
 * @see TranspositionTable
 */
public class CachingMinimaxPlayer extends MinimaxPlayer {
    private final TranspositionTable transpositionTable;

    /**
     * Constructs a caching minimax player.
     */
    public CachingMinimaxPlayer() {
        this.transpositionTable = new TranspositionTable();
    }

    @Override
    public String getName() {
        return "Caching Minimax";
    }

    @Override
    public Move getMyMove(GameModel model, int depth, TileColor tileColor) {
        // Check if the move is in the table
        if (transpositionTable.hasMove(model, depth)) {
            return transpositionTable.getMove(model, depth);
        } else {
            // If not, call MinimaxPlayer's getMyMove() method
            Move move = super.getMyMove(model, depth, tileColor);
            // Add the result to the table
            transpositionTable.putMove(model, depth, move);
            // Return the result
            return move;
        }
    }
}
