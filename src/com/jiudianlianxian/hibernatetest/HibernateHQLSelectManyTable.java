package com.jiudianlianxian.hibernatetest;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.jiudianlianxian.entity.Customer;
import com.jiudianlianxian.utils.HibernateUtils;

/**
 * 
 * Title: HibernateHQLSelectManyTable
 * Description: hibernate的多表查询
 * Company: 济宁九点连线信息技术有限公司
 * ProjectName: hibernate_day04
 * @author fupengpeng
 * @date 2017年8月10日 上午8:49:04
 */
public class HibernateHQLSelectManyTable {

	
	//HQL多表右外连接查询
	@Test
	public void testSelectHQL05(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.：使用SessionFactory创建Session对象
			//理解：类似于jdbc的连接数据库
			session = HibernateUtils.getSessionObject();
			//2.：开启事务
			transaction = session.beginTransaction();
			//3.：写具体的crud操作
			
			
			//3-1.查询linkman的所有用户和customer中与linkman有关系的所有用户
			Query query = session.createQuery("from Customer c right outer join c.setLinkMan");
			//3-2.调用方法得到结果，list集合，不能指定泛型，list集合中是数组
			List list = query.list();
			
			for (Object object : list) {
				System.out.println(object.toString());
			}
			
			
			//4.：提交事务
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//5.回滚事务
			transaction.rollback();
		} finally {
			//6.：关闭资源     在使用了与本地线程绑定的session对象之后，就不需要手动关闭session了
//			session.close();
		}
	}
	
	
	//HQL多表迫切左外连接查询
	@Test
	public void testSelectHQL04(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.：使用SessionFactory创建Session对象
			//理解：类似于jdbc的连接数据库
			session = HibernateUtils.getSessionObject();
			//2.：开启事务
			transaction = session.beginTransaction();
			//3.：写具体的crud操作
			
			
			//3-1.查询customer的所有用户和linkman中与customer有关系的所有用户
			Query query = session.createQuery("from Customer c left outer join fetch c.setLinkMan");
			//3-2.调用方法得到结果，list集合，不能指定泛型，list集合中是对象
			List list = query.list();
			
			for (Object object : list) {
				System.out.println(object.toString());
			}
			
			
			//4.：提交事务
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//5.回滚事务
			transaction.rollback();
		} finally {
			//6.：关闭资源     在使用了与本地线程绑定的session对象之后，就不需要手动关闭session了
//			session.close();
		}
	}

	

	//HQL多表左外连接查询
	@Test
	public void testSelectHQL03(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.：使用SessionFactory创建Session对象
			//理解：类似于jdbc的连接数据库
			session = HibernateUtils.getSessionObject();
			//2.：开启事务
			transaction = session.beginTransaction();
			//3.：写具体的crud操作
			
			
			//3-1.查询customer的所有用户和linkman中与customer有关系的所有用户
			Query query = session.createQuery("from Customer c left outer join c.setLinkMan");
			//3-2.调用方法得到结果，list集合，不能指定泛型，list集合中是数组
			List list = query.list();
			
			for (Object object : list) {
				System.out.println(object.toString());
			}
			
			
			//4.：提交事务
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//5.回滚事务
			transaction.rollback();
		} finally {
			//6.：关闭资源     在使用了与本地线程绑定的session对象之后，就不需要手动关闭session了
//			session.close();
		}
	}
	
	//HQL多表迫切内连接查询
	@Test
	public void testSelectHQL02(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.：使用SessionFactory创建Session对象
			//理解：类似于jdbc的连接数据库
			session = HibernateUtils.getSessionObject();
			//2.：开启事务
			transaction = session.beginTransaction();
			//3.：写具体的crud操作
			
			
			//3-1.查询customer有联系的所有用户
			Query query = session.createQuery("from Customer c inner join fetch c.setLinkMan");
			//3-2.调用方法得到结果，list集合，不能指定泛型，list集合中是对象
			List list = query.list();
			
			for (Object object : list) {
				System.out.println(object.toString());
			}
			
			
			//4.：提交事务
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//5.回滚事务
			transaction.rollback();
		} finally {
			//6.：关闭资源     在使用了与本地线程绑定的session对象之后，就不需要手动关闭session了
//			session.close();
		}
	}

	

	//HQL多表内连接查询
	@Test
	public void testSelectHQL01(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.：使用SessionFactory创建Session对象
			//理解：类似于jdbc的连接数据库
			session = HibernateUtils.getSessionObject();
			//2.：开启事务
			transaction = session.beginTransaction();
			//3.：写具体的crud操作
			
			
			//3-1.查询customer有联系的所有用户
			Query query = session.createQuery("from Customer c inner join c.setLinkMan");
			//3-2.调用方法得到结果，list集合，不能指定泛型，list集合中是数组
			List list = query.list();
			
			for (Object object : list) {
				System.out.println(object.toString());
			}
			
			
			//4.：提交事务
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//5.回滚事务
			transaction.rollback();
		} finally {
			//6.：关闭资源     在使用了与本地线程绑定的session对象之后，就不需要手动关闭session了
//			session.close();
		}
	}
	
	
	
}
