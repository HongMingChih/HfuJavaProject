package tw.com.hfu.dao.android;

import java.util.List;

public interface BaseDAO<T> {
	
	void insert(T t);
	
	void delete(T t);
	
	void update(T t);
	
	List<T> getAll();
	
	T getByKeyword(String keyword);
	
	List<T> getByKeywordLike(String keyword);	
}
