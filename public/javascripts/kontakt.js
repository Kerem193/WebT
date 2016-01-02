var kontakt = angular.module('kontakt', []);

var host = "http://" + window.location.host;

kontakt.controller('kontaktCtrl', function($scope,$http) {
		
	var email;
	var betreff;
	var nachricht;
	
	$scope.ngAbschicken = function() {
		
		var daten = {};
		
		email = $scope.kontaktEmail;
		betreff = $scope.kontaktBetreff;
		nachricht = $scope.kontaktNachricht;	
				
		daten["email"] = email;
		daten["betreff"] = betreff;
		daten["nachricht"] = nachricht;
		
		$http.post(host + "/kontaktNotLogged", daten).then(function(data) {
			
			if(data.data.status === "ok") {

				location.reload();
				
			} else {
				
				$scope.nachrichtFehler = data.data.message;
				
			}
					
		});
	}
		
});

$(document).ready(function() {

	angular.bootstrap(document.getElementById("kontaktDiv"), ['kontakt']);

});