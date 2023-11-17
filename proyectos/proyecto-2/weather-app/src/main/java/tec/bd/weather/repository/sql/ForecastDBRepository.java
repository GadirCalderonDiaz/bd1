package tec.bd.weather.repository.sql;


import tec.bd.weather.entity.Country;
import tec.bd.weather.entity.ForecastDB;
import tec.bd.weather.entity.State;
import tec.bd.weather.repository.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ForecastDBRepository implements Repository<ForecastDB, Integer> {

    private final DataSource dataSource;

    public ForecastDBRepository(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    @Override
    public Optional findById(Integer forecastID) {
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.FIND_FORECASTBYID_PROC)) {

            stmt.setInt(1, forecastID);
            var rs = stmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                var forecast = new ForecastDB(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(5), rs.getFloat(4));
                return Optional.of(forecast);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve forecasts", e);
        }
    }

    @Override
    public List<ForecastDB> findAll() {
        List<ForecastDB> allForecasts = new ArrayList<>();
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.FIND_ALL_FORECASTDB_PROC)) {

            stmt.setInt(1, 0);
            var rs = stmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                var forecast = new ForecastDB(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getFloat(5));
                allForecasts.add(forecast);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve Forecasts", e);
        }

        return allForecasts;
    }

    @Override
    public List<ForecastDB> findLogs(Integer n) {
        return null;
    }


    public List<ForecastDB> findAllDate(String forecastDate) {
        List<ForecastDB> allForecasts = new ArrayList<>();
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.FIND_ALL_FORECASTDB_PROC)) {

            stmt.setString("p_forecastDate", forecastDate);
            var rs = stmt.executeQuery();
            // loop through the result set
            while (rs.next()) {
                var stateID = findStateID(rs.getInt(2));
                var countryID = findCountryID(stateID);
                var forecast = new ForecastDB(rs.getInt(1), rs.getInt(3),findCityName(rs.getInt(2)),stateName(stateID),findCountryName(countryID), rs.getString(5), rs.getFloat(4));
                allForecasts.add(forecast);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve Forecasts", e);
        }

        return allForecasts;
    }

    public List<ForecastDB> findAllZipCode(Integer zipcode) {
        List<ForecastDB> allForecasts = new ArrayList<>();
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.FIND_ALL_FORECASTDB_byZipCode_PROC)) {

            stmt.setInt("p_zipCode", zipcode);
            var rs = stmt.executeQuery();
            // loop through the result set
            while (rs.next()) {
                var stateID = findStateID(rs.getInt(2));
                var countryID = findCountryID(stateID);
                var forecast = new ForecastDB(rs.getInt(1), rs.getInt(3),findCityName(rs.getInt(2)),stateName(stateID),findCountryName(countryID), rs.getString(5), rs.getFloat(4));
                allForecasts.add(forecast);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve Forecasts", e);
        }

        return allForecasts;
    }

    public List<ForecastDB> findAllCityID(Integer cityID) {
        List<ForecastDB> allForecasts = new ArrayList<>();
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.FIND_ALL_FORECASTDB_BYCITYID_PROC)) {

            stmt.setInt("p_cityCode", cityID);
            var rs = stmt.executeQuery();
            // loop through the result set
            while (rs.next()) {
                var stateID = findStateID(rs.getInt(2));
                var countryID = findCountryID(stateID);
                var forecast = new ForecastDB(rs.getInt(1), rs.getInt(3),findCityName(rs.getInt(2)),stateName(stateID),findCountryName(countryID), rs.getString(5), rs.getFloat(4));
                allForecasts.add(forecast);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve Forecasts", e);
        }

        return allForecasts;
    }


    @Override
    public ForecastDB save(ForecastDB forecast) {
        ForecastDB forecastR = null;
        try {

            Connection connection = this.dataSource.getConnection();
            try {
                CallableStatement stmt = connection.prepareCall(Queries.CREATE_FORECAST_PROC_NAME);

                connection.setAutoCommit(false);

                stmt.setInt("p_cityID", forecast.getCityID());
                stmt.setInt("p_zipcode", findZip(forecast.getCityID()));
                stmt.setFloat("p_temperature", forecast.getTemperature());
                stmt.setString("p_fecha", forecast.getForecastDate());
                stmt.registerOutParameter("p_new_forecastID", Types.INTEGER);
                stmt.executeUpdate();

                var newForecast = new ForecastDB(stmt.getInt("p_new_forecastID"), forecast.getCityID(), findZip(forecast.getCityID()), forecast.getForecastDate(), forecast.getTemperature());
                forecastR = newForecast;

                connection.commit();

                return newForecast;

            } catch (SQLException e) {
                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (SQLException rollbackException) {
                        rollbackException.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
        throw new RuntimeException("Failed to create State", e);
        }

        return forecastR;
    }

    public Integer findZip(Integer ID){
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.FIND_ZIPCODE)){

            stmt.setInt("p_cityID",ID);
            stmt.registerOutParameter("p_new_zipcodeID", Types.INTEGER);
            stmt.executeUpdate();

            var zipcode = stmt.getInt("p_new_zipcodeID");

            return  zipcode;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to return zip", e);
        }
    }

    public String findCityName(Integer cityID){
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.FIND_CITYNAME)){

            stmt.setInt("p_cityID",cityID);
            stmt.registerOutParameter("p_cityName", Types.CHAR);
            stmt.executeUpdate();

            var cityname = stmt.getString("p_cityName");

            return cityname;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to return cityName", e);
        }
    }

    public Integer findStateID(Integer cityID){
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.FIND_STATEID)){

            stmt.setInt("p_cityID",cityID);
            stmt.registerOutParameter("p_stateID", Types.INTEGER);
            stmt.executeUpdate();

            var stateID = stmt.getInt("p_stateID");

            return  stateID;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to return StateID", e);
        }
    }

    public String stateName(Integer stateID){
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.FIND_STATENAME)){

            stmt.setInt("p_stateID",stateID);
            stmt.registerOutParameter("p_stateName", Types.CHAR);
            stmt.executeUpdate();

            var stateName = stmt.getString("p_stateName");

            return  stateName;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to return StateName", e);
        }
    }
    public Integer findCountryID(Integer stateID){
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.FIND_COUNTRYID)){

            stmt.setInt("p_stateID",stateID);
            stmt.registerOutParameter("p_countryID", Types.INTEGER);
            stmt.executeUpdate();

            var countryID = stmt.getInt("p_countryID");

            return  countryID;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to return CountryID", e);
        }
    }

    public String findCountryName(Integer countryID){
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.FIND_COUNTRYNAME)){

            stmt.setInt("p_countryID",countryID);
            stmt.registerOutParameter("p_countryName", Types.CHAR);
            stmt.executeUpdate();

            var countryName= stmt.getString("p_countryName");

            return  countryName;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to return StateID", e);
        }
    }





    @Override
    public void delete(Integer forecastID) {
        try {
            Connection connection = this.dataSource.getConnection();
            try {
                CallableStatement stmt = connection.prepareCall(Queries.DELETE_FORECAST_BY_ID_PROC);

                connection.setAutoCommit(false);
                stmt.setInt(1, forecastID);
                stmt.executeUpdate();
                connection.commit();

            } catch (SQLException e) {
                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (SQLException rollbackException) {
                        rollbackException.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to Delete Forecast", e);
        }
    }

    @Override
    public ForecastDB update(ForecastDB forecast) {
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.UPDATE_FORECAST_PROC)) {

            stmt.setInt(1, forecast.getId());
            stmt.setInt(2, forecast.getCityID());
            stmt.setInt(3, findZip(forecast.getCityID()));
            forecast.setZipcode(findZip(forecast.getCityID()));
            stmt.setFloat(4, forecast.getTemperature());
            stmt.setString(5, forecast.getForecastDate());
            stmt.executeUpdate();

            return forecast;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to Update city, maybe zipCode ALREADY exists in DataBase", e);
        }
    }
}


