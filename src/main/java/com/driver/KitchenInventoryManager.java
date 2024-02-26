package com.driver;

import java.util.HashMap;
import java.util.Map;

public class KitchenInventoryManager {
	 public Map<String, Integer> inventory;

	    public KitchenInventoryManager() {
	        inventory = new HashMap<>();
	    }

	    public synchronized void addItem(String item, int quantity) {
	    	// your code goes here
	    }

	    public synchronized void removeItem(String item, int quantity) {
	    	// your code goes here
	    }

	    public synchronized int searchItem(String item) {
	        int quantity = inventory.getOrDefault(item, 0);
	        // your code goes here
	        return quantity;
	    }

	    public static void main(String[] args) {
	        KitchenInventoryManager inventoryManager = new KitchenInventoryManager();

	        // Thread to add items to the inventory
	        Thread addThread = new Thread(() -> {
	            inventoryManager.addItem("Spoon", 10);
	            inventoryManager.addItem("Fork", 8);
	        });

	        // Thread to remove items from the inventory
	        Thread removeThread = new Thread(() -> {
	            inventoryManager.removeItem("Spoon", 3);
	            inventoryManager.removeItem("Fork", 5);
	        });

	        // Thread to search for items in the inventory
	        Thread searchThread = new Thread(() -> {
	            inventoryManager.searchItem("Spoon");
	            inventoryManager.searchItem("Knife");
	        });

	        addThread.start();
	        removeThread.start();
	        searchThread.start();
	    }
}
