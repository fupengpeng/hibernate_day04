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
 * Description: hibernate�ĳ�����ѯ��ʽ��Ӧ��
 * Company: �����ŵ�������Ϣ�������޹�˾
 * ProjectName: hibernate_day04
 * @author fupengpeng
 * @date 2017��8��9�� ����10:25:24
 */
public class HibernateDemoOne {
	

	
	//OID��ѯ
	@Test
	public void testSelectOID(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.��ʹ��SessionFactory����Session����
			//��⣺������jdbc���������ݿ�
			session = HibernateUtils.getSessionObject();
			//2.����������
			transaction = session.beginTransaction();
			//3.��д�����crud����
			
			
			//��ѯcid=3�Ŀͻ�
			Customer customer = session.get(Customer.class, 3);
			
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
	
	
	//���󵼺���ѯ
	@Test
	public void testSelectObject(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.��ʹ��SessionFactory����Session����
			//��⣺������jdbc���������ݿ�
			session = HibernateUtils.getSessionObject();
			//2.����������
			transaction = session.beginTransaction();
			//3.��д�����crud����
			
			
			//��ѯcid=3�Ŀͻ����ڲ�ѯ����������ϵ��
			//01.��ѯcid=3�Ŀͻ�
			Customer customer = session.get(Customer.class, 3);
			
		    //02.�ٲ�ѯcid=3�Ŀͻ���������ϵ��
			//ͨ���ͻ�����ϵ�����Ի�ȡ�����е���ϵ��
			Set<LinkMan> linkMans = customer.getSetLinkMan();
			for (LinkMan linkMan : linkMans) {
				System.out.println(linkMan);
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
	
	
	
	//һ�Զ�ļ������渴��д���������ã�
	@Test
	public void testAddDemo1(){
		Session session = null;
		Transaction transaction = null;
		try {
			
			//1.��ʹ��SessionFactory����Session����
			//��⣺������jdbc���������ݿ�
			session = HibernateUtils.getSessionObject();
			//2.����������
			transaction = session.beginTransaction();
			//3.��д�����crud����
			//���һ���ͻ���Ϊ�ͻ����һ����ϵ��
			//01.����һ���ͻ�����ϵ��
			Customer customer = new Customer();
			customer.setCustName("�ŵ�����");
			customer.setCustLevel("vip");
			customer.setCustMobile("999");
			customer.setCustPhone("110");
			customer.setCustSource("����");
			
			LinkMan linkMan = new LinkMan();
			linkMan.setLkm_name("����");
			linkMan.setLkm_gender("��");
			linkMan.setLkm_phone("911");
			
		    //02.�����ͻ��������ϵ�˶���֮��Ĺ�ϵ
			//02-1.����ϵ�˷ŵ��ͻ�ʵ��������set������ȥ
			customer.getSetLinkMan().add(linkMan);
			//02-2.�ѿͻ�����ŵ���ϵ�˿ͻ���������ȥ
			linkMan.setCustomer(customer);
			
			//03.�������ݵ����ݿ�
			session.save(customer);
			session.save(linkMan);
			
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
