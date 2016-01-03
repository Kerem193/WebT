/**
 * 
 */
var suche = angular.module('suche', []);
var host = "http://" + window.location.host;
suche.controller('suchenCtrl', function($scope,$http,$filter) {
	
	
	$scope.ngSuchen = function(){
				
		var bundesland = $scope.bundesland;
		var studiengang = $scope.studiengang;
		var dauer = $scope.dauer;
		var wasInput = $scope.wasinput;
		var datumInput = $filter('date')($scope.datuminput, "yyyy-MM-dd");
		var plzInput = $scope.plzinput;
		$http({
			  method: 'GET',
			  url: host + "/sucheStellen?bundesland=" + bundesland + 
				"&studiengang=" + studiengang + 
				"&dauer="+ dauer + 
				"&was=" + wasInput + 
				"&wo=" + plzInput + 
				"&wann=" + datumInput
			  
			}).then(function(data) {
				localStorage.setItem("SearchResult", JSON.stringify(data.data));
			
				window.location = "/stellenangebot";
		});
	}
	
});
$(document).ready(function() {

	angular.bootstrap(document.getElementById("sucheDiv"), ['suche']);

});