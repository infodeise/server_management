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
		Query query = session.createQuery("SELECT COUNT(*) FROM Server");
		Long result = (Long) query.uniqueResult();
		session.close();
		return result.intValue();
	}

	public List<Server> list() {
		Session session = this.sessionFactory.openSession();
		List<Server> serverList = session.createQuery("FROM Server").list();
		session.close();
		return serverList;
	}

	public List<Server> listPaging(int first, int limit) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("FROM Server");
        query.setFirstResult(first);
        query.setMaxResults(limit);
        List<Server> serverList = query.list();
		return serverList;
	}

	public List<Server> listFiltering(int first, int limit, String query) {
		Session session = this.sessionFactory.openSession();
		Query q = session.createQuery("FROM Server WHERE name LIKE :query");
		q.setParameter("query", "%"+query+"%");
        q.setFirstResult(first);
        q.setMaxResults(limit);
        List<Server> serverList = q.list();
		return serverList;
	}

}
