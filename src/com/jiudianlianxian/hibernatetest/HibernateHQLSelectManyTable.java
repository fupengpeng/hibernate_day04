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
 * Description: hibernate�Ķ���ѯ
 * Company: �����ŵ�������Ϣ�������޹�˾
 * ProjectName: hibernate_day04
 * @author fupengpeng
 * @date 2017��8��10�� ����8:49:04
 */
public class HibernateHQLSelectManyTable {

	
	//HQL����������Ӳ�ѯ
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
			
			
			//3-1.��ѯlinkman�������û���customer����linkman�й�ϵ�������û�
			Query query = session.createQuery("from Customer c right outer join c.setLinkMan");
			//3-2.���÷����õ������list���ϣ�����ָ�����ͣ�list������������
			List list = query.list();
			
			for (Object object : list) {
				System.out.println(object.toString());
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
	
	
	//HQL��������������Ӳ�ѯ
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
			
			
			//3-1.��ѯcustomer�������û���linkman����customer�й�ϵ�������û�
			Query query = session.createQuery("from Customer c left outer join fetch c.setLinkMan");
			//3-2.���÷����õ������list���ϣ�����ָ�����ͣ�list�������Ƕ���
			List list = query.list();
			
			for (Object object : list) {
				System.out.println(object.toString());
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

	

	//HQL����������Ӳ�ѯ
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
			
			
			//3-1.��ѯcustomer�������û���linkman����customer�й�ϵ�������û�
			Query query = session.createQuery("from Customer c left outer join c.setLinkMan");
			//3-2.���÷����õ������list���ϣ�����ָ�����ͣ�list������������
			List list = query.list();
			
			for (Object object : list) {
				System.out.println(object.toString());
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
	
	//HQL������������Ӳ�ѯ
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
			
			
			//3-1.��ѯcustomer����ϵ�������û�
			Query query = session.createQuery("from Customer c inner join fetch c.setLinkMan");
			//3-2.���÷����õ������list���ϣ�����ָ�����ͣ�list�������Ƕ���
			List list = query.list();
			
			for (Object object : list) {
				System.out.println(object.toString());
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

	

	//HQL��������Ӳ�ѯ
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
			
			
			//3-1.��ѯcustomer����ϵ�������û�
			Query query = session.createQuery("from Customer c inner join c.setLinkMan");
			//3-2.���÷����õ������list���ϣ�����ָ�����ͣ�list������������
			List list = query.list();
			
			for (Object object : list) {
				System.out.println(object.toString());
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
