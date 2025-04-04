package islands.model.player.student;

import islands.model.GameModel;
import islands.model.Move;

import java.util.*;

/**
 * A table for storing and retrieving the results of calls to {@link
 * islands.model.player.SimulatedGameTreePlayer#getMyMove(GameModel, int, islands.model.TileColor)}.
 */
public class TranspositionTable {
    // A record is the Java equivalent of a Kotlin data class.
    private record CachedInfo(int depth, Move move) {
    }

    private final Map<String, CachedInfo> cachedMoves = new HashMap<>();

    /**
     * Constructs an empty transposition table.
     */
    public TranspositionTable() {
    }

    /**
     * Records that calling {@link
     * islands.model.player.SimulatedGameTreePlayer#getMyMove(GameModel, int, islands.model.TileColor)}
     * with the given model and depth produced the specified move.
     *
     * @param model the model
     * @param depth the depth
     * @param move  the move
     */
    public void putMove(GameModel model, int depth, Move move) {
        // Make an addition to the hash table.
        // The key should be the model's board string.
        // The value should be an instance of Value with the depth and move.

        // Add the transformed board and move to the hash table.
        String boardString = model.getBoardString();
        CachedInfo cachedInfo = new CachedInfo(depth, move);
        cachedMoves.put(boardString, cachedInfo);
        putTransformation(model.getSize(), boardString, cachedInfo);
    }

    // Adds entry for the board rotated 180 degrees.
    private void putTransformation(int size, String boardString, CachedInfo cachedInfo) {
        // Generate the board string of the 180-degree rotation of
        // the current model, using its board string. While board strings
        // are not arrays, you can get any row and column position of
        // boardString by calling the helper method getTileChar().

        // You will have to build up the other board string one character
        // at a time using the translation formula provided on Canvas:
        //   (row, col) ---> (size - 1 - row, size - 1 - col)
        // Don't forget to add a newline character at the end of each row.

        // In addition to transforming the board, you need to use the same formula
        // to translate the move. Add the new board and move to the hash table.


        StringBuilder transformedBoard = new StringBuilder();
        Move originalMove = cachedInfo.move();
        int originalRow = originalMove.row();
        int originalCol = originalMove.col();
        int transformedRow = size - 1 - originalRow; // Adjust row index for transformation
        int transformedCol = size - 1 - originalCol; // Adjust column index for transformation
        for (int row = size - 1; row >= 0; row--) {
            for (int col = size - 1; col >= 0; col--) {
                char tileChar = getTileChar(size, boardString, row, col); // Reverse row and column indices
                transformedBoard.append(tileChar);
            }
            transformedBoard.append('\n'); // Add newline character after each row
        }
        String transformedBoardString = transformedBoard.toString();
        Move transformedMove = new Move(transformedRow, transformedCol, originalMove.value());
        CachedInfo transformedCachedInfo = new CachedInfo(cachedInfo.depth(), transformedMove);
        cachedMoves.put(transformedBoardString, transformedCachedInfo);
    }

    private static char getTileChar(int size, String boardString, int row, int col) {
        int offset = row * (size + 1) + col; // + 1 for the newline character in boardString
        return boardString.charAt(offset);
    }

    /**
     * Checks whether this table has the move recommended for this model
     * (or its transformations) when searching to the specified depth or
     * deeper.
     *
     * @param model the model
     * @param depth the minimum search depth
     * @return true if a move is available, false otherwise
     */
    public boolean hasMove(GameModel model, int depth) {
        // Check if the hash table has an entry with the model's board string as the key
        // and that the associated value includes a depth greater than or equal
        // to the passed depth.
        String boardString = model.getBoardString();
        if (!cachedMoves.containsKey(boardString)) {
            return false;
        }
        CachedInfo cachedInfo = cachedMoves.get(boardString);
        return cachedInfo.depth() >= depth;
    }

    /**
     * Gets the stored move for this model computed to the given depth or
     * deeper.
     *
     * @param model the model
     * @param depth the depth
     * @return the stored move
     * @throws NoSuchElementException if this table does not have an entry
     *                                with the specified model with a depth
     *                                greater than or equal to the
     *                                requested depth
     */
    public Move getMove(GameModel model, int depth) {
        String boardString = model.getBoardString();
        if (!hasMove(model, depth)) {
            throw new NoSuchElementException("No move found for the specified model and depth.");
        }
        return cachedMoves.get(boardString).move();
    }
}
