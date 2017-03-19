package com.dimendiondata.server_management.dao;

import java.util.List;

import com.dimendiondata.server_management.model.Server;

public interface ServerDao {
	
	public void insert(Server server);
	
	public void update(Server server);
	
	public void delete (int id);
	
	public int count();
	
	public List<Server> list();
}
