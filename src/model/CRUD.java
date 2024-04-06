package model;

import java.util.List;

public interface CRUD {
    public Object create(Object obj);

    public List<Object> findAll();
    public Boolean update(Object obj);
    public Boolean delete(Object obj);
}
