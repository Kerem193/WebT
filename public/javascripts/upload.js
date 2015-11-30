var upload = angular.module('upload', []);

var host = "http://" + window.location.host;

upload.controller('uploadCtrl', function($scope,$http) {
		
	var titel;
	var link;
	var studiengang;
	var beschreibung;
	var stadt;
	var bundesland;
	var datum;
	var dauer;
	
	$(document).ready(function(){
		
		if(location.href === host+"/upload?erfolg") {
			
			$scope.uploadErfolg = "Die Stelle wurde erfolgreich hochgeladen.";
			
		}
		
	});

	
	$scope.ngWeiter1 = function() {
		
		titel = $scope.titel;
		link = $scope.link;
		beschreibung = $scope.beschreibung;	
		
		studiengang = $("#selectstudi2").val();
				
	}
	
	$scope.ngWeiter2 = function() {
		
		stadt = $scope.stadt;
		bundesland = $("#bundesland").val();
		
	}
	
	$scope.ngWeiter3 = function() {
		
		datum = $("#datum").val();
		dauer = $("#selectdauer2").val();
		
		console.log(datum);
		console.log(dauer);
		
	}
	
	$scope.ngFertig = function() {

		var daten = {};
			
		daten["titel"] = titel;
		daten["link"] = link;
		daten["studiengang"] = studiengang;
		daten["beschreibung"] = beschreibung;
		daten["stadt"] = stadt;
		daten["bundesland"] = bundesland;
		daten["datum"] = datum;
		daten["dauer"] = parseInt(dauer);
		
		console.log(daten);
		
		$http.post(host + "/upload", daten).then(function(data) {
			
			if(data.data.status === "ok") {
				
				location.href = location.href + "?erfolg";

				console.log(data);
			}
					
		});
	}
		
});

$(document).ready(function() {

	angular.bootstrap(document.getElementById("uploadBody"), ['upload']);

});