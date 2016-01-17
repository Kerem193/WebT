var upload = angular.module('upload', []);

var host = "http://" + window.location.host;

var stadtGlobal;

console.log("BLAAAAA");

upload.controller('uploadCtrl', function($scope,$http) {
		
	var titel;
	var link;
	var studiengang;
	var beschreibung;
	var stadt;
	var bundesland;
	var datum;
	var dauer;
	
	//$(document).ready(function(){
		
		if(localStorage.getItem("uploadErfolg") === "erfolgreich") {
			
			$scope.uploadErfolg = "Die Stelle wurde erfolgreich hochgeladen.";
			
			localStorage.removeItem("uploadErfolg");
			
		} 
		
		if(localStorage.getItem("uploadErfolg") === "fehler") {
			
			$scope.uploadFehler = "Die Stelle konnte nicht hochgeladen werden.";
			
			localStorage.removeItem("uploadErfolg");
			
		} 
		
	//});

	
	$scope.ngWeiter1 = function() {
		
		titel = $scope.titel;
		link = $scope.link;
		beschreibung = $scope.beschreibung;	
		
		studiengang = $("#selectstudi2").val();
				
	}
	
	$scope.ngWeiter2 = function() {
		
		stadt = stadtGlobal;
		console.log("Stadt: " + stadt);
		bundesland = $("#bundesland").val();
		$("#stadtfertig").html(stadt);
		
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
				
				localStorage.setItem("uploadErfolg", "erfolgreich");
				
				location.reload();

			} 
					
		}).catch(function(e) {
			
			console.log("Fehler");
			
			localStorage.setItem("uploadErfolg", "fehler");
			
			location.reload();
			
		});
	}
		
});

$(document).ready(function() {

	angular.bootstrap(document.getElementById("uploadBody"), ['upload']);

});
