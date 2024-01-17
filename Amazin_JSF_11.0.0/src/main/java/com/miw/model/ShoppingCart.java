package com.miw.model;

import java.util.HashMap;

public class ShoppingCart {

		private HashMap<Book, Integer>carrito = new HashMap<Book, Integer>();
		private double price;
		public  HashMap<Book, Integer> getCarrito() {
			return carrito;
		}
		public void setCarrito( HashMap<Book, Integer> carrito) {
			this.carrito = carrito;
		}
		public void add(Book element, int stock) {
			price=Math.round((price+element.getPrice())*100.0)/100.0;//Actualizamos y redondeamos el precio
			carrito.put(element, carrito.getOrDefault(element, 0) + stock);
		}
		public double getPrice() {
			return price;
		}
}
