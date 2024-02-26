package com.driver;

import java.util.HashMap;
import java.util.Map;

public class KitchenInventoryManager {
	public Map<String, Integer> inventory;

	public KitchenInventoryManager() {
		inventory = new HashMap<>();
	}

	public synchronized void addItem(String item, int quantity) {
		inventory.put(item, inventory.getOrDefault(item, 0) + quantity);
		System.out.println(quantity + " " + item + "(s) added to the inventory.");
	}

	public synchronized void removeItem(String item, int quantity) {
		int currentQuantity = inventory.getOrDefault(item, 0);
		if (currentQuantity < quantity) {
			System.out.println("Error: Not enough " + item + " in the inventory.");
			return;
		}
		inventory.put(item, currentQuantity - quantity);
		System.out.println(quantity + " " + item + "(s) removed from the inventory.");
	}

	public synchronized int searchItem(String item) {
		int quantity = inventory.getOrDefault(item, 0);
		System.out.println("There are " + quantity + " " + item + "(s) in the inventory.");
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