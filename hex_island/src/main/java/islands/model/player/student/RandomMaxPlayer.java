package islands.model.player.student;

import islands.model.GameModel;
import islands.model.RowColPair;
import islands.model.TileColor;
import islands.model.player.MinimaxPlayer;

/**
 * A player that chooses the highest-scoring move based on the assumption
 * that the opponent chooses moves randomly.
 */
public class RandomMaxPlayer extends MinimaxPlayer {
    /**
     * Constructs a player that chooses the highest-scoring move based on the assumption that the
     * opponent chooses moves randomly.
     */
    public RandomMaxPlayer() {
    }

    @Override
    public String getName() {
        return "RandomMax";
    }

    // Because this only does the opponent move, it does not need to provide a move, just a value.
    @Override
    public double getOpponentValue(GameModel model, int depth, TileColor tileColor) {
        if (depth < 0) {
            throw new IllegalArgumentException("Depth cannot be negative.");
        }
        if (depth == 0 || model.isGameOver()) {
            return getValue(model, tileColor.getOpposite());
        }

        double totalValue = 0.0;
        int moveCount = 0;
        for (RowColPair position : getLegalPositions(model)) {
            GameModel newModel = model.deepCopy();
            newModel.makePlay(position.row(), position.column(), tileColor);
            totalValue += getMyMove(newModel, depth - 1, tileColor.getOpposite()).value();
            moveCount++;
        }
        if (moveCount == 0) {
            return 0.0;
        }
        return totalValue / moveCount;
    }
}
