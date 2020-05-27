# Concern Slicer

This application aims at measuring separation of concerns in texts, typically programs.
The first feature consists in colorizing texts using one color per concern.
Concerns are specified by a name, a color, a list of tokens.
The tokens are given in a file, usually one per line but layout is ignored.

Now lines are numbered and for this the output is now HMTL/CSS

This line is only suppose to appear in the line numbering branch

When the application starts it lets the user browse the local file system so that he selects a file which will be colorized.
The directory of this file must contain a concerns folder.
In the concerns folder each file lists the words of each concern, one per line.

When user selects a file, html colorized file shows up on the right from the frame, however
into this new file where colorized has been carried out, it seems that there has would been some
inexpected outcomings.

Even though it works in a regular navigator like Chrome, we'll face to this point
and focalize all our attention to fix it, in order to displays correctly in our application.