//创建地图
var map = new AMap.Map('container', {
    zoom: 4,
    resizeEnable: true
});

//在地图中添加MouseTool插件
var mouseTool = new AMap.MouseTool(map);
AMap.event.addDomListener(document.getElementById('line'), 'click', function () {
    mouseTool.polyline();
}, false);

var c1 = AMap.event.addDomListener(document.getElementById('fj'), 'click', function (e) {
    var clickListener = AMap.event.addListener(map, "click", function (e) {
        var r = confirm("是否在地图上添加风机?");
        if (r == true) {
            jQuery(function ($) {
                $.ajax({
                    url: 'http://localhost/marker/domarker',
                    //type:'POST', //GET
                    async: true,    //或false,是否异步
                    data: {"lng": e.lnglat.getLng(), "lat": e.lnglat.getLat(), "fname": "风机测试"},
                    timeout: 5000,    //超时时间
                    dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                    beforeSend: function (xhr) {
                        console.log(xhr);
                        console.log('发送前');
                    },
                    success: function (data, textStatus, jqXHR) {
                        console.log(data);
                        console.log('发送成功');
                        console.log(textStatus);
                        console.log(jqXHR);
                    },
                    error: function (xhr, textStatus) {
                        console.log('错误');
                        console.log(xhr);
                        console.log(textStatus)
                    }
                });
            });
            new AMap.Marker({
                position: e.lnglat,
                content: '<div class="marker"></div>',
                title: '风机:' + e.lnglat.getLng() + ',' + e.lnglat.getLat(),
                map: map
            });
            if (clickListener) {
                AMap.event.removeListener(clickListener);
            }
        }

    });
}, false);


var c2 = AMap.event.addDomListener(document.getElementById('fc'), 'click', function (e) {

    var clickListener2 = AMap.event.addListener(map, "click", function (e) {

        var r = confirm("是否在地图上添加风场?");
        if (r == true) {
            jQuery(function ($) {
                $.ajax({
                    url: 'http://localhost/marker/domarker',
                    //type:'POST', //GET
                    async: true,    //或false,是否异步
                    data: {"lng": e.lnglat.getLng(), "lat": e.lnglat.getLat(), "fname": "风机测试"},
                    timeout: 5000,    //超时时间
                    dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                    beforeSend: function (xhr) {
                        console.log(xhr);
                        console.log('发送前');
                    },
                    success: function (data, textStatus, jqXHR) {
                        console.log(data);
                        console.log('发送成功');
                        console.log(textStatus);
                        console.log(jqXHR);
                    },
                    error: function (xhr, textStatus) {
                        console.log('错误');
                        console.log(xhr);
                        console.log(textStatus)
                    }
                });
            });
            new AMap.Marker({
                position: e.lnglat,
                content: '<div class="markerh"></div>',
                title: '风场:' + e.lnglat.getLng() + ',' + e.lnglat.getLat(),
                map: map
            });
            if (clickListener2) {
                AMap.event.removeListener(clickListener2);
            }
        }
    });
}, false);


function initPage(DistrictCluster, $) {

    var distCluster=districtcluster(DistrictCluster,map,[130000, 110000, 120000, 140000, 150000],'rgb(255,227,132)',1,'rgb(145,145,145)',null,currentLngLat);
    var distCluster2=districtcluster(DistrictCluster,map,[410000, 420000, 430000, 310000, 320000, 330000, 340000, 370000],'rgb(254,221,200)',1,'rgb(145,145,145)',null,currentLngLat2);
    var distCluster3=districtcluster(DistrictCluster,map,[210000, 220000, 230000],'rgb(199,254,253)',1,'rgb(145,145,145)',null,currentLngLat3);
    var distCluster4=districtcluster(DistrictCluster,map,[440000, 450000, 460000, 350000, 360000, 500000, 520000, 530000, 510000],'rgb(199,255,208)',1,'rgb(145,145,145)',null,currentLngLat4);
    var distCluster5=districtcluster(DistrictCluster,map,[540000],'rgb(212,213,242)',1,'rgb(145,145,145)',null,currentLngLat5);
    var distCluster6=districtcluster(DistrictCluster,map,[610000, 620000, 630000, 640000, 650000],'rgb(255,199,204)',1,'rgb(145,145,145)',null,currentLngLat6);
    var distCluster7=districtcluster(DistrictCluster,map,[810000, 820000, 710000],'rgb(255,192,203)',1,'rgb(145,145,145)',null,currentLngLat7);

    var currentLngLat = null;
    var currentLngLat2 = null;
    var currentLngLat3 = null;
    var currentLngLat4 = null;
    var currentLngLat5 = null;
    var currentLngLat6 = null;
    var currentLngLat7 = null;
    window.distCluster = distCluster;
    window.distCluster2 = distCluster2;
    window.distCluster3 = distCluster3;
    window.distCluster4 = distCluster4;
    window.distCluster5 = distCluster5;
    window.distCluster6 = distCluster6;
    window.distCluster7 = distCluster7;

    $('<div id="loadingTip">加载数据，请稍候...</div>').appendTo(document.body);
    $.get('http://a.amap.com/amap-ui/static/data/10w.txt', function (csv) {
        $('#loadingTip').remove();
        var data = csv.split('\n');
        distCluster.setData(data);
        distCluster2.setData(data);
        distCluster3.setData(data);
        distCluster4.setData(data);
        distCluster5.setData(data);
        distCluster6.setData(data);
        distCluster7.setData(data);
    });
}


function districtcluster(DistrictCluster,map,codes,fillStyle,lineWidth,strokeStyle,hoverOptions,currentLngLat){
var distCluster=new DistrictCluster({
    map: map,
    topAdcodes: codes,
    autoSetFitView: false,
    getPosition: function (item) {
        if (!item) {
            return null;
        }
        var parts = item.split(',');
        return [parseFloat(parts[0]), parseFloat(parts[1])];
    },

    renderOptions: {
        featureStyle: {
            fillStyle: fillStyle,
            lineWidth: lineWidth,
            strokeStyle: strokeStyle,
            hoverOptions: hoverOptions
        },
        getFeatureStyle: function (feature, dataItems) {
            return {
                fillStyle: fillStyle
            };

        },

        clusterMarkerKeepConsistent: false,
        getClusterMarkerPosition: function (feature) {
            return currentLngLat;
        },
        getClusterMarker: function (feature, dataItems, recycledMarker) {
            var resultMarker = recycledMarker || new AMap.Marker({
                    topWhenClick: true,
                    offset: new AMap.Pixel(-20, -30),
                    content: null
                });

            return resultMarker;
        }
    }
});
return distCluster;
}


setpolygon();
//添加不同区域的颜色
AMapUI.load(['ui/geo/DistrictCluster', 'lib/$'], function (DistrictCluster, $) {

    window.DistrictCluster = DistrictCluster;
    initPage(DistrictCluster, $);
});
function a() {
    layer.open({
        type: 2,
        title: '信息',
        area: ['620px', '510px'],
        shadeClose: false,
        scrollbar: true,
        content: 'file:///D:/项目/高德地图/open.html'
    });
}

function b() {
    layer.open({
        type: 2,
        title: '信息',
        area: ['620px', '510px'],
        shadeClose: false,
        scrollbar: true,
        content: 'file:///D:/项目/高德地图/open1.html'
    });

}
var data = [{
    id: 'A',
    position: [89.8681640625, 40.9798980696],
    iconStyle: 'blue',
    title: '西北',
    desc: '西北'
}, {
    id: 'B',
    position: [116.0595714123, 42.3585433399],
    iconStyle: 'blue',
    title: '华北',
    desc: '华北'
}, {
    id: 'C',
    position: [127.3535156250, 44.7779358963],
    iconStyle: 'blue',
    title: '东北',
    desc: '东北'
}, {
    id: 'D',
    position: [118.1469739974, 31.2409856647],
    iconStyle: 'blue',
    title: '华中',
    desc: '华中'
}, {
    id: 'E',
    position: [89.5166015625, 30.7512777763],
    iconStyle: 'blue',
    title: '西南',
    desc: '西南'
}, {
    id: 'F',
    position: [111.4013671875, 23.0392977478],
    iconStyle: 'blue',
    title: '华南',
    desc: '华南'
}
];
if (map.getZoom() == 4) {
    var mouseTool = new AMap.MouseTool(map);
    document.getElementById("level").value = "whole";
    addlist(data);
}

function addlist(data) {
    document.getElementById("my-list").innerHTML = "";
    AMapUI.loadUI(['misc/MarkerList', 'overlay/SimpleMarker', 'overlay/SimpleInfoWindow'],
        function (MarkerList, SimpleMarker, SimpleInfoWindow) {


            var markerList = new MarkerList({
                //关联的map对象
                map: map,
                //列表的dom容器的id
                listContainer: 'my-list',

                /*//需要监听的列表节点事件
                 listElementEvents: ['click', 'mouseenter', 'mouseleave'],

                 //需要监听的marker事件
                 markerEvents: ['click', 'mouseover', 'mouseout'],*/

                /*//需要监听的infoWindow事件
                 infoWindowEvents: ['click', 'mouseover', 'mouseout'],*/

                //返回数据项的Id
                getDataId: function (dataItem, index) {
                    //index表示该数据项在数组中的索引位置，从0开始，如果确实没有id，可以返回index代替
                    return dataItem.id;
                },
                //返回数据项的位置信息，需要是AMap.LngLat实例，或者是经纬度数组，比如[116.789806, 39.904989]
                getPosition: function (dataItem) {
                    return dataItem.position;
                },
                //返回数据项对应的Marker
                getMarker: function (dataItem, context, recycledMarker) {

                    var label = dataItem.id;
                    //存在可回收利用的marker
                    if (recycledMarker) {
                        //直接更新内容返回
                        recycledMarker.setIconLabel(label);
                        return recycledMarker;
                    }

                    //返回一个新的Marker
                    if (document.getElementById("level").value == "windc") {
                        if (dataItem.id == "A") {
                           return setSimpleMarker(SimpleMarker,context,'http://localhost/static/gaode/imgs/yx.png','my-marker','35px');
                        } else if (dataItem.id == "B") {
                           return setSimpleMarker(SimpleMarker,context,'http://localhost/static/gaode/imgs/tj.png','my-marker','35px');
                        }
                        else if (dataItem.id == "D") {
                            return setSimpleMarker(SimpleMarker,context,'http://localhost/static/gaode/imgs/dj.png','my-marker','35px');
                        }
                        else if (dataItem.id == "C") {
                            return setSimpleMarker(SimpleMarker,context,'http://localhost/static/gaode/imgs/wh.png','my-marker','35px');
                        } else {
                            return setSimpleMarker(SimpleMarker,context,'http://localhost/static/gaode/imgs/xd.png','my-marker','35px');
                        }
                    }
                    else if (document.getElementById("level").value == "region") {
                        return setSimpleMarker(SimpleMarker,context,'http://localhost/static/gaode/imgs/house.png','my-marker','30px');
                    }
                    else {
                       return setSimpleMarker(SimpleMarker,context,'http://localhost/static/gaode/imgs/zone.png','my-marker','25px');
                    }

                },
                //返回数据项对应的列表节点
                getListElement: function (dataItem, context, recycledListElement) {
                    var tpl = '<p><%-dataItem.id%>：<%-dataItem.title%>';
                    var content = MarkerList.utils.template(tpl, {
                        dataItem: dataItem,
                        dataIndex: context.index
                    });

                    if (recycledListElement) {
                        //存在可回收利用的listElement, 直接更新内容返回
                        recycledListElement.innerHTML = content;
                        return recycledListElement;
                    }

                    //返回一段html，MarkerList将利用此html构建一个新的dom节点
                    return '<li>' + content + '</li>';
                },
                getInfoWindow: function (dataItem, context, recycledInfoWindow) {
                    if (recycledInfoWindow) {
                        //存在可回收利用的infoWindow, 直接更新内容返回
                        recycledInfoWindow.setInfoTitle(dataItem.title);
                        recycledInfoWindow.setInfoBody(dataItem.desc);
                        return recycledInfoWindow;
                    }
                    //返回一个新的InfoWindow
                    return new SimpleInfoWindow({
                        offset: new AMap.Pixel(0, -37),
                        infoTitle: dataItem.id,
                        infoBody: dataItem.desc
                    });
                }
            });
            //监听选中改变
            markerList.on('selectedChanged', function (event, info) {
                //重复选中，取消当前选中
                if (info.selectAgain) {
                    this.clearSelected();
                    return;
                }

                var selectedRecord = info.selected,
                    unSelectedRecord = info.unSelected,
                    marker;
                if (selectedRecord) {
                    marker = selectedRecord.marker;
                    if (selectedRecord.data.desc.indexOf("风场") > -1) {
                        map.setZoomAndCenter(13, marker.getPosition());
                        if (map.getZoom() == 13) {
                            var data3 = [{
                                id: 'A',
                                position: [79.1728535742, 38.8910328265],
                                iconStyle: 'purple',
                                title: '风机1',
                                desc: '<ul id="myTab" class="nav nav-tabs"> <li class="active"> <a href="#home" data-toggle="tab">测试1 </a> </li> <li><a href="#ios" data-toggle="tab">iOS</a></li> <li class="dropdown"><a href="#" id="myTabDrop1" class="dropdown-toggle"data-toggle="dropdown">Java <b class="caret"></b> </a> <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1"> <li><a href="#jmeter" tabindex="-1" data-toggle="tab">jmeter</a></li> <li><a href="#ejb" tabindex="-1" data-toggle="tab">ejb</a></li> </ul> </li> </ul> <div id="myTabContent" class="tab-content"><div class="tab-pane fade in active" id="home"> <p>aaaaaaaaaaaaaaaaaaa</p> </div> <div class="tab-pane fade" id="ios"><p>bbbbbbbbbbbbbbbbbbbbb</p> </div> <div class="tab-pane fade" id="jmeter"> <p>ccccccccccccccccccccccccccc</p> </div> <div class="tab-pane fade" id="ejb"> <p>dddddddddddddddddddddddddddd</p> </div> </div>'
                            }, {
                                id: 'B',
                                position: [79.6728535742, 38.1910328265],
                                iconStyle: 'green',
                                title: '风机2',
                                desc: '风机2'
                            },
                                {
                                    id: 'C',
                                    position: [79.4728535742, 38.2910328265],
                                    iconStyle: 'red',
                                    title: '风机3',
                                    desc: '风机3'
                                }, {
                                    id: 'D',
                                    position: [79.5728535742, 38.3910328265],
                                    iconStyle: 'blue',
                                    title: '风机4',
                                    desc: '风机4'
                                }, {
                                    id: 'E',
                                    position: [79.7728535742, 38.4910328265],
                                    iconStyle: 'blue',
                                    title: '风机5',
                                    desc: '风机5'
                                }];
                            document.getElementById("my-list").innerHTML = "";
                            document.getElementById("level").value = "windc";
                            addlist(data3);
                            //自定义线路导航
                            // setPath(100,map,'路线0',[
                            //     [79.1728535742, 38.8910328265],
                            //     [79.6728535742, 38.1910328265],
                            //     [79.4728535742, 38.2910328265],
                            //     [79.5728535742, 38.3910328265],
                            //     [79.7728535742, 38.4910328265]
                            // ],true,100000);
                        }
                    }
                }
                if (unSelectedRecord) {
                    marker = unSelectedRecord.marker;
                    marker.show();
                }
            });
            //构建一个数据项数组，数据项本身没有格式要求，但需要支持下述的getDataId和getPosition
            //展示该数据
            markerList.render(data);
        });
}
function reback() {
    if (document.getElementById("level").value == "windc") {
        map.clearMap();
        map.setZoomAndCenter(5, [93.562649, 38.9124]);
        // var mouseTool = new AMap.MouseTool(map);

        var data2 = [{
            id: 'f1',
            position: [87.0117187500, 44.9025779963],
            iconStyle: 'blue',
            title: '风场1',
            desc: '风场1'
        }, {
            id: 'f2',
            position: [79.6728535742, 38.8910328265],
            iconStyle: 'blue',
            title: '风场2',
            desc: '风场2'
        }, {
            id: 'f3',
            position: [101.7993164063, 36.4919734706],
            iconStyle: 'blue',
            title: '风场3',
            desc: '风场3'
        }
        ];
        addlist(data2);
        document.getElementById("level").value = "region";
    } else if (document.getElementById("level").value == "region") {
        map.clearMap();
        initPage(DistrictCluster, $);
        map.setZoom(4);
        setpolygon();
        var data = [{
            id: 'A',
            position: [89.8681640625, 40.9798980696],
            iconStyle: 'blue',
            title: '西北',
            desc: '西北'
        }, {
            id: 'B',
            position: [116.0595714123, 42.3585433399],
            iconStyle: 'blue',
            title: '华北',
            desc: '华北'
        }, {
            id: 'C',
            position: [127.3535156250, 44.7779358963],
            iconStyle: 'blue',
            title: '东北',
            desc: '东北'
        }, {
            id: 'D',
            position: [118.1469739974, 31.2409856647],
            iconStyle: 'blue',
            title: '华中',
            desc: '华中'
        }, {
            id: 'E',
            position: [89.5166015625, 30.7512777763],
            iconStyle: 'blue',
            title: '西南',
            desc: '西南'
        }, {
            id: 'F',
            position: [111.4013671875, 23.0392977478],
            iconStyle: 'blue',
            title: '华南',
            desc: '华南'
        }
        ];
        document.getElementById("level").value = "whole";
        addlist(data);
    }
}


//设置西北区域的polygon
function setpolygon() {
    var path = [
        [87.448757, 49.025257],
        [86.987332, 49.068462],
        [85.822781, 48.328859],
        [85.537136, 46.967128],
        [85.383328, 46.997108],
        [83.144002, 47.165591],

        [82.438992, 45.47746],
        [82.570828, 45.152984],
        [80.043972, 44.857809],
        [80.329617, 42.149144],
        [77.868679, 41.031709],
        [75.759304, 40.19775],
        [75.649441, 40.582664],
        [74.924343, 40.482464],
        [74.111355, 39.996053],
        [74.001492, 39.472238],
        [74.06741, 39.556994],
        [73.957546, 38.567488],
        [74.924343, 38.447129],
        [75.012234, 37.598993],
        [75.188015, 37.319929],
        [74.616726, 37.109948],
        [76.066921, 36.564799],
        [76.22073, 35.730899],
        [78.198269, 35.373367],

        [79.846218, 34.400104],
        [81.647976, 35.176046],
        [82.50491, 35.713061],
        [83.098171, 35.588078],
        [83.713406, 35.337527],
        [85.339382, 35.730899],
        [86.042507, 35.713061],
        [89.711941, 36.086834],
        [89.558132, 35.944652],
        [89.909695, 35.766565],
        [89.680867, 34.032358],
        [91.504598, 32.937418],
        [95.402859, 32.289447],
        [95.842312, 31.730496],
        [96.084011, 31.749182],
        [96.281765, 31.562147],
        [96.809109, 31.674413],
        [97.402371, 32.844974],
        [97.73196, 33.323636],
        [97.512234, 33.799684],
        [97.402371, 33.799684],
        [97.863796, 34.145901],
        [98.501003, 34.018511],
        [99.79739, 32.69717],
        [100.061062, 32.992532],
        [101.64584, 33.093835],
        [101.659573, 33.31216],
        [101.777676, 33.442894],
        [101.85458, 33.600887],
        [101.95071, 33.436018],
        [101.887539, 33.309865],
        [101.835354, 33.270835],
        [101.785915, 33.254759],
        [101.849087, 33.199619],
        [101.85458, 33.178932],
        [101.843594, 33.121443],
        [101.843594, 33.121443],
        [101.843594, 33.121443],
        [101.97543, 33.185828],
        [102.115505, 33.2915],
        [102.222622, 33.2203],
        [102.203396, 33.328226],
        [102.277554, 33.415387],
        [102.472561, 33.447477],
        [102.458828, 33.561988],
        [102.351711, 33.600887],
        [102.305019, 33.706055],
        [102.291287, 33.728901],
        [102.250088, 33.790554],
        [102.244595, 33.790554],
        [102.277554, 33.822505],
        [102.156704, 33.961579],
        [102.398403, 33.975246],
        [102.445095, 34.07768],
        [102.670315, 34.070855],
        [103.123501, 34.166356],
        [103.175686, 34.075405],
        [103.172939, 34.004851],
        [103.161953, 33.995743],
        [103.142727, 33.950188],
        [103.186672, 33.902329],
        [103.192165, 33.900049],
        [103.172939, 33.808813],
        [103.304775, 33.795119],
        [103.442104, 33.749457],
        [103.543728, 33.671775],
        [105.471829, 32.736454],
        [105.460842, 32.874966],
        [107.075832, 32.680989],
        [107.273586, 32.440242],
        [108.053616, 32.152354],
        [109.668606, 31.695448],
        [109.712551, 32.366036],
        [110.28384, 32.58847],
        [111.162747, 39.27053],
        [110.789211, 39.21948],
        [110.349758, 39.253518],
        [109.11929, 38.121586],
        [108.943508, 37.496645],
        [108.899563, 37.531503],
        [106.350735, 38.397637],
        [107.05386, 38.9124],
        [106.658352, 39.389502],
        [105.911282, 37.601169],
        [104.24136, 37.252187],
        [103.439358, 38.043758],
        [99.517239, 39.880228],
        [100.176418, 40.809645],
        [98.40762, 40.58475],
        [98.396633, 40.909355],
        [97.770413, 41.644177],
        [97.188137, 42.742972],
        [95.393758, 44.263061],
        [91.087117, 45.137647],
        [91.10909, 46.576005],
        [90.318074, 47.674783]
    ];
    var polygon = new AMap.Polygon({
        map: map,
        path: path,
        strokeColor: null, //线颜色
        strokeOpacity: 0.2, //线透明度
        strokeWeight: 3,    //线宽
        fillColor: null, //填充色
        fillOpacity: 0.35//填充透明度
    });
    polygon.on('dblclick', function (e) {
        map.destroy();
        map = new AMap.Map('container', {
            zoom: 5
        });
        var mouseTool1 = new AMap.MouseTool(map);
        AMap.event.addDomListener(document.getElementById('line'), 'click', function () {
            mouseTool1.polyline();
        }, false);
        map.setZoomAndCenter(5, [93.562649, 38.9124]);

        var data2 = [{
            id: 'f1',
            position: [87.0117187500, 44.9025779963],
            iconStyle: 'blue',
            title: '风场1',
            desc: '风场1'
        }, {
            id: 'f2',
            position: [79.6728535742, 38.8910328265],
            iconStyle: 'blue',
            title: '风场2',
            desc: '风场2'
        }, {
            id: 'f3',
            position: [101.7993164063, 36.4919734706],
            iconStyle: 'blue',
            title: '风场3',
            desc: '风场3'
        }
        ];
        addlist(data2);
        document.getElementById("level").value = "region";
    });
}

/*设置SimpleMarker标点
* SimpleMarker：SimpleMarker地图的SimpleMarker对象
* context:
* src:图标路径
* calssName:containerClassNames的类名
* stylewidth:图标的大小  如15px
* */
function setSimpleMarker(SimpleMarker,context,src,className,stylewidth) {

    var simplemarker= new SimpleMarker({
        containerClassNames: className,
        iconStyle: {
            src: src,
            style: {
                width: stylewidth
            }
        },
        iconLabel: context.id
    });
    return simplemarker;
}
/*自定义线路导航
* zIndex
* map：地图
* name：线路标号
* loop:是否循环播放  是 true  否 false
* speed:巡航速度，单位千米/小时
* */
function setPath(zIndex,map,name,path,loop,speed){
    AMapUI.load(['ui/misc/PathSimplifier', 'lib/$'], function(PathSimplifier, $) {

        if (!PathSimplifier.supportCanvas) {
            alert('当前环境不支持 Canvas！');
            return;
        }
        var pathSimplifierIns = new PathSimplifier({
            zIndex: zIndex,
            //autoSetFitView:false,
            map: map, //所属的地图实例

            getPath: function(pathData, pathIndex) {

                return pathData.path;
            },
            getHoverTitle: function(pathData, pathIndex, pointIndex) {

                if (pointIndex >= 0) {
                    //point
                    return pathData.name + '，点：' + pointIndex + '/' + pathData.path.length;
                }

                return pathData.name + '，点数量' + pathData.path.length;
            },
            renderOptions: {

                renderAllPointsIfNumberBelow: 100 //绘制路线节点，如不需要可设置为-1
            }
        });

        window.pathSimplifierIns = pathSimplifierIns;

        //设置数据
        pathSimplifierIns.setData([{
            name: name,
            path: path
        }]);

        //对第一条线路（即索引 0）创建一个巡航器
        var navg1 = pathSimplifierIns.createPathNavigator(0, {
            loop: loop, //循环播放
            speed: speed //巡航速度，单位千米/小时
        });

        navg1.start();
    });
}