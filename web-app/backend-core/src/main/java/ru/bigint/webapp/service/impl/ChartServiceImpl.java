package ru.bigint.webapp.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import ru.bigint.webapp.dto.chart.Candle;
import ru.bigint.webapp.service.iface.ChartService;
import ru.bigint.webapp.utils.HttpClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChartServiceImpl implements ChartService {

    private String url = "https://iss.moex.com/iss/engines/currency/markets/selt/securities/USD000UTSTOM/candles.json";

    @Override
    public List<Candle> getChart(String tiker, Integer timeframe) {
        List<Candle> candles = new ArrayList<>();

        try {
            String data = getJsonData();
            JSONObject jsonData = new JSONObject(data);
            JSONObject jsonCandles = jsonData.getJSONObject("candles");
            JSONArray dataArr = jsonCandles.getJSONArray("data");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            for (int i = 0; i < dataArr.length(); i++) {
                JSONArray candleData = (JSONArray) dataArr.get(i);
                Candle candle = new Candle(
                        Double.valueOf(candleData.get(0).toString()),
                        Double.valueOf(candleData.get(1).toString()),
                        Double.valueOf(candleData.get(2).toString()),
                        Double.valueOf(candleData.get(3).toString()),
                        Double.valueOf(candleData.get(4).toString()),
                        Double.valueOf(candleData.get(5).toString()),
                        LocalDateTime.parse((String) candleData.get(6), formatter),
                        LocalDateTime.parse((String) candleData.get(7), formatter)
                );
                candles.add(candle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return candles;
    }

    private String getJsonData() {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("from", "2022-11-11");
        params.put("till", "2022-11-12");
        params.put("interval", "10");
        params.put("start", "0");
        return HttpClient.sendGet(url, params);
    }
}
