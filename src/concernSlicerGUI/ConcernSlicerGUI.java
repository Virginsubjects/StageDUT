package concernSlicerGUI;

import java.io.*;
import java.io.FileFilter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.filechooser.*;
import javax.swing.text.html.HTMLEditorKit;

import concernSlicer.ConcernSlicer;


@SuppressWarnings("serial")
public class ConcernSlicerGUI extends JPanel
                             implements ActionListener{
    static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;
    JLabel color;
    JLabel titleUpdate;
    JLabel concern;
    JButton okBtn;
    JTextField tfCol;
    JTextField tfCon;
    File file ;
      
    public ConcernSlicerGUI() {
        super(new BorderLayout());
        
        log = new JTextArea(20,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
     
        JScrollPane logScrollPane = new JScrollPane(log);
     	
       //Create a file chooser
        fc = new JFileChooser();
        
        openButton = new JButton("Select the File to be colorized");
        saveButton = new JButton("Sauvegarder");
        titleUpdate = new JLabel("COLOR UPDATE");
        color = new JLabel("New color : ");
		concern = new JLabel("Concern : ");
		okBtn = new JButton("OK");
		
		tfCol = new JTextField();
		tfCon = new JTextField();
	
        openButton.addActionListener(this);
        saveButton.addActionListener(this);
        okBtn.addActionListener(this);
        
        //For layout purposes, put the buttons in a separate panel, file updates as well
        JPanel buttonPanel = new JPanel();
        JPanel updatePanel = new JPanel();
        updatePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = gbc.gridy = 0;
        gbc.gridheight = gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        gbc.insets = new Insets(0, 10, 0, 0);
        updatePanel.add(titleUpdate, gbc);
        
         gbc.gridy = 1;
        gbc.gridheight = gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        gbc.insets = new Insets(0, 10, 0, 0);
        updatePanel.add(color, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 0, 10);
        updatePanel.add(tfCol, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 10, 0, 0);
        updatePanel.add(concern, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 0, 10);
        updatePanel.add(tfCon, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridheight = gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 10, 0, 0);
        updatePanel.add(okBtn, gbc);

        updatePanel.setPreferredSize(new Dimension(170,0));
                     
        //use FlowLayout       
   
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        
        //Add the buttons and the log to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
        add(updatePanel, BorderLayout.EAST);           
    }
    
    public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(ConcernSlicerGUI.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	ArrayList<String> list;
            	String msg ="";
                File file = fc.getSelectedFile();
                this.file = file;
                //This is where a real application would open the file.
                log.append("Opening: " + file.getAbsolutePath() + "." + newline);       
                list = listFiles(file);
                           
                try {
                	msg = ConcernSlicer.colorize(file, list);				
					String codeDir = "file:"+file.getParentFile().getAbsolutePath();				
					String colorized = codeDir + "\\colorized.html";		
					log.append("This is the HTML generated :" + colorized );
					log.append(newline+"Liste d'erreurs:"+ newline+"."+msg+ newline);
					
				} catch (IOException e1) {
					log.append("IOException : "+e1.getMessage()+ newline  );
				}
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());

        //Handle save button action.
        } else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(ConcernSlicerGUI.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                             
                String codeDir = "file:"+file.getParentFile().getAbsolutePath();
                String colorized = codeDir + "\\colorized.html";      
				
              //This is where a real application would save the file.
                log.append("Saving: " + file.getName() + "." + newline);
            } else {
                log.append("Save command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
        //Handle concern color changes
        else if(e.getSource() == okBtn){
        		if(file != null) {
        			String color = tfCol.getText();
        			String concern = tfCon.getText();
        		
        			String codeDir = file.getParentFile().getAbsolutePath();				
        			String concernModif = codeDir + "\\concerns\\"+ concern+".txt";
				
        			Path concernPath = Paths.get(concernModif);
				
        			BufferedReader br = null;
        			String txt;
        			ArrayList<String> res= new ArrayList<>() ;
        			BufferedWriter writer;
        			try {
        				br = new BufferedReader(new FileReader(concernModif));
        				while((txt = br.readLine()) != null) {
        					res.add(txt);
        				}
        				res.remove(0);
        				writer = Files.newBufferedWriter(concernPath, StandardCharsets.UTF_8);
        				writer.write(color+newline);
        				for(String s : res)
        				  writer.write(s+newline);
        				log.append("changes have been properly operated in file: "+concernModif +", "
        						+ "please run again the program since the begin");
        				writer.close();
        			} catch (IOException e1) {
        				log.append("IOException : "+e1.getMessage()+ newline  );
        			} 	
        		}
				else {
					log.append("No file has been chosen, please choose the right one"+newline);					
				}
        }
    }
    
    private ArrayList<String> listFiles(File file){
    	ArrayList<String> list = new ArrayList();
    	try {
     	   String concernsDir = file.getParentFile().getAbsolutePath() + "\\concerns";
			    File repFile = new File(concernsDir);
				File[] paths = repFile.listFiles();
				for( File f : paths) 
					list.add(f.getName());										
		} catch (Exception e2) {		
			System.out.println("Inexpected error occured");
		}
    	return list;    	
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ConcernSlicerGUI.class.getResource(path);
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
       // frame.setSize(new Dimension(800,400));
        
       
        frame.add(new ConcernSlicerGUI());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
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