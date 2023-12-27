package com.miw.model;

import java.util.HashMap;

public class ShoppingCart {

	private HashMap<String, Integer> list;//carrito de la compra

	public ShoppingCart() {
		list= new HashMap<>();
	}
	// Añade o incrementa el número de veces que se ha añadido el elemento al carrito
	public void add(String element) {
		list.put(element, list.getOrDefault(element, 0) + 1);
	}

	// Devuelve una copia de la lista para evitar modificaciones externas
	public HashMap<String, Integer> getList() {
		return new HashMap<>(list);
	}
}
