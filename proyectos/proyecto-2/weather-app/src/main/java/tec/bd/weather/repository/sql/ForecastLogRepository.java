package tec.bd.weather.repository.sql;

import tec.bd.weather.entity.ForecastDB;
import tec.bd.weather.entity.Logs;
import tec.bd.weather.repository.Repository;
import tec.bd.weather.service.ForecastLog;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class ForecastLogRepository implements Repository<Logs, Integer> {


    private final DataSource dataSource;

    public ForecastLogRepository(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    @Override
    public Optional findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Logs> findAll() {
        return null;
    }


    ////////////
    @Override
    public List<Logs> findLogs(Integer n) {
        List<Logs> allForecastsLogs = new ArrayList<>();
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.FIND_FORECASTLOGS_PROC)) {

            stmt.setInt(1, n);
            var rs = stmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                var forecastLog = new Logs(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
                allForecastsLogs.add(forecastLog);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve Forecasts Logs", e);
        }

        return allForecastsLogs;
    }

    @Override
    public List<Logs> findAllDate(String forecastDate) {
        return null;
    }

    @Override
    public List<Logs> findAllZipCode(Integer zipCode) {
        return null;
    }

    @Override
    public List<Logs> findAllCityID(Integer zipCode) {
        return null;
    }

    ///////////////






    @Override
    public Logs save(Logs logs) {
        return null;
    }


    @Override
    public void delete(Integer integer) {

    }

    @Override
    public Logs update(Logs source) {
        return null;
    }


}
