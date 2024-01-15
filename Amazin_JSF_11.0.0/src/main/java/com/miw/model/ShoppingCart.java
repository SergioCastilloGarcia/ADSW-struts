package com.miw.model;

import java.util.HashMap;

public class ShoppingCart {

		private HashMap<String, Integer>carrito = new HashMap<String, Integer>();
		public  HashMap<String, Integer> getCarrito() {
			return carrito;
		}
		public void setCarrito( HashMap<String, Integer> login) {
			this.carrito = login;
		}
		public void add(String title) {
			carrito.put(title, carrito.getOrDefault(title, 0)+1);
		}
		
}
