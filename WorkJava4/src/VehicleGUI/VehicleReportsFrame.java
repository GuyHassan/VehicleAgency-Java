package VehicleGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class VehicleReportsFrame extends JFrame {
	private static VehicleReportsFrame singletonCheck = null;
	private JPanel panel;
	private ArrayList<JButton> arrayButtons = new ArrayList<JButton>();
	private static ImageIcon[] imgArr = {new ImageIcon("img\\buyvehicle.png"),new ImageIcon("img\\testdrive.PNG"),
			new ImageIcon("img\\changeflag.png"),new ImageIcon("img\\setkm.png"),new ImageIcon("img\\addvehicle.png"),new ImageIcon("img\\report.png"),new ImageIcon("img\\exit.png")};
	private ArrayList<JRadioButton> arrayRadio = new ArrayList<JRadioButton>();
	private ButtonGroup group = new ButtonGroup();
	private JPanel scrollPanel = new JPanel();
	private JScrollPane scroll = new JScrollPane(scrollPanel);
	private JLabel NoVehicleExist = new JLabel("There Are No Vehicles Available");
	private JLabel VehicleReports = new JLabel("~ Vehicle Reports ~");
	JLabel ToString = new JLabel();

	public VehicleReportsFrame() {
		//init new frame ----------------------------------------------------------------------------
		setTitle("Vehicle Reports");
		setLayout(null);
		setBounds(10, 0, 600, 480);
		setLocationRelativeTo(null);//put the windows in the center
		setResizable(false);//cannot resize the windows
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close the windows when click X
		setVisible(true);
		
		//initialize a scroll panel with a new object image on this frame 
		refreshVehicleReportFrame();
		
		//no vehicle exist label -------------------------------------------------------------------
		NoVehicleExist.setBounds(35, 50, 600, 250);
		NoVehicleExist.setFont(new Font(NoVehicleExist.getName(), Font.BOLD, 35));
		
		//title above the picture
		VehicleReports.setBounds(160, -20, 500,100);
		VehicleReports.setFont(new Font(VehicleReports.getName(), Font.BOLD, 30));
	
		//create panel on a scroll -------------------------------------------------------------------
		scrollPanel.setBounds(0, 0,600 ,250);
		
		//label to show the toString function -----------------------------------------------------------
		ToString.setBounds(30, 380, 580, 50);
		add(ToString);
		
		//create scroll panel ---------------------------------------------------------------
		scroll.setBounds(0, 50,600 , 290);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);//every image increases the scroll
	}

	/**------------------------------------------------------------------------------
	 * set size to the image label
	 */
	public static ImageIcon resizeImage(ImageIcon img,int w , int h)
	{
		Image im = img.getImage();
		Image tmp =  im.getScaledInstance(w, h, Image.SCALE_REPLICATE);
		img = new ImageIcon(tmp);
		return img;
	}
	
	public void putOnToStringLabelTheCurrentVehicle()
	{
		for(int i=0; i<arrayRadio.size();i++)
		{	
			arrayRadio.get(i).addMouseListener(new MouseListener()
			{		
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e) {}
				@Override
				public void mouseExited(MouseEvent e) {ToString.setText(null);}
				@Override
				public void mouseEntered(MouseEvent e) {
					for(int i=0;i<arrayRadio.size();i++)
						if(e.getSource().equals(arrayRadio.get(i)))
							ToString.setText("<html><p width=\"550\">" + MainVehicleAgency.arrV.get(i).toString()+ "</p><html>" );
				}
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			}
	}
	
	public void refreshVehicleReportFrame()
	{
		if(MainVehicleAgency.arrV.size() == 0) {
			remove(scroll);
			remove(VehicleReports);
			add(NoVehicleExist);}
		else {
			remove(NoVehicleExist);
			add(scroll);
			add(VehicleReports);}
		arrayRadio.clear();
		group.clearSelection();
		scrollPanel.removeAll();
		for (int i = 0; i < MainVehicleAgency.arrayImage.size(); i++)
		{
			MainVehicleAgency.arrayImage.set(i,resizeImage(MainVehicleAgency.arrayImage.get(i), 270, 260));
			arrayRadio.add(new JRadioButton(MainVehicleAgency.arrayImage.get(i)));
			scrollPanel.add(arrayRadio.get(i)); 
			group.add(arrayRadio.get(i));
		}
		repaint_revalidate(scrollPanel);
		repaint_revalidate(scroll);
		repaint_revalidate(VehicleReports);
		repaint_revalidate(this);
		putOnToStringLabelTheCurrentVehicle();
	}
	public void repaint_revalidate(Component a)
	{
		a.revalidate();
		a.repaint();
	}
	/**
	 * createInstance singleton method ,initialize the singleton variable if he null 
	 * @return the instance from this class
	 */
	public static VehicleReportsFrame createInstance() {
		if(singletonCheck == null)
			singletonCheck = new VehicleReportsFrame();
		return singletonCheck;
	}
	/**
	 * getInstace method, return the singleton variable , help me to understand if i have an open frame from this class
	 * @return the instance of variable class
	 */
	public static VehicleReportsFrame getInstance(){return singletonCheck;}
}
