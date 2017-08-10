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
 * Title: HibernateHQLSelect
 * Description: hibernate的HQL常用查询
 * Company: 济宁九点连线信息技术有限公司
 * ProjectName: hibernate_day04
 * @author fupengpeng
 * @date 2017年8月9日 上午10:44:35
 */
public class HibernateHQLSelect {

	//HQL聚集函数的使用
	@Test
	public void testSelectHQL07(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.：使用SessionFactory创建Session对象
			//理解：类似于jdbc的连接数据库
			session = HibernateUtils.getSessionObject();
			//2.：开启事务
			transaction = session.beginTransaction();
			//3.：写具体的crud操作
			
			
			//3-1.投影查询
			Query query = session.createQuery("select count(*) from Customer");
			//3-2.调用方法得到结果
			Object object = query.uniqueResult();
			
			Long count = (Long)object;
			
			int a = count.intValue();
			
			System.out.println("object = " + a);
			
			
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
	
	//HQL投影查询（查询表中某一字段的所有值）
	@Test
	public void testSelectHQL06(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.：使用SessionFactory创建Session对象
			//理解：类似于jdbc的连接数据库
			session = HibernateUtils.getSessionObject();
			//2.：开启事务
			transaction = session.beginTransaction();
			//3.：写具体的crud操作
			
			
			//3-1.投影查询
			Query query = session.createQuery("select custName,custLevel from Customer");
			//3-2.调用方法得到结果
			List<Object> list = query.list();
			
			for (Object object : list) {
				System.out.println("custName = " + object);

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
	
	//HQL分页查询
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
			
			
			//3-1.分页查询
			Query query = session.createQuery("from Customer");
			//3-2.设置分页参数
			//3-2-1.设置开始位置
			query.setFirstResult(0);
			//3-2-1.设置每页记录数
			query.setMaxResults(3);
			//3-3.调用方法得到结果
			List<Customer> list = query.list();
			
			for (Customer customer : list) {
				System.out.println("cid = " + customer.getCid() + "    custName = " + customer.getCustName());
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
	
	//HQL排序查询
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
			
			
			//3-1.排序查询
			Query query = session.createQuery("from Customer order by cid asc");//升序     asc  降序   desc
			//3-2.调用方法得到结果
			List<Customer> list = query.list();
			
			for (Customer customer : list) {
				System.out.println(customer.toString());
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
	
	//HQL模糊查询
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
			
			
			//3-1.模糊查询
			Query query = session.createQuery("from Customer where custName like ?");
			//3-2.设置条件
			//参数1：? 的位置，从 0 开始    参数2：? 的值
			query.setParameter(0, "%点%");
			//3-3.调用方法得到结果
			List<Customer> list = query.list();
			
			for (Customer customer : list) {
				System.out.println(customer.toString());
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

	//HQL条件查询
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
			
			
			//3-1.条件查询
			Query query = session.createQuery("from Customer where cid=? and custName=?");
			//3-2.设置条件
			//参数1：? 的位置，从 0 开始    参数2：? 的值
			query.setParameter(0, 2);
			query.setParameter(1, "二点连线");
			//3-3.调用方法得到结果
			List<Customer> list = query.list();
			
			for (Customer customer : list) {
				System.out.println(customer.toString());
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
	

	//HQL查询所有
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
			
			
			//3-1.查询所有的客户--from 实体类名
			Query query = session.createQuery("from Customer");
			//3-2.调用方法得到结果
			List<Customer> list = query.list();
			
			for (Customer customer : list) {
				System.out.println(customer.toString());
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
