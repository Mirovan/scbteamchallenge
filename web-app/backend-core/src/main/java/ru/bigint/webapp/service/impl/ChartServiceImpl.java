package ru.bigint.webapp.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import ru.bigint.webapp.dto.chart.Candle;
import ru.bigint.webapp.service.iface.ChartService;
import ru.bigint.webapp.utils.HttpClient;
import ru.bigint.webapp.utils.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ChartServiceImpl implements ChartService {

    private String url = "https://iss.moex.com/iss/engines/currency/markets/selt/securities/%TIKER%/candles.json";
    private List<Candle> candles;

    //Значения по умолчанию для загрузки данных графиков
    private String tiker;
    private int timeframe;
    private String fromDate = "2022-11-11";
    private String toDate = "2022-11-12";

    @Override
    public List<Candle> getChart(String tiker, Integer timeframe) {

        if (!Objects.equals(this.tiker, tiker) || this.timeframe != timeframe) {
            candles = null;
        }

        //Запрашиваем исторические свечи только один раз, далее берем из памяти
        try {
            if (candles == null) {
                this.tiker = tiker;
                this.timeframe = timeframe;
                candles = new ArrayList<>();
                String data = getJsonData(fromDate, toDate, timeframe, tiker);
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Отдаем только свечи с начала дня до времени NOW()
        List<Candle> res = new ArrayList<>();
        for (Candle candle : candles) {
            //Если свеча после текущей даты
            LocalDateTime fakeNow = LocalDateTime.of(candle.getBegin().toLocalDate(), LocalDateTime.now().toLocalTime());
            if (candle.getBegin().isAfter(fakeNow)) break;
            res.add(candle);
        }

        if (!res.isEmpty()) {
            updateLastCandle(res.get(res.size() - 1));
        } else {
            System.out.println("Нет свечей");
        }

        return res;
    }

    @Override
    public Candle getLastCandle() {
        if (candles != null && !candles.isEmpty()) {
            return candles.get(candles.size() - 1);
        } else {
            return null;
        }
    }

    @Override
    public Double getLastPrice() {
        Candle candle = getLastCandle();
        if (candle != null) {
            return candle.getClose();
        } else {
            return null;
        }
    }

    /*
     * Имитация сделок для последней свечи - свеча будет дергаться
     * */
    private void updateLastCandle(Candle candle) {
        int rnd1 = Util.getRandomNumber(1, 60);
        int rnd2 = Util.getRandomNumber(1, 60);
        Double newClose = candle.getClose() + candle.getClose() * (rnd1 - rnd2) / 100000d;
        newClose = Math.min(candle.getHigh(), newClose);
        newClose = Math.max(candle.getLow(), newClose);
        candle.setClose(newClose);
    }

    private String getJsonData(String fromDate, String toDate, int interval, String tiker) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("from", fromDate);
        params.put("till", toDate);
        params.put("interval", String.valueOf(interval));
        params.put("start", "0");
        return HttpClient.sendGet(url.replace("%TIKER%", tiker), params);
    }
}
