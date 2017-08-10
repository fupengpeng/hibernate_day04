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
 * Description: hibernate��HQL���ò�ѯ
 * Company: �����ŵ�������Ϣ�������޹�˾
 * ProjectName: hibernate_day04
 * @author fupengpeng
 * @date 2017��8��9�� ����10:44:35
 */
public class HibernateHQLSelect {

	//HQL�ۼ�������ʹ��
	@Test
	public void testSelectHQL07(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.��ʹ��SessionFactory����Session����
			//��⣺������jdbc���������ݿ�
			session = HibernateUtils.getSessionObject();
			//2.����������
			transaction = session.beginTransaction();
			//3.��д�����crud����
			
			
			//3-1.ͶӰ��ѯ
			Query query = session.createQuery("select count(*) from Customer");
			//3-2.���÷����õ����
			Object object = query.uniqueResult();
			
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
	
	//HQLͶӰ��ѯ����ѯ����ĳһ�ֶε�����ֵ��
	@Test
	public void testSelectHQL06(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.��ʹ��SessionFactory����Session����
			//��⣺������jdbc���������ݿ�
			session = HibernateUtils.getSessionObject();
			//2.����������
			transaction = session.beginTransaction();
			//3.��д�����crud����
			
			
			//3-1.ͶӰ��ѯ
			Query query = session.createQuery("select custName,custLevel from Customer");
			//3-2.���÷����õ����
			List<Object> list = query.list();
			
			for (Object object : list) {
				System.out.println("custName = " + object);

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
	
	//HQL��ҳ��ѯ
	@Test
	public void testSelectHQL05(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.��ʹ��SessionFactory����Session����
			//��⣺������jdbc���������ݿ�
			session = HibernateUtils.getSessionObject();
			//2.����������
			transaction = session.beginTransaction();
			//3.��д�����crud����
			
			
			//3-1.��ҳ��ѯ
			Query query = session.createQuery("from Customer");
			//3-2.���÷�ҳ����
			//3-2-1.���ÿ�ʼλ��
			query.setFirstResult(0);
			//3-2-1.����ÿҳ��¼��
			query.setMaxResults(3);
			//3-3.���÷����õ����
			List<Customer> list = query.list();
			
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
	
	//HQL�����ѯ
	@Test
	public void testSelectHQL04(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.��ʹ��SessionFactory����Session����
			//��⣺������jdbc���������ݿ�
			session = HibernateUtils.getSessionObject();
			//2.����������
			transaction = session.beginTransaction();
			//3.��д�����crud����
			
			
			//3-1.�����ѯ
			Query query = session.createQuery("from Customer order by cid asc");//����     asc  ����   desc
			//3-2.���÷����õ����
			List<Customer> list = query.list();
			
			for (Customer customer : list) {
				System.out.println(customer.toString());
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
	
	//HQLģ����ѯ
	@Test
	public void testSelectHQL03(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.��ʹ��SessionFactory����Session����
			//��⣺������jdbc���������ݿ�
			session = HibernateUtils.getSessionObject();
			//2.����������
			transaction = session.beginTransaction();
			//3.��д�����crud����
			
			
			//3-1.ģ����ѯ
			Query query = session.createQuery("from Customer where custName like ?");
			//3-2.��������
			//����1��? ��λ�ã��� 0 ��ʼ    ����2��? ��ֵ
			query.setParameter(0, "%��%");
			//3-3.���÷����õ����
			List<Customer> list = query.list();
			
			for (Customer customer : list) {
				System.out.println(customer.toString());
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

	//HQL������ѯ
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
			
			
			//3-1.������ѯ
			Query query = session.createQuery("from Customer where cid=? and custName=?");
			//3-2.��������
			//����1��? ��λ�ã��� 0 ��ʼ    ����2��? ��ֵ
			query.setParameter(0, 2);
			query.setParameter(1, "��������");
			//3-3.���÷����õ����
			List<Customer> list = query.list();
			
			for (Customer customer : list) {
				System.out.println(customer.toString());
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
	

	//HQL��ѯ����
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
			
			
			//3-1.��ѯ���еĿͻ�--from ʵ������
			Query query = session.createQuery("from Customer");
			//3-2.���÷����õ����
			List<Customer> list = query.list();
			
			for (Customer customer : list) {
				System.out.println(customer.toString());
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
