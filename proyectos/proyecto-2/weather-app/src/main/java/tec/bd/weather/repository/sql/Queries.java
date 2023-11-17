package tec.bd.weather.repository.sql;

public class Queries {

    public static final String FIND_ALL_FORECAST = "SELECT " +
            "forecast_id, country_name, city_name, zip_code, forecast_date, temperature " +
            "FROM forecast";
    public static final String INSERT_NEW_FORECAST = "INSERT INTO " +
            "forecast(country_name, city_name, zip_code, forecast_date, temperature) " +
            "VALUES(?, ?, ?, ?, ?)";

    public static final String FIND_FORECAST_BY_ID = "SELECT " +
            "forecast_id, country_name, city_name, zip_code, forecast_date, temperature " +
            "FROM forecast WHERE forecast_id = ?";

    public static final String DELETE_FORECAST_BY_ID = "DELETE FROM forecast WHERE forecast_id = ?";

    public static final String UPDATE_FORECAST = "UPDATE forecast SET " +
            "country_name = ? , city_name = ?, zip_code = ? , forecast_date = ?, temperature = ? " +
            "WHERE forecast_id = ?";


    //Country
    public static final String CREATE_COUNTRY_PROC_NAME = "call CreateNewCountry(?, ?)";

    public static final String FIND_ALL_COUNTRIES_PROC = "call findAllCountries(?)";

    public static final String DELETE_COUNTRY_BY_ID_PROC = "call deleteCountry(?)";

    public static final String UPDATE_COUNTRY_PROC = "call updateCountry(?, ?)";

    //States
    public static final String CREATE_STATE_PROC_NAME = "call CreateNewState(?, ?, ?)";

    public static final String FIND_ALL_STATES_PROC = "call findAllStates(?)";

    public static final String DELETE_STATE_BY_ID_PROC = "call deleteState(?)";

    public static final String UPDATE_STATE_PROC = "call updateState(?, ?, ?)";

    //Cities
    public static final String CREATE_CITY_PROC_NAME = "call CreateNewCity(?, ?, ?, ?)";

    public static final String FIND_ALL_CITIES_PROC = "call findAllCities(?)";

    public static final String DELETE_CITY_BY_ID_PROC = "call deleteCity(?)";

    public static final String UPDATE_CITY_PROC = "call updateCity(?, ?, ?, ?)";
    //////////////////////////
    //forecast
    /////////////////////////////////
    public static final String CREATE_FORECAST_PROC_NAME = "call CreateNewForecast(?, ?, ?, ?, ?)";

    public static final String FIND_ZIPCODE = "call findZipcode(?, ?)";

    public static final String FIND_CITYNAME = "call findCityName(?, ?)";
    public static final String FIND_STATEID = "call findStateID(?, ?)";
    public static final String FIND_STATENAME = "call findStateName(?, ?)";
    public static final String FIND_COUNTRYID = "call findCountryID(?, ?)";
    public static final String FIND_COUNTRYNAME = "call findCountryName(?, ?)";


    public static final String DELETE_FORECAST_BY_ID_PROC = "call deleteForecast(?)";

    public static final String FIND_ALL_FORECASTDB_PROC = "call findAllForecast(?)";

    public static final String FIND_ALL_FORECASTDB_byZipCode_PROC = "call findAllForecastZipCode(?)";

    public static final String FIND_ALL_FORECASTDB_BYCITYID_PROC = "call findAllForecastCityCode(?)";

    public static final String UPDATE_FORECAST_PROC = "call updateForecast(?, ?, ?, ?, ?)";


    public static final String FIND_FORECASTBYID_PROC = "call findForecastBYID(?)";
    ///////////////////////////
    //logs
    ///////////////
    public static final String FIND_FORECASTLOGS_PROC = "call GetLastForecastLogs(?)";

}