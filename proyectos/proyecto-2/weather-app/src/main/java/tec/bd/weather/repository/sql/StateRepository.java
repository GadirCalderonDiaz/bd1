package tec.bd.weather.repository.sql;

import tec.bd.weather.entity.Country;
import tec.bd.weather.entity.State;
import tec.bd.weather.repository.Repository;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static tec.bd.weather.repository.sql.Queries.UPDATE_COUNTRY_PROC;

public class StateRepository implements Repository<State, Integer> {


    private final DataSource dataSource;

    public StateRepository(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    @Override
    public Optional findById(Integer stateID) {
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.FIND_ALL_STATES_PROC)) {

            stmt.setInt(1, stateID);
            var rs = stmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                var state = new State(rs.getInt(1), rs.getString(2), rs.getInt(3));
                return Optional.of(state);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve states", e);
        }
    }

    @Override
    public List<State> findAll() {
        List<State> allStates = new ArrayList<>();
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.FIND_ALL_STATES_PROC)) {

            stmt.setInt(1, 0);
            var rs = stmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                var state = new State(rs.getInt(1), rs.getString(2), rs.getInt(3));
                allStates.add(state);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve states", e);
        }

        return allStates;
    }

    @Override
    public List<State> findLogs(Integer n) {
        return null;
    }

    @Override
    public List<State> findAllDate(String forecastDate) {
        return null;
    }

    @Override
    public List<State> findAllZipCode(Integer zipCode) {
        return null;
    }

    @Override
    public List<State> findAllCityID(Integer zipCode) {
        return null;
    }

    @Override
    public State save(State state) {

        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.CREATE_STATE_PROC_NAME)){

            stmt.setString("p_stateName",state.getStateName());
            stmt.setInt("p_countryID", state.getCountryID());
            stmt.registerOutParameter("p_new_stateID", Types.INTEGER);
            stmt.executeUpdate();

            var newState = new State(stmt.getInt("p_new_stateID"), state.getStateName(),state.getCountryID());

            return  newState;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to create State", e);
        }


    }

    @Override
    public void delete(Integer stateID) {
        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.DELETE_STATE_BY_ID_PROC)){

            stmt.setInt(1, stateID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to Delete State", e);
        }
    }

    @Override
    public State update(State state) {

        try (Connection connection = this.dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(Queries.UPDATE_STATE_PROC)) {

            stmt.setInt(1, state.getId());
            stmt.setString(2, state.getStateName());
            stmt.setInt(3, state.getCountryID());
            stmt.executeUpdate();

            return state;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to Update State", e);
        }

    }
}
