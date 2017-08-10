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
 * Description: QBC���ò�ѯ
 * Company: �����ŵ�������Ϣ�������޹�˾
 * ProjectName: hibernate_day04
 * @author fupengpeng
 * @date 2017��8��9�� ����11:39:05
 */
public class HibernateQBCSelect {

	/*
	 * �ص㣺
	 *     1.����Ҫд��ѯ��䣬ʹ�÷���ʵ��
	 *     2.ʹ��qbc����ʵ���������
	 *     3.ʹ��qbcʱ��ͨ��ʹ��Criteria����ʵ��
	 */
	
	//QBCͳ�Ʋ�ѯ
	@Test
	public void testSelectQBC06(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.��ʹ��SessionFactory����Session����
			//��⣺������jdbc���������ݿ�
			session = HibernateUtils.getSessionObject();
			//2.����������
			transaction = session.beginTransaction();
			//3.��д�����crud����
			
			
			//3-1.ͳ�Ʋ�ѯ
			Criteria criteria = session.createCriteria(Customer.class);
			//3-2.������Ҫ���Ĳ���
			criteria.setProjection(Projections.rowCount());
			//3-3.���÷����õ����
			Object object = criteria.uniqueResult();
			Long count = (Long)object;
			
			int a = count.intValue();
			
			System.out.println("object = " + a);
			
			
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
	

	//QBC���߲�ѯ
	@Test
	public void testSelectQBC07(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.��ʹ��SessionFactory����Session����
			//��⣺������jdbc���������ݿ�
			session = HibernateUtils.getSessionObject();
			//2.����������
			transaction = session.beginTransaction();
			//3.��д�����crud����
			
			//���߲�ѯ
			//ʹ�ó�����servlet����service��service����dao�������ݿ�crud����--ʹ��hibernate���ʱ������ʹ��session����ķ���ʵ�����ݲ�����
			
			//3-1.��������
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
			
			//3-2.����ִ��ʱ���õ�session
			Criteria criteria = detachedCriteria.getExecutableCriteria(session);
			//3-3.���÷����õ����
			List<Customer> list = criteria.list();
			
			for (Customer customer : list) {
				System.out.println("cid = " + customer.getCid() + "    custName = " + customer.getCustName());
			}
			
			
			
			
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
	

	//QBC��ҳ��ѯ
	@Test
	public void testSelectQBC05(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.��ʹ��SessionFactory����Session����
			//��⣺������jdbc���������ݿ�
			session = HibernateUtils.getSessionObject();
			//2.����������
			transaction = session.beginTransaction();
			//3.��д�����crud����
			
			
			//3-1.��ҳ��ѯ        ��ʼλ�ü��㹫ʽ������ǰҳ-1��*ÿҳ��¼��
			Criteria criteria = session.createCriteria(Customer.class);
			//3-2.���÷�ҳ����
			//3-2-1.���ÿ�ʼλ��
			criteria.setFirstResult(0);
			//3-2-1.����ÿҳ��¼��
			criteria.setMaxResults(3);
			//3-3.���÷����õ����
			List<Customer> list = criteria.list();
			
			for (Customer customer : list) {
				System.out.println("cid = " + customer.getCid() + "    custName = " + customer.getCustName());
			}
			
			
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
	
	//QBC�����ѯ
	@Test
	public void testSelectQBC04(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.��ʹ��SessionFactory����Session����
			//��⣺������jdbc���������ݿ�
			session = HibernateUtils.getSessionObject();
			//2.����������
			transaction = session.beginTransaction();
			//3.��д�����crud����
			
			
			//�����ѯ
			//3-1.����Criteria����
			Criteria criteria = session.createCriteria(Customer.class);
			//3-2.ʹ��Criteria��������ķ�����������ֵ
			//ʹ��add������������ֵ����add��������ʹ��Restrictions��ķ���ʵ����������
			//��̬��������������򣬲��������ø����Ǹ����Խ�������
			criteria.addOrder(Order.asc("cid"));
			
			//3-3.���÷����õ����
			List<Customer> list = criteria.list();
			
			for (Customer customer : list) {
				System.out.println("cid = " + customer.getCid() + "    custName = " + customer.getCustName());
			}
			
			
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
	
	//QBCģ����ѯ
	@Test
	public void testSelectQBC03(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.��ʹ��SessionFactory����Session����
			//��⣺������jdbc���������ݿ�
			session = HibernateUtils.getSessionObject();
			//2.����������
			transaction = session.beginTransaction();
			//3.��д�����crud����
			
			
			//ģ����ѯ
			//3-1.����Criteria����
			Criteria criteria = session.createCriteria(Customer.class);
			//3-2.ʹ��Criteria��������ķ�����������ֵ
			//ʹ��add������������ֵ����add��������ʹ��Restrictions��ķ���ʵ����������
			//��̬�����У�����1��ʵ����������    ����2��ʵ������������Ӧ������ֵ
			criteria.add(Restrictions.like("custName", "%��%"));
			
			//3-3.���÷����õ����
			List<Customer> list = criteria.list();
			
			for (Customer customer : list) {
				System.out.println("cid = " + customer.getCid() + "    custName = " + customer.getCustName());
			}
			
			
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

	//QBC������ѯ
	@Test
	public void testSelectQBC02(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.��ʹ��SessionFactory����Session����
			//��⣺������jdbc���������ݿ�
			session = HibernateUtils.getSessionObject();
			//2.����������
			transaction = session.beginTransaction();
			//3.��д�����crud����
			
			
			//������ѯ
			//3-1.����Criteria����
			Criteria criteria = session.createCriteria(Customer.class);
			//3-2.ʹ��Criteria��������ķ�����������ֵ
			//ʹ��add������������ֵ����add��������ʹ��Restrictions��ķ���ʵ����������
			//��̬�����У�����1��ʵ����������    ����2��ʵ������������Ӧ������ֵ
			criteria.add(Restrictions.eq("cid", 3));
			criteria.add(Restrictions.eq("custName", "��������"));
			
			//3-3.���÷����õ����
			List<Customer> list = criteria.list();
			
			for (Customer customer : list) {
				System.out.println("cid = " + customer.getCid() + "    custName = " + customer.getCustName());
			}
			
			
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
	

	//QBC��ѯ����
	@Test
	public void testSelectQBC01(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.��ʹ��SessionFactory����Session����
			//��⣺������jdbc���������ݿ�
			session = HibernateUtils.getSessionObject();
			//2.����������
			transaction = session.beginTransaction();
			//3.��д�����crud����
			
			//��ѯ���еĿͻ�
			//3-1.����Criteria����
			Criteria criteria = session.createCriteria(Customer.class);
			//3-2.���÷����õ����
			List<Customer> list = criteria.list();
			
			for (Customer customer : list) {
				System.out.println("cid = " + customer.getCid() + "    custName = " + customer.getCustName());
			}
			
			
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
