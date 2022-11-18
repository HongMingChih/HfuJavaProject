package tw.com.hfu.dao.web;


import java.util.ArrayList;

//DAO
public abstract class BaseDao<T> {

	public abstract void add(T t);

	public abstract ArrayList<T> query(String name);
	
	public abstract ArrayList<T> querymenu(String cate);

	public abstract ArrayList<T> queryAll();

	public abstract void delete(Integer pId);

	public abstract void update(T t);
	


	public Integer addM(T t) {
		
		return null;
		
	}
	

}
