<h1>CritterWorldSimulation</h1>
<hr />
Project Description: 

The landscape is a 2D N x N grid of cells, where N is an input.
Initially, there are a number of critters placed randomly 
in the grid, one per cell.
Some cells may have a rock in them. The rocks never move.
A critter can move to any of its 8 neighboring cells (north,
northeast, east, southeast, south, southwest, west, northwest).
A critter may not move to a cell that's already occupied,
either by another critter or by a rock.
One particular cell in the grid will be a designated
Escape Hatch, to escape critter world. The goal is for all critters
to find this Hatch and reach a neighboring cell to the Hatch, in
which case the critter escapes.
Each critter goes through several states:
     
Initially, each critter is in the state S (for searching). A
critter in this state picks a random neighboring cell and moves there.
Thus, in this state, it wanders about randomly.
When a critter finds the Hatch, it becomes a Leader (state L).
It's job is now to find other critters to tell them where the
Hatch is (the grid coordinates of the Hatch).
Once a critter is informed by a Leader, it enters state F
(Follower) and heads towards the Hatch by moving to the
neighboring cell that's closest in the direction of the Hatch.
While in state F, it heads towards the Hatch until it's
right next to it, after which it leaves the world.
When a Leader informs one critter in state S, it looks to
see if any others are in state S and heads to towards one of them.
If there are no more critters in state S, a Leader turns
itself into a Follower (state F) and heads towards the Hatch.
It's quite possible that more than one critter becomes a
Leader, in which the time to escape will be shorter.

A simulation ends when all critters have escaped.