var registr = angular.module('registr', []);

var host = "http://" + window.location.host;

registr.controller('registrierungCtrl', function($scope,$http) {
	
	var email;
	var name;
	var passwort;
	var passwortw;
	
	var myRegExp = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/;
	
	$scope.ngWeiter = function() {
		
		email = $scope.email;
		name = $scope.name;
		
	};
	
	var sicherheit = false;
	
	$scope.sicherheitTesten = function() {
		
		var passw = $scope.passwort;
		
		var ergebnis = passw.search(myRegExp);
		
		if(ergebnis !== -1) {
			$scope.fehler = "";
			$scope.ok = "Passwort ist sicher";
			sicherheit = true;
			
		} else {
			$scope.fehler = "Passwort zu unsicher";
			$scope.ok = "";
			sicherheit = false;
		}
	}

	$scope.ngWeiter2 = function() {
		
		email = $scope.email;
		name = $scope.name;
		passwort = $scope.passwort;
		passwortw = $scope.passwortw;
		
		if(passwort === passwortw && sicherheit === true) {
		
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
				
		} else {
			
			weiter('step1');
			
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
