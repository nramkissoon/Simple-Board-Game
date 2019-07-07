This was an assigned project for a Data Structures class.

## Project Summary

The objective of the project was to create a board game involving a board with tiles and each tile had a number associated to it.
Players rolled a die and added the number on the tile they landed on to their total score.
If a player landed on a tile already occupied by another player, the occupant would move back 7 spaces.
If a player had a score above a certain threshold (44) and reached the end of the board, they won.
If a player reached the end of the board and their score was not above 44, they returned to the start.

#### Project Requirements

 - The board was to be represented as a doubly linked list so that players moved through the board by getting the next or previous tiles.
 - Once the game was over, the board and player locations were to be printed for every ten games.
 - The game was to be played by 1, 2, 3, and 4 players.
 - Statistics on win percentages and the amount of turns needed to win were to be provided after a set of games.
 
#### Learning Objectives

 - Effectively use a Linked List to represent a board and keep track of player locations
 - Understand how to traverse a Linked List without directly accessing its nodes
 - Practice writing classes such as Player and GameSet to keep track of statistics and data relevant to those respective classes
 - Formatting output printing of the board and data in a readable and effective manner