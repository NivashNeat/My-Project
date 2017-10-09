package com.neat.hibernate.dao;





import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




@Repository
@Transactional
public class DbUtil {

	@Autowired
	private SessionFactory sf;
	
	
	
	
	public List queryForList(String hql) {
		Query query = sf.openSession().createQuery(hql);
		List login = query.list();
		System.out.println("Rows affected: " + login.size());
		return login;
	}
	
	public List queryForList(Class clazz) {
		String hql = "From "+clazz.getName();
		Query query = openSession().createQuery(hql);
		List login = query.list();
		System.out.println("Rows affected: " + login.size());
		return login;
	}
	
	public int executeQuery(String hql) {
		Query query = openSession().createQuery(hql);
		int result = query.executeUpdate();
		System.out.println("Rows affected: " + result);
		return result;
	}
	
	public int executeQuery(String hql,String value1) {
		Query query = openSession().createQuery(hql);
		query.setParameter(1,value1);
		int result = query.executeUpdate();
		System.out.println("Rows affected: " + result);
		return result;
	}
	
	public int executeQuery(String hql,String value1,String value2) {
		Query query = openSession().createQuery(hql);
		query.setParameter(1,value1);
		query.setParameter(2,value2);
		int result = query.executeUpdate();
		System.out.println("Rows affected: " + result);
		return result;
	}


	public Session openSession() {
		return sf.openSession();
	}
	
	public void endTrasaction() {
		getCurrentSession().getTransaction().commit();
	}
	
	
	public Session getCurrentSession() {
		return sf.getCurrentSession();
	}
	
	public Object get(Class<?> clazz,Integer primaryKeyValue) {
		openSession();
		Object obj = getCurrentSession().get(clazz,primaryKeyValue);
		return obj;
	}
	
	public int deleteRecord(Class clazz,String fieldName,String value) {
		String hql= "DELETE FROM "+clazz.getSimpleName()+" where "+fieldName+" =:value";
		openSession();
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("value",value);
		int result = query.executeUpdate();
		System.out.println("Rows affected: " + result);
		return result;	
	}
	
	public int queryForInt(String hql) {
		int result  = ((Long)openSession().createQuery(hql).uniqueResult()).intValue();
		System.out.println("count: " + result);
		return result;
	}
	
	public int queryForInt(Class clazz) {
		String hql = "Select count(*) from "+clazz.getName();
		int result  = ((Long)openSession().createQuery(hql).uniqueResult()).intValue();
		System.out.println("count: " + result);
		return result;
	}
	
	public int save(Object obj) {
		int result = (Integer) openSession().save(obj);
		return result;
	}
	
	public void queryForDelete(Object obj) {
		openSession().delete(obj);
		
	}
	
	
	
	
	
	
	
	

	
}
