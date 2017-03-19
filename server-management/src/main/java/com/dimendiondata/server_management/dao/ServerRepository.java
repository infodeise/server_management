package com.dimendiondata.server_management.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dimendiondata.server_management.model.Server;

/**
 * This class is responsible to implement the operations related Server
 * repository
 * 
 * @author deise
 *
 */
public class ServerRepository implements ServerDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void insert(Server server) {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(server);
		transaction.commit();
		session.close();
	}

	public void update(Server server) {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(server);
		transaction.commit();
		session.close();
	}

	public void delete(int id) {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Server server = new Server();
		server.setId(id);
		session.delete(server);
		transaction.commit();
		session.close();
	}

	public int count() {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("select count(*) from Server");
		Long result = (Long) query.uniqueResult();
		session.close();
		return result.intValue();
	}

	public List<Server> list() {
		Session session = this.sessionFactory.openSession();
		List<Server> serverList = session.createQuery("from Server").list();
		session.close();
		return serverList;
	}

}
