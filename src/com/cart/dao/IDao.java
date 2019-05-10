package com.cart.dao;

import java.util.List;



public interface IDao<T>{
	
	public void add(T t);

	public void update(T t);

	public void delete(int id);

	public T get(int id);

	public List<T> list();

	public List<T> list(int offset, int count);

}
