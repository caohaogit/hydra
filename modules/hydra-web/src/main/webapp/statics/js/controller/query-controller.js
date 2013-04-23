/*
 * Copyright jd
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
'use strict';
function QueryCtrl($scope, queryService, TraceList, AppList, ServiceList) {
    $('#startTime').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        pickerReferer:'input'
    });

    $scope.appList = AppList.getAll();

    var service = {change:function(appId){
        $scope.service.list = ServiceList.getAll({appId:appId},function(serviceList){
            var serviceArray = [];
            for(var i in serviceList){
                serviceArray.push(serviceList[i].name);
            }
            $('#serviceName').typeahead({source:serviceArray});
        });
    }}

    $scope.service = service;

    $scope.traceList =  TraceList.getTraceList({serviceId:22001, startTime:1366614281227, durationMin:20, durationMax:90, sum:500}, function(traceList){
        queryService.initTable(traceList);
    });
}