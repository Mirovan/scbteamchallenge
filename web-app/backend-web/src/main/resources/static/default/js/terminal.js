$(function () {
    makeChart();
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
 * Построение графика цены
 * */
function makeChart() {
    var options = {
        series: [{
            data: seriesData
        }],
        chart: {
            type: 'candlestick',
            height: 290,
            id: 'candles',
            toolbar: {
                autoSelected: 'pan',
                show: false
            },
            zoom: {
                enabled: false
            },
        },
        plotOptions: {
            candlestick: {
                colors: {
                    upward: '#3C90EB',
                    downward: '#DF7D46'
                }
            }
        },
        xaxis: {
            type: 'datetime'
        }
    };

    var chart = new ApexCharts(document.querySelector("#chart-candlestick"), options);
    chart.render();

    var optionsBar = {
        series: [{
            name: 'volume',
            data: seriesDataLinear
        }],
        chart: {
            height: 160,
            type: 'bar',
            brush: {
                enabled: true,
                target: 'candles'
            },
            selection: {
                enabled: true,
                xaxis: {
                    min: new Date('20 Jan 2017').getTime(),
                    max: new Date('10 Dec 2017').getTime()
                },
                fill: {
                    color: '#ccc',
                    opacity: 0.4
                },
                stroke: {
                    color: '#0D47A1',
                }
            },
        },
        dataLabels: {
            enabled: false
        },
        plotOptions: {
            bar: {
                columnWidth: '80%',
                colors: {
                    ranges: [{
                        from: -1000,
                        to: 0,
                        color: '#F15B46'
                    }, {
                        from: 1,
                        to: 10000,
                        color: '#FEB019'
                    }],

                },
            }
        },
        stroke: {
            width: 0
        },
        xaxis: {
            type: 'datetime',
            axisBorder: {
                offsetX: 13
            }
        },
        yaxis: {
            labels: {
                show: false
            }
        }
    };

    var chartBar = new ApexCharts(document.querySelector("#chart-bar"), optionsBar);
    chartBar.render();
}