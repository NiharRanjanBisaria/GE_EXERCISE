package com.ge.exercise5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.ge.exercise5.ItemType.*;
import static org.junit.Assert.assertEquals;

public class Warehouse {
	private static final Logger logger = LogManager.getLogger(Warehouse.class);

	List<Item> items;

	public Warehouse() {
		items = new ArrayList<>();
	}

	public void addItem(Item item) {
		items.add(item);
	}

	//TO DO
	//Update the software as necessary to accomplish the following
	//Add a new type of item that is PERISHABLE.
	//Perishable items are to degrade in value twice as fast as normal items
	
	
	//USED CASES

	//CASE					 			NORMAL VALUE	||		PERISHABLE VALUE
	//BEFORE SELL DATE  					-1		    			-2
	//ON SELL DATE  						-2						-4
	//AFTER SELL DATE  						-2						-4

	//PERISHABLE VALUE ARE DECREASING TWICE AS FAST AS NORMAL.



	public void updateItems() {	
		for (Item item : items) {
			if (item.getType() != AGEABLE && item.getType() != RARE) { //TRUE FOR NORMAL PERISHABLE PRECIOUS
				if (item.getValue() > 0) { 
					if (item.getType() == NORMAL || item.getType() == PERISHABLE) {
						item.setValue(item.getValue() - 1);//NORMAL && PERISHABLE  VALUE DECREASE BY -1
					}
					if(item.getValue() > 0 && item.getType() == PERISHABLE ) {
						//FOR TWCE DEGRADE 
						item.setValue(item.getValue() - 1);//PERISHABLE VALUE DECREASES BY -1
					}
				}

			} else if (item.getType() != NORMAL && item.getType() != PERISHABLE  && item.getType() != AGEABLE && item.getType() != RARE) {
				if (item.getValue() > 0) {
					item.setValue(item.getValue() - 1);
				}
			} else if (item.getValue() < 50) { //TRUE FOR AGEABLE  RARE
				//AGEABLE ITEMS IMPROVE IN VALUE OVER TIME. EACH DAY THEIR VALUE GOES UP BY ONE. 
				item.setValue(item.getValue() + 1);// 
				if (item.getType() == RARE) {
					if (item.getSellBy() <= 14) {
						//RARE ITEMS INCREASE IN VALUE OVER TIME LIKE AGEABLE ITEMS, 
						//HOWEVER, WITHIN 14 DAYS OF THE SELL BY THE VALUE 
						if (item.getValue() < 50)
							item.setValue(item.getValue() + 1);//2 TIMES FINAL INCREASE OF RARE VALUE
					}
					//RARE ITEMS INCREASE IN VALUE OVER TIME LIKE AGEABLE ITEMS, 
					// WITHIN 7 DAYS THE VALUE IMPROVES BY 3X THE NORMAL RATE. 
					if (item.getSellBy() <= 7) {
						if (item.getValue() < 50)
							item.setValue(item.getValue() + 1); //3 TIMES FINAL INCREASE OF RARE VALUE
					}
				}
			}
			//EXCEPT PRECIOUS ALL OTHER TYPES OF SELLBY DATE DECREASES
			if (item.getType() != PRECIOUS) {  
				item.setSellBy(item.getSellBy() - 1);
			}
			
			if (item.getSellBy() < 0) { 
				if (item.getType() != AGEABLE) { //TRUE FOR NORMAL PRECIOUS , RARE,PERISHABLE
					if (item.getType() != RARE) { //TRUE FOR NORMAL PRECIOUS,PERISHABLE
						if (item.getValue() > 0) {//The value of an item can never go below 0
							if (item.getType() != PRECIOUS) { //TRUE FOR NORMAL//PERISHABLE
								item.setValue(item.getValue() - 1);//DECREASE NORMAL VALUE BY -1
							}
							//FOR TWICE DEGRADE OF PERISHABLE 
							if (item.getType() == PERISHABLE) { //TRUE FOR PERISHABLE
								//FOR TWCE DEGRADE
								item.setValue(item.getValue() - 1);//DECREASE PERISHABLE VALUE BY -1 
							}
						}
					} else {//TRUE RARE
						item.setValue(item.getValue() - item.getValue());
					}
				} else {
					//AGEABLE ITEMS IMPROVE OVER TIME
					//EACH DAY THE VALUE DOUBLES
					if (item.getValue() < 50) { //ITEM VALUE CANNOT BE GREATER 50
						item.setValue(item.getValue() + 1);
					}
				}
			}
		}
	}
}
