$(function () {
    showChart();
    showUserOrders();
    setInterval(loadOrderBook, 500);
});


/**
 * Запрос на получение фооеров для стакана
 * */
function loadOrderBook() {
    $.get("/api/orderbook", {tiker: "USDRUB_TOM"})
        .done(function (data) {
            let tableData = makeStakanData(data["offers"]);

            $("#stakan").html(tableData);
        });
}


/**
 * Отображение офферов в стакане
 * */
function makeStakanData(orders) {
    let tableData = "";
    for (let item in orders) {
        let type = orders[item]["direction"] == "BUY" ? "bid" : "ask";
        tableData += "<tr class='" + type + "'>" +
            "<td>" + orders[item]["price"] + "</td>" +
            "<td>" + orders[item]["volume"] + "</td>" +
            "</tr>";
    }
    return tableData;
}


/**
 * Запрос данных графика и отображение
 * */
function showChart() {
    $.get("/api/chart", {tiker: "USD000UTSTOM", timeframe: 10})
        .done(function (data) {
            makeChart(data);
        });
}

/**
 * Построение графика цены
 * */
function makeChart(data) {
    // let xStart = null;
    // let xEnd = null;
    // let yStart = 99999999;
    // let yEnd = 0;

    //Формируем данные
    let seriesData = [];
    for (let item in data) {
        let xData = Date.parse(data[item]["begin"]);

        // if (xStart == null) xStart = xData;
        // xEnd = xData;
        // yStart = Math.min(yStart, data[item]["low"]);
        // yEnd = Math.max(yEnd, data[item]["high"]);

        let yData = [data[item]["open"], data[item]["high"], data[item]["low"], data[item]["close"]];
        seriesData.push({x: xData, y: yData});
    }

    //Свечной график
    let options = {
        series: [{
            data: seriesData
        }],
        chart: {
            type: 'candlestick',
            height: 290,
            id: 'candles',
            // toolbar: {
            //     autoSelected: 'pan',
            //     show: false
            // },
            zoom: {
                enabled: false
            },
        },
        plotOptions: {
            candlestick: {
                colors: {
                    upward: '#12b600',
                    downward: '#ff0e00'
                }
            }
        },
        xaxis: {
            type: 'datetime',
            labels: {
                datetimeUTC: false
            }
        },
        tooltip: {
            custom: function ({series, seriesIndex, dataPointIndex, w}) {
                let tooltipData = w.globals.initialSeries[seriesIndex].data[dataPointIndex];
                let dateFormat = new Date(tooltipData.x);
                let dateTime = dateFormat.getDate() +
                    "." + (dateFormat.getMonth() + 1) +
                    "." + dateFormat.getFullYear() +
                    " " + dateFormat.getHours() +
                    ":" + dateFormat.getMinutes();

                return '<ul>' +
                    '<li><b>Open</b>: ' + tooltipData.y[0] + '</li>' +
                    '<li><b>High</b>: ' + tooltipData.y[1] + '</li>' +
                    '<li><b>Low</b>: ' + tooltipData.y[2] + '</li>' +
                    '<li><b>Close</b>: ' + tooltipData.y[3] + '</li>' +
                    '<li><b>Date</b>: ' + dateTime + '</li>' +
                    '</ul>';
            }
        }
    };

    let chart = new ApexCharts(document.querySelector("#chart-candlestick"), options);
    chart.render();

    // let optionsBar = {
    //     series: [{
    //         name: 'volume',
    //         data: seriesDataLinear
    //     }],
    //     chart: {
    //         height: 160,
    //         type: 'bar',
    //         brush: {
    //             enabled: true,
    //             target: 'candles'
    //         },
    //         selection: {
    //             enabled: true,
    //             xaxis: {
    //                 // min: new Date('10 Nov 2022').getTime(),
    //                 // max: new Date('11 Nov 2022').getTime()
    //                 min: xStart,
    //                 max: xEnd
    //             },
    //             fill: {
    //                 color: '#ccc',
    //                 opacity: 0.4
    //             },
    //             stroke: {
    //                 color: '#0D47A1',
    //             }
    //         },
    //     },
    //     dataLabels: {
    //         enabled: false
    //     },
    //     plotOptions: {
    //         bar: {
    //             columnWidth: '80%',
    //             colors: {
    //                 ranges: [{
    //                     from: -1000,
    //                     to: 0,
    //                     color: '#F15B46'
    //                 }, {
    //                     from: 1,
    //                     to: 10000,
    //                     color: '#FEB019'
    //                 }],
    //
    //             },
    //         }
    //     },
    //     stroke: {
    //         width: 0
    //     },
    //     xaxis: {
    //         type: 'datetime',
    //         axisBorder: {
    //             offsetX: 13
    //         }
    //     },
    //     yaxis: {
    //         labels: {
    //             show: false
    //         }
    //     }
    // };
    //
    // var chartBar = new ApexCharts(document.querySelector("#chart-bar"), optionsBar);
    // chartBar.render();
}


/**
 * Загрузка данных о заявках пользователя
 * */
function showUserOrders() {
    $.get("/api/user-orders")
        .done(function (data) {
            let tableData = "";

            for (let item in data) {
                tableData +=
                    "<tr>" +
                    "<td>" + data[item]["id"] + "</td>" +
                    "<td>" + data[item]["dateTime"] + "</td>" +
                    "<td>" + data[item]["tiker"] + "</td>" +
                    "<td>" + data[item]["operation"] + "</td>" +
                    "<td>" + data[item]["price"] + "</td>" +
                    "<td>" + data[item]["lot"] + "</td>" +
                    "<td>" + data[item]["volume"] + "</td>" +
                    "<td>" + data[item]["status"] + "</td>" +
                    "</tr>"
            }

            $("#user-orders").html(tableData);
        });
}