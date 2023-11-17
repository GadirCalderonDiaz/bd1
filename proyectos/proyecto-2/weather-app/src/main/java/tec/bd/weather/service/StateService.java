package tec.bd.weather.service;



import tec.bd.weather.entity.State;

import java.util.List;
import java.util.Optional;

public interface StateService {


    State newState(String stateName, Integer countryID);

    List<State> getAllStates();

    Optional<State> getStateByID(int stateID);

    void removeByStateID(int stateID);

    State updateState(State state);
}








