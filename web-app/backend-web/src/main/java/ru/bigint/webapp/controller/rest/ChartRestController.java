package ru.bigint.webapp.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bigint.webapp.dto.chart.Candle;
import ru.bigint.webapp.service.iface.ChartService;

import java.util.List;

@Api(tags = {"График"})
@Tag(name = "График", description = "Сервис для работы со графиком")
@RestController
@RequestMapping(value = "/api/chart")
public class ChartRestController {

    private final ChartService chartService;

    public ChartRestController(ChartService chartService) {
        this.chartService = chartService;
    }

    @GetMapping
    public List<Candle> getChart(@RequestParam(name = "tiker") String tiker,
                                     @RequestParam(name = "timeframe", defaultValue = "60") Integer timeframe) {
        return chartService.getChart(tiker, timeframe);
    }

}
