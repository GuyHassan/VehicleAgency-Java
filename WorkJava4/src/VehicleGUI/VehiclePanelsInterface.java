package VehicleGUI;

import java.awt.Component;

/**
 * help me to create a array type of panels (jeep , frigate and other)
 * @author GuyHassan
 *
 */
public interface VehiclePanelsInterface {
	public boolean addVehicleToList();
	public Component PanelComp();
	public void resetField();
	public void setBrowseImg(String nameImg);
	public String getRealName();
}
