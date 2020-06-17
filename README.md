# Concern Slicer

This application aims at measuring separation of concerns in texts, typically programs.
The first feature consists in colorizing texts using one color per concern.
Concerns are specified by a name, a color, a list of tokens.
The tokens are given in a file, usually one per line but layout is ignored.

A token is the smallest element of a program that is meaningful to the compiler.

More about [Tokens](https://www.edureka.co/blog/tokens-in-java/)

Now lines are numbered and for this the output is now HMTL/CSS.

This line is only suppose to appear in the line numbering branch.

## Get started

So the first aim of this Java application is to colorate differents parts from a code, the result will be  a webpage in HTML.
Besides, to format its layout and most of all control the colors we do not will use CSS, because too much complex and not very usefull in this case.

We'll set colors directly within a generic inline container like HTML **span** element, wich is more suitable than a *div* element, thereby it can be used to group elements for styling purposes, like add colors wich is what are we are looking for.
Besides an inline element is more flexible and appropriate for use here than a block-level element like *div*.

*Syntactic coloration* aims to automatically formatting some items (tokens) from a text or a code, 
using a specific color, items have upstream been carefully choosen due to their importance 
and their interest to a concern.    

Thereby, highlighted elements from a code allow us to spot more easily program constituents, 
so that they can be analyzed more efficiently.

His goal, among others,  is a code  legibility improvement.


## How it works

When the application starts it lets the user browse the local file system so that he selects a file which will be colorized.
The directory of this file must contain a concerns folder.
In the concerns folder each file lists the words of each concern, one per line. 

However at the top each file, at the very first line, it must contains the name of one color. This one will be used for all the words attached to this concern.

### Choose up right colors

When using only your navigator to displays html content, you can choose whatever color you want, but unfortunately if you are using GUI not all of them are allowed.

Indeed in most of cases, it's fairly probably that the html content will show up without a color treatement. You must to pay attention what color is choosen.

We can only use 16 colors, those were defined in the HTML 4.01 specification, only those are working well using our GUI, as  follows (names are defined in this context to be not case-insensitive):

![Capture d’écran (85)](https://user-images.githubusercontent.com/56639090/84779404-a68ec480-afe4-11ea-8144-12d9dadbac2d.png)


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

## Fixing StreamTokenizer issues

The StreamTokenizer class takes an input stream and parses it into "tokens", allowing the tokens to be read one at a time. The parsing process is controlled by a table and a number of flags that can be set to various states. 

The stream tokenizer can recognize identifiers, numbers, quoted strings, and various comment styles.

More about  [Class StreamTokenizer](https://docs.oracle.com/javase/7/docs/api/java/io/StreamTokenizer.html) 

However we've noticed that when a string contains one diacritic sign, this class tends to split it and broke up in several strings, for instance : "ça" which contains "ç" will be split in two strings : "ç", "a", what happened here? 

We was able to fix this issue, therefore now the html page will dislplays correctly, 
provided you use the right colors! 

>Indeed Using proper colors, it seems that html content is displaying correctly.

![image](https://user-images.githubusercontent.com/56639090/84497310-d3726d00-acae-11ea-9991-241f0f8e77a6.png)

## Authors 
- __Mikal Ziane__ - *Initial work* -
- __Roberto Collantes__
