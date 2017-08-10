package com.jiudianlianxian.hibernatetest;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.jiudianlianxian.entity.Customer;
import com.jiudianlianxian.entity.LinkMan;
import com.jiudianlianxian.utils.HibernateUtils;


/**
 * 
 * Title: HibernateDemoOne
 * Description: hibernate的常见查询方式及应用
 * Company: 济宁九点连线信息技术有限公司
 * ProjectName: hibernate_day04
 * @author fupengpeng
 * @date 2017年8月9日 上午10:25:24
 */
public class HibernateDemoOne {
	

	
	//OID查询
	@Test
	public void testSelectOID(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.：使用SessionFactory创建Session对象
			//理解：类似于jdbc的连接数据库
			session = HibernateUtils.getSessionObject();
			//2.：开启事务
			transaction = session.beginTransaction();
			//3.：写具体的crud操作
			
			
			//查询cid=3的客户
			Customer customer = session.get(Customer.class, 3);
			
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
	
	
	//对象导航查询
	@Test
	public void testSelectObject(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.：使用SessionFactory创建Session对象
			//理解：类似于jdbc的连接数据库
			session = HibernateUtils.getSessionObject();
			//2.：开启事务
			transaction = session.beginTransaction();
			//3.：写具体的crud操作
			
			
			//查询cid=3的客户，在查询他的所有联系人
			//01.查询cid=3的客户
			Customer customer = session.get(Customer.class, 3);
			
		    //02.再查询cid=3的客户的所有联系人
			//通过客户的联系人属性获取其所有的联系人
			Set<LinkMan> linkMans = customer.getSetLinkMan();
			for (LinkMan linkMan : linkMans) {
				System.out.println(linkMan);
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
	
	
	
	//一对多的级联保存复杂写法（无配置）
	@Test
	public void testAddDemo1(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.：使用SessionFactory创建Session对象
			//理解：类似于jdbc的连接数据库
			session = HibernateUtils.getSessionObject();
			//2.：开启事务
			transaction = session.beginTransaction();
			//3.：写具体的crud操作
			//添加一个客户，为客户添加一个联系人
			//01.创建一个客户和联系人
			Customer customer = new Customer();
			customer.setCustName("九点连线");
			customer.setCustLevel("vip");
			customer.setCustMobile("999");
			customer.setCustPhone("110");
			customer.setCustSource("网络");
			
			LinkMan linkMan = new LinkMan();
			linkMan.setLkm_name("张三");
			linkMan.setLkm_gender("男");
			linkMan.setLkm_phone("911");
			
		    //02.建立客户对象和联系人对象之间的关系
			//02-1.把联系人放到客户实体类对象的set集合中去
			customer.getSetLinkMan().add(linkMan);
			//02-2.把客户对象放到联系人客户属性里面去
			linkMan.setCustomer(customer);
			
			//03.保存数据到数据库
			session.save(customer);
			session.save(linkMan);
			
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
