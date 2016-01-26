/**
 * ui-router test
 * 
 */

var jq = $.noConflict();

var ctrl = angular.module('app.ctrl',[]);

ctrl.controller('myController', ['$scope', function($scope) {
		$scope.test = function() {
//			angular.element("#wrapper").toggleClass("toggled");
			jq("#wrapper").toggleClass("toggled");
		}
}]);
