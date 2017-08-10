package com.jiudianlianxian.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 
 * Title: HibernateUtils
 * Description: hibernate������
 * Company: �����ŵ�������Ϣ�������޹�˾
 * ProjectName: hibernate_day01
 * @author fupengpeng
 * @date 2017��8��7�� ����3:11:17
 */
public class HibernateUtils {

	
	/**
	 * ��������SessionFactory����
	 *     SessionFactory�����������Դ����ֻ����һ��SessionFactory����
	 */
	private static final Configuration configuration;
	private static final SessionFactory sessionFactory;
	
	static {
		configuration = new Configuration().configure();
		sessionFactory = configuration.buildSessionFactory();
	}
	
	//�ṩ���������뱾���̰߳󶨵�session����ķ���
	public static Session getSessionObject(){
		return sessionFactory.getCurrentSession();  
	}
	
	//�ṩ��������SessionFactory����
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public static void main(String[] args) {
		
	}
	
}
