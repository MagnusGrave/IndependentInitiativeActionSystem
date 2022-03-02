/*****************************************************************************************************************************************************************************************
 * Author		: Corey Farmer
 * Date Created	: 03/01/2022
 * File			: TurnSequenceCalculator.java
 * Program		: Independent Initiative Action System
 * Description	: This program is intended to investigate the Independent Initiative Action System. It may also serve as a resource for other programmers to use in their projects.
 * Version		: 1.0.0
 * License		: Apache License, Version 2.0
 * Date Modified: 03/01/2022
 *****************************************************************************************************************************************************************************************/

package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TurnSequenceCalculator {
	//The initiative required to 
	private static final int initiativeThresholdForAction = 100;
	private static List<CharacterData> characters = new ArrayList<CharacterData>();
	private static int currentTurnCount = 1;
	
	//The whole program takes place within the main method
	public static void main(String[] args) {
		System.out.println("-= Welcome to the Turn Sequence Caluclator =-");
		System.out.println();
		
		Scanner scanner = new Scanner(System.in);
		
		//Create all characters
		System.out.println("[Creating Characters]");
		while(true) {
			System.out.println("-Enter next character's speed:  (-OR- Enter X to complete.)");
			String stringValue = scanner.next();
			if(stringValue.equals("X"))
				break;
			
			int speedValue =  Integer.parseInt( stringValue );
			characters.add(new CharacterData(speedValue));
		}
		System.out.println();
		
		//Get starting sequence
		System.out.println("[Starting Sequence]");
		CalcTurns();
		OutputTurns();
		System.out.println();
		
		//Handle Turns
		while(true) {
			System.out.println("[Turn "+ currentTurnCount +"]");
			System.out.println("-Enter total initiative used this turn:  (-OR- Enter X to exit.)");
			String stringValue = scanner.next();
			if(stringValue.equals("X"))
				break;
			
			int initiativeCost =  Integer.parseInt( stringValue );
			TakeTurn(initiativeCost);
			
			int ticksPast = CalcTurns();
			System.out.println();
			System.out.println("["+ ticksPast +" Ticks Later]");
			System.out.println();
			
			System.out.println("[Order for Turn "+ currentTurnCount +"]");
			OutputTurns();
			System.out.println();
		}
		
		
		//End Program
		System.out.println();
		System.out.println("[Program Terminated]");
		scanner.close();
	}
	
	//Handles the results of a character taking their turn
	private static void TakeTurn(int initiativeCost) {
		//Pay the action's cost
		characters.get(0).Initiative -= initiativeCost;
		
		//Count this turn
		currentTurnCount++;
	}
	
	//Determines character turn sequence
	private static int CalcTurns() {
		int tickCount = 0;
		boolean clockTicksRunning = true;
		
		//Apply ticks to characters until one hits the threshold
		while(clockTicksRunning) {
			for(CharacterData character : characters) {
				character.Initiative += character.getSpeed();
				if(character.Initiative >= initiativeThresholdForAction) {
					clockTicksRunning = false;
					break;
				}
			}
			tickCount++;
		}
		
		//Sort characters by their Initiative
		Collections.sort(characters);
		
		return tickCount;
	}
	
	//Displays the turn sequence of the characters in the console
	private static void OutputTurns() {
		//Display all characters in their turn sequence order
		for(int i = 0; i < characters.size(); i++) {
			System.out.println("Turn Taker " + i + ": " + characters.get(i).toString());
		}
	}
}
