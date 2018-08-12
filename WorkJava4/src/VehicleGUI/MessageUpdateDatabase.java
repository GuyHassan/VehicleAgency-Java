package VehicleGUI;
import java.awt.Color;
/***
@author Guy Hassan
ID : 307845032
Campus : Ashdod
 */
import java.awt.Font;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * that class open a frame with a loading gif and waiting randomize time between 3 to 8 second .
 * @author Guy Hassan
 * ID : 307845032
 * Campus : Ashdod
 */
public class MessageUpdateDatabase extends JFrame implements Runnable {
	private int index;
	public MessageUpdateDatabase(int i) {
		setTitle("Update Database");
		setLayout(null);
		setBounds(0, 0, 400, 220);
		setLocationRelativeTo(null);//put the windows in the center
		setResizable(false);//cannot resize the windows
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close the windows when click X
		
		JLabel UpdateLabel = new JLabel("Updating database… Please wait .");
		UpdateLabel.setFont(new Font("",Font.BOLD,20));
		UpdateLabel.setBounds(40, 10,320 , 40);
		
		Icon icon = new ImageIcon("img\\waiting.gif");
		JLabel iconLabel = new JLabel(icon);
		iconLabel.setBounds(0, 0, 400, 240);
		
		iconLabel.add(UpdateLabel);
		add(iconLabel);
		
		index = i;
	}
	@Override
	public void run() {
		setVisible(true);
		try {
			Random rand = new Random();
			Thread.sleep(rand.nextInt(8000) + 3000);
			dispose();
			if(index != -1) {
				JFrame tmp = SuccVehicleFrame(MainFrame.arrayVehiclePanels.get(index).getRealName());
				tmp.setVisible(true);
				Thread.sleep(1300);
				tmp.dispose();	
				if(MenuFrame.getInstance() != null)
					MenuFrame.getInstance().refreshMenuFrame();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * success vehicle add frame when i add new vehicle i open that frame for 1.3 second and close them
	 * @param s - name of vehicle added
	 * @return - frame
	 */
	public JFrame SuccVehicleFrame(String s)
	{
		JFrame f = new JFrame("New Vehicle");	
		f.setLayout(null);
		f.setBounds(0, 0, 250, 80);
		f.setLocationRelativeTo(null);//put the windows in the center
		f.setResizable(false);//cannot resize the windows
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //close the windows when click X
		JLabel a = new JLabel("<html>"+ s + " Successfully added" + "<html>");
		a.setForeground(Color.BLUE);
		a.setBounds(28, -20, 230, 80);
		f.add(a);
		return f;
	}

}
