# SUMMARY

Please read and follow the directions. Please put your answers after
the italicized instructions.

Answer all the questions. Please put your answers _after_ the
italicized instructions, as shown in the
[video](https://northeastern.hosted.panopto.com/Panopto/Pages/Viewer.aspx?id=d327c168-e0e8-4f70-9f3f-b12f0048baac).

## Questions within instructions

_These two questions are worth a total of 4 points._

### Milestone 3

#### Should CachingMinimaxPlayer give the same results as MinimaxPlayer or better ones? Explain.

CachingMinimaxPlayer and MinimaxPlayer give the same results/moves, but CachingMinimaxPlayer is more efficient.
They give the same results because the caching mechanism ensures that if a state has been
encountered before during the search, the move for that state is retrieved from the transposition table rather
than recomputing it. Because this mechanism design stores and retrieves moves based on the game state and depth, it
should always return the same move for the same state and depth combination. Therefore, the moves returned from
both classes/players are the same.
The biggest difference between the two is the efficiency. While MinimaxPlayer recomputes the algorithm for
each state during the search, CachingMinimaxPlayer saves the results of these computations in a transposition table.

#### What is the timing output for the specified pairs of simulators on a size-3 board?

Minimax vs Random
Time used by Minimax (White): 88,652 μs 88 ms 0 s
Time used by Random (Black): 29 μs 0 ms 0 s

RandomMax vs Random
Time used by RandomMax (White): 72,062 μs 72 ms 0 s
Time used by Random (Black): 21 μs 0 ms 0 s

Caching Minimax vs Random
Time used by Caching Minimax (White): 20,728 μs 20 ms 0 s
Time used by Random (Black): 18 μs 0 ms 0 s

### Milestone 4

#### What is the timing output when you run the following pairs of simulators on a size-5 board?

## Summary questions

### Where can we find your work on github?

_Give the usernames of all teammates and the HTTPS URL of the repository page so we can view
it in a browser._ [1 point]

## Did you successfully implement everything that was requested, and does everything work,

to the best of your knowledge? This includes autograder tests.

_Answer "Yes", or state here which parts did not work or which tests did not pass.
This would also be the place to mention any optional milestones you completed_ [1 point]

### Did you attend the class sessions where this assignment was discussed?

_You will not be penalized for not attending class. I would just like to
know how helpful attendance was for this assignment._ [1 point]

### How long did the assignment take?

Rather than giving ranges, if you are unsure, give the averages of the ranges._
[1 point]

### Who did you work with and how?

_Discussing the assignment with people not on your team is fine as long as you
don't share code. Be sure to state how you worked together_ [1 points]

### What resources did you use?

_Please give specific URLs/questions (not just "Stack Overflow" or "ChatGPT") and
state which ones were particularly helpful._ [1 point]

### Reflections

_Give one or more paragraphs reflecting on your experience with the
assignment, including answers to all of these questions:_

* _Were the tests helpful? If so, how?_
* _What was the most difficult part of the assignment?_
* _What was the most rewarding part of the assignment?_
* _What did you learn doing the assignment?_
* _What were your favorite assignments this semester? What were your least favorites?_

_Constructive and actionable suggestions for improving assignments, office hours,
and lecture are always welcome. If there was a TA who was especially helpful,
say so._ [4 points]
