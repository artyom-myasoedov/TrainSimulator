package myasoedov.cs.models.storages;

import myasoedov.cs.models.abstractWagons.Wagon;

import java.sql.SQLException;
import java.util.UUID;

public interface Storage<T> {

    boolean save(T item) throws SQLException;

    T get(UUID id) throws SQLException;

    boolean delete(UUID id) throws SQLException;
}
