package concernSlicerGUI;

import java.io.*;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.filechooser.*;
import javax.swing.text.html.HTMLEditorKit;

import concernSlicer.ConcernSlicer;


@SuppressWarnings("serial")
public class ConcerSlicerGUI extends JPanel
                             implements ActionListener{
    static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;
    JEditorPane pane;
    
    public ConcerSlicerGUI() {
        super(new BorderLayout());
        pane = new JEditorPane();
        pane.setEditable(true);
        pane.getDocument().putProperty("IgnoreCharsetDirective", Boolean.TRUE);
        pane.setPreferredSize(new Dimension(400, 400));
        
        log = new JTextArea(20,10);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
        JScrollPane paneScrollPane = new JScrollPane(pane);
        
       //Create a file chooser
        fc = new JFileChooser();
        
        openButton = new JButton("Select the File to be colorized");
        saveButton = new JButton("Sauvegarder");
        openButton.addActionListener(this);
        saveButton.addActionListener(this);
        
        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel(); //use FlowLayout       
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
    

        //Add the buttons and the log to this panel. ( + pane )
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
        add(paneScrollPane, BorderLayout.EAST);              
    }

    public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(ConcerSlicerGUI.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                log.append("Opening: " + file.getAbsolutePath() + "." + newline);
                try {
					ConcernSlicer.colorize(file);				
					String codeDir = "file:"+file.getParentFile().getAbsolutePath();				
					String colorized = codeDir + "\\colorized.html";
					System.out.println(colorized);
					pane.setPage(colorized);				
				  //readTextFile(log,file.getParentFile()+"\\colorized.html");
				} catch (IOException e1) {
					log.append("IOException : "+e1.getMessage()+ newline);
				}
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());

        //Handle save button action.
        } else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(ConcerSlicerGUI.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would save the file.
                log.append("Saving: " + file.getName() + "." + newline);
            } else {
                log.append("Save command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ConcerSlicerGUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Concern Slicer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new ConcerSlicerGUI());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
   /* private void readTextFile(JTextArea texte, String fileName) 
 	{
 		try 
 			{
  			BufferedReader inStream  
      				= new BufferedReader (new FileReader(fileName));
 			String line = inStream.readLine();  
 		 	while (line != null)
 		 	 {                        
     	       texte.append(line + "");                
		      line = inStream.readLine();                  
  			}
   			inStream.close();                              
  			} catch (Exception e) 
  				{
              texte.setText("Exception cause: "+e);
   		      e.printStackTrace();
  				}		 
	} */
		 

    public static void main(String[] args) {
		Arrays.sort(args);
		if (Arrays.binarySearch(args, "trace") != -1)
			ConcernSlicer.setTrace(true);
	
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}