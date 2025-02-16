package me.verni.forecast;

import me.verni.expense.Expense;
import me.verni.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forecasts")
public class ForecastController {
    private final ForecastService forecastService;

    public ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping("/user/{userId}")
    public List<Forecast> getForecastsByUser(@PathVariable Long userId) {
        return forecastService.findByUserId(userId);
    }

    @PostMapping
    public Forecast saveForecast(@RequestBody Forecast forecast) {
        return forecastService.saveForecast(forecast);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Forecast> updateExpense(@RequestBody Forecast forecast) {
        return ResponseEntity.ok(forecastService.updateForecast(forecast));
    }

    @DeleteMapping("/{id}")
    public void deleteForecast(@PathVariable Long id) {
        forecastService.deleteForecast(id);
    }
}
