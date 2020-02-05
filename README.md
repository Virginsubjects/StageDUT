# Concern Slicer

This application aims at measuring separation of concerns in texts, typically programs.
The first feature consists in colorizing texts using one color per concern.
Concerns are specified by a name, a color, a list of tokens.
The tokens are given in a file, usually one per line but layout is ignored.

Now lines are numbered and for this the output is now HMTL/CSS

This line is only suppose to appear in the line numbering branch

When the application starts it lets the user browse the local file system to select a file to be colorized.
The directory of this file must contain a concerns folder.
In the concerns folder each file lists the words of each concern, one per line.
