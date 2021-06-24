package api.dao;

import java.util.List;

public interface AbstractDao {
    <T> void set(List<T> list);
}
