var registr = angular.module('registr', []);

var host = "http://" + window.location.host;

registr.controller('registrierungCtrl', function($scope,$http) {
	
	var email;
	var name;
	var passwort;
	var passwortw;
	
	var myRegExp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,10}/;
	
	$scope.ngWeiter = function() {
		
		email = $scope.email;
		name = $scope.name;
		
	};
	
	$scope.sicherheitTesten = function() {
		
		var ergebnis = $scope.passwort.search(myRegExp);
		
		if(ergebnis !== -1) {
			
			console.log("aaaa");
		}
	}

	$scope.ngWeiter2 = function() {
		
		email = $scope.email;
		name = $scope.name;
		passwort = $scope.passwort;
		passwortw = $scope.passwortw;
		
		if(passwort === passwortw) {
		
		var daten = {};
		daten["email"] = email;
		daten["name"] = name;
		daten["passwort"] = passwort;
		daten["passwortw"] = passwortw;
		
		$scope.meldung = "Fehler";
		
		$http.post(host + "/registrierung", daten).then(function(data) {
			
				console.log(data);
				var erfolg = data.data.message;
				$scope.meldung = "Hallo " + erfolg;
						
		});
				
		}
		
	}
	
	$scope.ngRedirect = function() {
		
		location.reload();
		
	}
	
});

$(document).ready(function() {

	angular.bootstrap(document.getElementById("modal_registrierung"), ['registr']);
	
	if(document.cookie) {
		
		$("#praktAnbieten").hide();
				
	} else {
		
		$("#praktAnbieten").show();
		
	}
});
