/*****************************************************************************************************************************************************************************************
 * Author		: Corey Farmer
 * Date Created	: 03/01/2022
 * File			: CharacterData.java
 * Program		: Independent Initiative Action System
 * Description	: This program is intended to investigate the Independent Initiative Action System. It may also serve as a resource for other programmers to use in their projects.
 * Version		: 1.0.0
 * License		: Apache License, Version 2.0
 * Date Modified: 03/01/2022
 *****************************************************************************************************************************************************************************************/

package logic;

public class CharacterData implements Comparable<CharacterData>{
	public CharacterData(int speed) {
		this.speed = speed;
	}
	
	//Unchanging Variables
	private final int speed;
	public int getSpeed() {
		//TODO Apply modifiers here, if for example this character has a status effect speeding them up or slowing them down.
		
		
		return speed;
	}
	
	//Actively Updated Variables
	public int Initiative;
	
	
	@Override
	public String toString() {
		return "Character w/ speed: " + speed + ", Initiative: " + Initiative;
	}
	
	@Override
	public int compareTo(CharacterData other) {
		//Default value to return is zero. This would mean that the things being compared are equal to one another.
		int returnValue = 0;
		//See if we sequence sooner
		if(this.Initiative < other.Initiative)
			returnValue = 1;
		//See if the other sequences sooner
		else if(this.Initiative > other.Initiative)
			returnValue = -1;
		else {
			//TODO Compare the characters by another aspect, since they've got the same Initiative
			
		}
		return returnValue;
	}
}
