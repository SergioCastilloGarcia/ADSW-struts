package com.miw.model;

import java.util.HashMap;

public class ShoppingCart {

	private HashMap<Book, Integer> list;//carrito de la compra
	private double price;

	public ShoppingCart() {
		list= new HashMap<>();
	}
	// Añade o incrementa el número de veces que se ha añadido el elemento al carrito
	public void add(Book element) {
		price=Math.round((price+element.getPrice())*100.0)/100.0;//Actualizamos y redondeamos el precio
		list.put(element, list.getOrDefault(element, 0) + 1);
	}

	// Devuelve una copia de la lista para evitar modificaciones externas
	public HashMap<Book, Integer> getList() {
		return new HashMap<>(list);
	}
	
	public double getPrice() {
		return price;
	}
}
