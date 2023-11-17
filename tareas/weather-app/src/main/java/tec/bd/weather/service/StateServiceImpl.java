package tec.bd.weather.service;

import tec.bd.weather.entity.Country;
import tec.bd.weather.entity.State;
import tec.bd.weather.repository.Repository;

import java.util.List;
import java.util.Optional;

public class StateServiceImpl implements StateService{


    private Repository<State, Integer> stateRepository;


    public StateServiceImpl(Repository<State, Integer> stateRepository){
        this.stateRepository = stateRepository;
    }

    @Override
    public State newState(String stateName, Integer countryID) {

        if(stateName.equals(" ")){
            throw new RuntimeException("The StateName its NULL");
        }
        var stateToBeSaved = new State(null, stateName, countryID);
        return this.stateRepository.save(stateToBeSaved);

    }

    @Override
    public List<State> getAllStates() {
        return this.stateRepository.findAll();
    }

    @Override
    public Optional<State> getStateByID(int stateID) {
        if(stateID > 0 ){
            return this.stateRepository.findById(stateID);
        }else{
            throw new RuntimeException("The stateID has to be greater than 0");

        }
    }

    @Override
    public void removeByStateID(int stateID) {
        var stateFROMDB = this.getStateByID(stateID);

        if(stateFROMDB.isEmpty()){
            throw new RuntimeException("The StateID:"+ stateID + "is not found");
        }
        this.stateRepository.delete(stateID);

    }

    @Override
    public State updateState(State state) {
        //validar si el countryID existe en la base de datos
        //si el nombre existe en la db

        var stateIDFromDB = this.getStateByID(state.getId());

        if(stateIDFromDB.isEmpty()){
            throw new RuntimeException("The StateID:"+ state.getId() + "is not found");
        }

        var stateUpdated = this.stateRepository.update(state);

        return stateUpdated;
    }

}
