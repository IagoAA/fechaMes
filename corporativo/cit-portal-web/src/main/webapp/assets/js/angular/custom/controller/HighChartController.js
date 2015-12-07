'use strict';
citApp.service('highChartService', ['$q', '$http', 'highchartApiUrl',
		function($q, $http, highchartApiUrl){
    return {
      get: function(path){
        var deferred = $q.defer();
        var url = highchartApiUrl + path + '/commits?callback=JSON_CALLBACK';
        $http.jsonp(url)
          .success(function(data){
            if (data && data.meta){
              var status = data.meta.status;
              if ( status < 300 ){
                deferred.resolve(data.data);
              } else {
                deferred.reject(data.data.message);
              }
            }
          })
          .error(function(){
            deferred.reject();
          });
        return deferred.promise;
      }
    };
  }]);

citApp.controller('HighChartController', ['$scope', 'config', 'commits',
		function($scope, config, commits){

    var data = {};
    angular.forEach(commits, function(commit){
      var author = commit.commit.author.name;
      if (data[author]){
        data[author]++;
      } else {
        data[author] = 1;
      }
    });

    var seriesData = [];
    angular.forEach(data, function(count, author){
      seriesData.push([author, count]);
    });
    if (seriesData.length > 0){
      seriesData.sort(function(a, b){
        return b[1] - a[1];
      });
      var s = seriesData[0];
      seriesData[0] = {
        name: s[0],
        y: s[1],
        sliced: true,
        selected: true
      };
    }

    if ( commits ){
      $scope.chartConfig = {
        chart: {
          plotBackgroundColor: null,
          plotBorderWidth: null,
          plotShadow: false
        },
        title: {
          text: config.path
        },
        plotOptions: {
          pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
              enabled: true,
              color: '#000000',
              connectorColor: '#000000',
              format: '<b>{point.name}</b>: {point.percentage:.1f} %'
            }
          }
        },
        series: [{
          type: 'pie',
          name: config.path,
          data: seriesData
        }]
      };
    }

  }]);