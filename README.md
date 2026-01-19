# High Suit Game

Java console card game.

## Rules
Goal: Get the highest total score after 1–3 rounds by building the best scoring single suit in your hand.
Players: 1–2 players. If a player name is "Computer", that player is AI-controlled.
Deck / Deal: Standard 52-card deck. Each round, each player is dealt 5 cards.
Card values:
- 2–10 = face value
- J = 11, Q = 12, K = 13, A = 14
Scoring (per round):
- Add up the values of cards by suit (Hearts, Diamonds, Clubs, Spades).
- Your round score is the highest total from any one suit in your hand.
Bonus suit:
- At the start of each round, each player chooses a bonus suit.
- If your highest scoring suit matches your chosen bonus suit, you get +5 points.
Swaps:
- Each round, each player may swap 0–4 cards.
- Cards are labelled A–E; selected cards are replaced from the deck.
Final result:
- Round scores are added into a total score.
- Final scores are displayed in descending order, and the highest total wins.
High Score Table:
- Stores the top 5 scores for the session using average score:
- total score ÷ number of rounds.
Replay:
- After a game ends, players can optionally view a replay showing key actions/results from each round.

## Run
1. Download the repo
2. Open a terminal in the project folder
3. Run:

```bash
cd src
javac *.java
java HighSuitGame

