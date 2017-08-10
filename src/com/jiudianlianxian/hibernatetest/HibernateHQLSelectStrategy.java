package com.jiudianlianxian.hibernatetest;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.jiudianlianxian.entity.Customer;
import com.jiudianlianxian.entity.LinkMan;
import com.jiudianlianxian.utils.HibernateUtils;

/**
 * 
 * Title: HibernateHQLSelectStrategy
 * Description: hibernate的检索策略
 * Company: 济宁九点连线信息技术有限公司
 * ProjectName: hibernate_day04
 * @author fupengpeng
 * @date 2017年8月10日 上午9:12:01
 */
public class HibernateHQLSelectStrategy {


	/*
	 * hibernate 检索策略
	 *     1.立即查询，根据id查询，使用get方法，一调用get方法发上发送语句查询数据库
	 *     2.延迟查询，根据id查询，使用load方法，调用load方法不会马上发送语句查询数据库，只有得到对象里面的值，才会发送语句查询数据库
	 *         01.类级别延迟：根据id查询返回实体类对象，调用load方法不会马上发送语句的延迟查询，就是类级别延迟
	 *         02.关联级别延迟：查询某个客户，在查询这个客户的所有联系人，查询客户联系人的过程是否需要延迟，这个过程称为关联级别延迟
	 */

	//HQL检索策略分析
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
			
			
//			Customer customer = session.get(Customer.class, 1);
			
			
			/*
			 * 关联级别延迟：
			 *     1.调用get方法查询客户，发送语句，查询数据库客户数据
			 *     2.获取客户对应的联系人，默认不发送sql语句查询数据库，
			 *     3.当需要使用客户对应的联系人数据的时候，才发送sql语句查询客户对应的联系人数据
			 */
			Customer customer = session.get(Customer.class, 1);
			
			Set<LinkMan> linkMans = customer.getSetLinkMan();
			
			System.out.println("linkMans.size = " + linkMans.size());
			
			
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
	
	//HQL检索策略分析
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
			
			
//			Customer customer = session.get(Customer.class, 1);
			
			
			/*
			 * 1.调用load方法后并不会立即发送sql语句去数据库查询
			 * 2.当要获取除查询条件以外的值的时候（需要用除查询条件以外的值的时候），才会发送sql语句去数据库查询
			 */
			Customer customer = session.load(Customer.class, 1);
			
			System.out.println("cid = " + customer.getCid());
			
			
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
