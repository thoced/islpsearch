package GUI;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

public class popupMenuColler extends JPopupMenu implements ActionListener
{
	private JMenuItem anItem;
	private JMenuItem copyItem;
	
	private JTextArea source;
	
	public popupMenuColler(JTextArea s)
	{
		source = s;
		anItem = new JMenuItem("Coller");
		anItem.addActionListener(this);
		copyItem = new JMenuItem("Copier");
		copyItem.addActionListener(this);
		add(anItem);
		//add(copyItem);
	}

	@Override
	public boolean isPopupTrigger(MouseEvent e) {
		// TODO Auto-generated method stub
		
		//JOptionPane.showMessageDialog(e.getComponent(), "test");
		
		return super.isPopupTrigger(e);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated metho
		
		if(source != null)
		{
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			
			if(arg0.getSource() == anItem)
			{
			

				try 
				{
					 Transferable contents = clipboard.getContents(null);
					 String clipText = (String) contents.getTransferData(DataFlavor.stringFlavor);
					
					 source.setText(clipText);
					
				} catch (UnsupportedFlavorException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
	}

	
}
