# Concern Slicer

This application aims at measuring separation of concerns in texts, typically programs.
The first feature consists in colorizing texts using one color per concern.
Concerns are specified by a name, a color, a list of tokens.
The tokens are given in a file, usually one per line but layout is ignored.

Now lines are numbered and for this the output is now HMTL/CSS

This line is only suppose to appear in the line numbering branch

## How it works

When the application starts it lets the user browse the local file system so that he selects a file which will be colorized.
The directory of this file must contain a concerns folder.
In the concerns folder each file lists the words of each concern, one per line. 

However at the top each file, at the very first line, it must contains the name of one color, written in uppercase. This one will be used for all the words attached to this concern.

## Choose up right colors

When using only your navigator to displays html content, you can choose whatever color you want, but unfortunately if you are using GUI not all of them are allowed.

Indeed in most of cases, it's fairly probably that the html content will show up without a color treatement. You must to pay attention what color is choosen, for instance some colors who are actually working well are red, green, grey, yellow, orange and so on. 


## Quick overview

When user selects a file, html colorized file shows up on the right from the frame, however
into this new file where colorization has been carried out, it seems that there would have been some
inexpected outcomings.

>On the picture down bellow, it show us so far what is going wrong (not color treatement because of using unallowed colors)
 
![Overview](https://user-images.githubusercontent.com/56639090/83023498-48963f00-a02d-11ea-9e7f-b1297ad37b0f.png)

- On the left screenshot : using Java Swing (Our GUI) :thumbsdown:                                
- On the right screenshot : using a regular navigator (Chrome) :thumbsup: 

Even though it seems to be working well in a regular navigator like Chrome, we'll face to this point
and focalize all our attention to fix it, in order to displays correctly in our application.

## Fixing color displays issues

>Using proper colors, it seems that html content is displaying correctly.

![Capture d’écran (69)](https://user-images.githubusercontent.com/56639090/83869388-cd1f4680-a72c-11ea-9ca7-a9796f644545.png)
