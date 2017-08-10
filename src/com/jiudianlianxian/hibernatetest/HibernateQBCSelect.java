package com.jiudianlianxian.hibernatetest;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.jiudianlianxian.entity.Customer;
import com.jiudianlianxian.manytomany.User;
import com.jiudianlianxian.utils.HibernateUtils;

/**
 * 
 * Title: HibernateQBCSelect
 * Description: QBC常用查询
 * Company: 济宁九点连线信息技术有限公司
 * ProjectName: hibernate_day04
 * @author fupengpeng
 * @date 2017年8月9日 上午11:39:05
 */
public class HibernateQBCSelect {

	/*
	 * 特点：
	 *     1.不需要写查询语句，使用方法实现
	 *     2.使用qbc操作实体类和属性
	 *     3.使用qbc时，通过使用Criteria对象实现
	 */
	
	//QBC统计查询
	@Test
	public void testSelectQBC06(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.：使用SessionFactory创建Session对象
			//理解：类似于jdbc的连接数据库
			session = HibernateUtils.getSessionObject();
			//2.：开启事务
			transaction = session.beginTransaction();
			//3.：写具体的crud操作
			
			
			//3-1.统计查询
			Criteria criteria = session.createCriteria(Customer.class);
			//3-2.设置所要做的操作
			criteria.setProjection(Projections.rowCount());
			//3-3.调用方法得到结果
			Object object = criteria.uniqueResult();
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
	

	//QBC离线查询
	@Test
	public void testSelectQBC07(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.：使用SessionFactory创建Session对象
			//理解：类似于jdbc的连接数据库
			session = HibernateUtils.getSessionObject();
			//2.：开启事务
			transaction = session.beginTransaction();
			//3.：写具体的crud操作
			
			//离线查询
			//使用场景，servlet调用service，service调用dao（对数据库crud操作--使用hibernate框架时，最终使用session里面的方法实现数据操作）
			
			//3-1.创建对象
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
			
			//3-2.最终执行时才用到session
			Criteria criteria = detachedCriteria.getExecutableCriteria(session);
			//3-3.调用方法得到结果
			List<Customer> list = criteria.list();
			
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
	

	//QBC分页查询
	@Test
	public void testSelectQBC05(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.：使用SessionFactory创建Session对象
			//理解：类似于jdbc的连接数据库
			session = HibernateUtils.getSessionObject();
			//2.：开启事务
			transaction = session.beginTransaction();
			//3.：写具体的crud操作
			
			
			//3-1.分页查询        开始位置计算公式：（当前页-1）*每页记录数
			Criteria criteria = session.createCriteria(Customer.class);
			//3-2.设置分页参数
			//3-2-1.设置开始位置
			criteria.setFirstResult(0);
			//3-2-1.设置每页记录数
			criteria.setMaxResults(3);
			//3-3.调用方法得到结果
			List<Customer> list = criteria.list();
			
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
	
	//QBC排序查询
	@Test
	public void testSelectQBC04(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.：使用SessionFactory创建Session对象
			//理解：类似于jdbc的连接数据库
			session = HibernateUtils.getSessionObject();
			//2.：开启事务
			transaction = session.beginTransaction();
			//3.：写具体的crud操作
			
			
			//排序查询
			//3-1.创建Criteria对象
			Criteria criteria = session.createCriteria(Customer.class);
			//3-2.使用Criteria对象里面的方法设置条件值
			//使用add方法设置条件值，在add方法里面使用Restrictions类的方法实现条件设置
			//静态方法决定排序规则，参数：设置根据那个属性进行排序
			criteria.addOrder(Order.asc("cid"));
			
			//3-3.调用方法得到结果
			List<Customer> list = criteria.list();
			
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
	
	//QBC模糊查询
	@Test
	public void testSelectQBC03(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.：使用SessionFactory创建Session对象
			//理解：类似于jdbc的连接数据库
			session = HibernateUtils.getSessionObject();
			//2.：开启事务
			transaction = session.beginTransaction();
			//3.：写具体的crud操作
			
			
			//模糊查询
			//3-1.创建Criteria对象
			Criteria criteria = session.createCriteria(Customer.class);
			//3-2.使用Criteria对象里面的方法设置条件值
			//使用add方法设置条件值，在add方法里面使用Restrictions类的方法实现条件设置
			//静态方法中，参数1：实体类属性名    属性2：实体类属性名对应的条件值
			criteria.add(Restrictions.like("custName", "%点%"));
			
			//3-3.调用方法得到结果
			List<Customer> list = criteria.list();
			
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

	//QBC条件查询
	@Test
	public void testSelectQBC02(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.：使用SessionFactory创建Session对象
			//理解：类似于jdbc的连接数据库
			session = HibernateUtils.getSessionObject();
			//2.：开启事务
			transaction = session.beginTransaction();
			//3.：写具体的crud操作
			
			
			//条件查询
			//3-1.创建Criteria对象
			Criteria criteria = session.createCriteria(Customer.class);
			//3-2.使用Criteria对象里面的方法设置条件值
			//使用add方法设置条件值，在add方法里面使用Restrictions类的方法实现条件设置
			//静态方法中，参数1：实体类属性名    属性2：实体类属性名对应的条件值
			criteria.add(Restrictions.eq("cid", 3));
			criteria.add(Restrictions.eq("custName", "三点连线"));
			
			//3-3.调用方法得到结果
			List<Customer> list = criteria.list();
			
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
	

	//QBC查询所有
	@Test
	public void testSelectQBC01(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.：使用SessionFactory创建Session对象
			//理解：类似于jdbc的连接数据库
			session = HibernateUtils.getSessionObject();
			//2.：开启事务
			transaction = session.beginTransaction();
			//3.：写具体的crud操作
			
			//查询所有的客户
			//3-1.创建Criteria对象
			Criteria criteria = session.createCriteria(Customer.class);
			//3-2.调用方法得到结果
			List<Customer> list = criteria.list();
			
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
	
}
