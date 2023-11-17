package tec.bd.weather.repository.sql;

import tec.bd.weather.entity.City;
import tec.bd.weather.entity.Country;
import tec.bd.weather.repository.Repository;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CityRepository implements Repository<City, Integer> {


    private final DataSource dataSource;

    public CityRepository(DataSource dataSource) {

        this.dataSource = dataSource;
    }


    @Override
    public Optional findById(Integer cityID) {
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.FIND_ALL_CITIES_PROC)) {

            stmt.setInt(1, cityID);
            var rs = stmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                var city = new City(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
                return Optional.of(city);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve cities", e);
        }
    }

    @Override
    public List<City> findAll() {
        List<City> allCities = new ArrayList<>();
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.FIND_ALL_CITIES_PROC)) {

            stmt.setInt(1, 0);
            var rs = stmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                var city = new City(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
                allCities.add(city);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve cities", e);
        }

        return allCities;



    }

    @Override
    public List<City> findLogs(Integer n) {
        return null;
    }

    @Override
    public List<City> findAllDate(String forecastDate) {
        return null;
    }

    @Override
    public List<City> findAllZipCode(Integer zipCode) {
        return null;
    }

    @Override
    public List<City> findAllCityID(Integer zipCode) {
        return null;
    }

    @Override
    public City save(City city) {
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.CREATE_CITY_PROC_NAME)){

            stmt.setString("p_cityName",city.getCityName());
            stmt.setInt("p_zipcode", city.getZipcode());
            stmt.setInt("p_stateID", city.getStateID());
            stmt.registerOutParameter("p_new_cityID", Types.INTEGER);
            stmt.executeUpdate();

            var newCity = new City(stmt.getInt("p_new_cityID"), city.getCityName(), city.getZipcode(), city.getStateID());

            return  newCity;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to create City, already CityName in DataBase", e);
        }
    }

    @Override
    public void delete(Integer cityID) {

        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.DELETE_CITY_BY_ID_PROC)){

            stmt.setInt(1, cityID);
            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException("Failed to Delete city, Maybe this City has a forecast Associated", e);
        }


    }

    @Override
    public City update(City city) {
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.UPDATE_CITY_PROC)) {

            stmt.setInt(1, city.getId());
            stmt.setString(2, city.getCityName());
            stmt.setInt(3, city.getZipcode());
            stmt.setInt(4, city.getStateID());
            stmt.executeUpdate();

            return city;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to Update city, maybe zipCode ALREADY exists in DataBase", e);
        }
    }
}
