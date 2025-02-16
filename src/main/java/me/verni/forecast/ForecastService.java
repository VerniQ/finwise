package me.verni.forecast;

import me.verni.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForecastService {
    private ForecastRepository forecastRepository;

    public ForecastService(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    public Forecast saveForecast(Forecast forecast){
        return forecastRepository.save(forecast);
    }

    public List<Forecast> findByUserId(Long userId){
        return forecastRepository.findByUserId(userId);
    }

    public List<Forecast> findByUser(User user){
        return forecastRepository.findByUser(user);
    }
    public Forecast updateForecast(Forecast forecast){
        return forecastRepository.save(forecast);
    }
    public void deleteForecast(Long id){
        forecastRepository.deleteById(id);
    }
}
