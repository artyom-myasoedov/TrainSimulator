package myasoedov.cs.models.storages;

import myasoedov.cs.models.Storable;

import java.sql.SQLException;

public interface Storage {

    boolean save(Storable item) throws SQLException;

    Storable get(Long id);

    boolean delete(Long id);

}
