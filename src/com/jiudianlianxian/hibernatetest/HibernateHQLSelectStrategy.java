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
 * Description: hibernate�ļ�������
 * Company: �����ŵ�������Ϣ�������޹�˾
 * ProjectName: hibernate_day04
 * @author fupengpeng
 * @date 2017��8��10�� ����9:12:01
 */
public class HibernateHQLSelectStrategy {


	/*
	 * hibernate ��������
	 *     1.������ѯ������id��ѯ��ʹ��get������һ����get�������Ϸ�������ѯ���ݿ�
	 *     2.�ӳٲ�ѯ������id��ѯ��ʹ��load����������load�����������Ϸ�������ѯ���ݿ⣬ֻ�еõ����������ֵ���Żᷢ������ѯ���ݿ�
	 *         01.�༶���ӳ٣�����id��ѯ����ʵ������󣬵���load�����������Ϸ��������ӳٲ�ѯ�������༶���ӳ�
	 *         02.���������ӳ٣���ѯĳ���ͻ����ڲ�ѯ����ͻ���������ϵ�ˣ���ѯ�ͻ���ϵ�˵Ĺ����Ƿ���Ҫ�ӳ٣�������̳�Ϊ���������ӳ�
	 */

	//HQL�������Է���
	@Test
	public void testSelectHQL02(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.��ʹ��SessionFactory����Session����
			//��⣺������jdbc���������ݿ�
			session = HibernateUtils.getSessionObject();
			//2.����������
			transaction = session.beginTransaction();
			//3.��д�����crud����
			
			
//			Customer customer = session.get(Customer.class, 1);
			
			
			/*
			 * ���������ӳ٣�
			 *     1.����get������ѯ�ͻ���������䣬��ѯ���ݿ�ͻ�����
			 *     2.��ȡ�ͻ���Ӧ����ϵ�ˣ�Ĭ�ϲ�����sql����ѯ���ݿ⣬
			 *     3.����Ҫʹ�ÿͻ���Ӧ����ϵ�����ݵ�ʱ�򣬲ŷ���sql����ѯ�ͻ���Ӧ����ϵ������
			 */
			Customer customer = session.get(Customer.class, 1);
			
			Set<LinkMan> linkMans = customer.getSetLinkMan();
			
			System.out.println("linkMans.size = " + linkMans.size());
			
			
			//4.���ύ����
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//5.�ع�����
			transaction.rollback();
		} finally {
			//6.���ر���Դ     ��ʹ�����뱾���̰߳󶨵�session����֮�󣬾Ͳ���Ҫ�ֶ��ر�session��
//			session.close();
		}
	}
	
	//HQL�������Է���
	@Test
	public void testSelectHQL01(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.��ʹ��SessionFactory����Session����
			//��⣺������jdbc���������ݿ�
			session = HibernateUtils.getSessionObject();
			//2.����������
			transaction = session.beginTransaction();
			//3.��д�����crud����
			
			
//			Customer customer = session.get(Customer.class, 1);
			
			
			/*
			 * 1.����load�����󲢲�����������sql���ȥ���ݿ��ѯ
			 * 2.��Ҫ��ȡ����ѯ���������ֵ��ʱ����Ҫ�ó���ѯ���������ֵ��ʱ�򣩣��Żᷢ��sql���ȥ���ݿ��ѯ
			 */
			Customer customer = session.load(Customer.class, 1);
			
			System.out.println("cid = " + customer.getCid());
			
			
			//4.���ύ����
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//5.�ع�����
			transaction.rollback();
		} finally {
			//6.���ر���Դ     ��ʹ�����뱾���̰߳󶨵�session����֮�󣬾Ͳ���Ҫ�ֶ��ر�session��
//			session.close();
		}
	}
	
	
	
}
