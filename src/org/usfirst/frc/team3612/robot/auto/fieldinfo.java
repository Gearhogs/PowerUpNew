package org.usfirst.frc.team3612.robot.auto;

import edu.wpi.first.wpilibj.DriverStation;

public class fieldinfo {
	
	public fieldinfo() {
	}
		
	public boolean getGameData()
	
	{	
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		
	
		if(gameData.charAt(0) == 'L')
		{
			return true;
			//Left auto code
		} else {
			return false;
			//Right auto code
		}
	}
	
	
	public int getStation()
	{
		return DriverStation.getInstance().getLocation();	
	}
}

