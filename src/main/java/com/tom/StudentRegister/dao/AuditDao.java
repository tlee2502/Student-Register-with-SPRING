package com.sg.studentregister.dao;

public interface AuditDao {
    
    public void writeAuditEntry(String entry) throws PersistenceException;
}
