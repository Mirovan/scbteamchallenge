let chart = null;

$(function () {
    showUserOrders();
    showChart();
    loadOrderBook();
    orderBookClick();

    setInterval(showChart, 3000);
    setInterval(loadOrderBook, 700);
});


/**
 * Запрос на получение оферов для стакана
 * */
function loadOrderBook() {
    let tiker = $("#select-tiker").val();

    $.get("/api/orderbook", {tiker: tiker})
        .done(function (data) {
            let tableData = makeStakanData(data["offers"]);

            $("#stakan-offers").html(tableData);
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
    let tiker = $("#select-tiker").val();
    let timeframe = $("#select-timeframe").val();
    $.get("/api/chart", {tiker: tiker, timeframe: timeframe})
        .done(function (data) {
            makeChart(data);
        });
}

/**
 * Построение графика цены
 * */
function makeChart(data) {
    let xMin = null;
    let xMax = null;
    let yMin = 99999999;
    let yMax = 0;

    //Формируем данные
    let seriesData = [];
    for (let item in data) {
        let xData = Date.parse(data[item]["begin"]);

        if (xMin == null) xMin = xData;
        xMax = xData;
        yMin = Math.min(yMin, data[item]["low"]);
        yMax = Math.max(yMax, data[item]["high"]);

        let yData = [data[item]["open"], data[item]["high"], data[item]["low"], data[item]["close"]];
        seriesData.push({x: xData, y: yData});
    }

    //Обновляем дат границ
    // xMax = addMinutes(new Date(xMax), 30);
    yMin = parseInt(yMin * 100) / 100;
    yMax = parseInt(yMax * 100) / 100;

    //Свечной график
    let options = {
        series: [{
            name: 'candles',
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
            },
        },
        yaxis: {
            decimalsInFloat: 4,
            min: yMin,
            max: yMax,
            tickAmount: 4
            // min: parseFloat(yMin).toFixed(4),
            // max: parseFloat(yMax).toFixed(4)
        },
        tooltip: {
            custom: function ({series, seriesIndex, dataPointIndex, w}) {
                let tooltipData = w.globals.initialSeries[seriesIndex].data[dataPointIndex];
                let dateTime = formatDate(tooltipData.x);

                return '<ul>' +
                    '<li><b>Open</b>: ' + parseFloat(tooltipData.y[0]).toFixed(4) + '</li>' +
                    '<li><b>High</b>: ' + parseFloat(tooltipData.y[1]).toFixed(4) + '</li>' +
                    '<li><b>Low</b>: ' + parseFloat(tooltipData.y[2]).toFixed(4) + '</li>' +
                    '<li><b>Close</b>: ' + parseFloat(tooltipData.y[3]).toFixed(4) + '</li>' +
                    '<li><b>Date</b>: ' + dateTime + '</li>' +
                    '</ul>';
            }
        }
    };

    if (chart == null) {
        chart = new ApexCharts(document.querySelector("#chart-candlestick"), options);
        chart.render();
    } else {
        chart.updateOptions({
            series: [{
                name: 'candles',
                data: seriesData
            }],
            yaxis: {
                decimalsInFloat: 4,
                min: yMin,
                max: yMax,
                tickAmount: 4
            }
        });
    }

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
                    "<td>" + formatDate(data[item]["createdAt"]) + "</td>" +
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


function addMinutes(date, minutes) {
    const dateCopy = new Date(date.getTime());

    dateCopy.setTime(dateCopy.getTime() + minutes * 60 * 1000);

    return dateCopy;
}


/**
 * Преобразование даты из timestamp в формат dd-mm-yyyy hh:mm
 * */
function formatDate(dateTime) {
    let dateFormat = new Date(dateTime);
    let date = dateFormat.getDate();
    if (date.toString().length < 2) date = "0" + date;
    let month = dateFormat.getMonth() + 1;
    if (month.toString().length < 2) month = "0" + month;
    let year = dateFormat.getFullYear();
    let hours = dateFormat.getHours();
    if (hours.toString().length < 2) hours = "0" + hours;
    let minutes = dateFormat.getMinutes();
    if (minutes.toString().length < 2) minutes = "0" + minutes;

    return date +
        "." + month +
        "." + year +
        " " + hours +
        ":" + minutes;
}


/**
 * Отправка запроса на создание заявки пользователя
 * */
function createOrder() {
    let operation = $("#order-type-radio-1").is(':checked') ? "BUY" : "SELL";
    let tiker = $("#select-tiker").val();
    jQuery.ajax({
        url: "/api/user-orders/save",
        type: "POST",
        data: JSON.stringify({
            tiker: tiker,
            price: $("#order-price").val(),
            lot: $("#order-lot").val(),
            operation: operation
        }),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function () {
            showUserOrders();
        }
    });
}


/**
 * Событие нажатия на строку стакана - вставляет в поле ввода заявки цену
 * */
function orderBookClick() {
    $("#stakan-offers").on("click", "tr", function() {
        let values = $(this).find('td').map(function() {
            return $(this).text();
        }).get();
        $("#order-price").val(values[0]);
    });
}