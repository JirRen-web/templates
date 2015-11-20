/**
 * 
 */

angular
    .module('myApp', ['base64','angular-md5'])
    .controller('myController', ['$base64', '$scope', 'md5', function($base64, $scope, md5) {
    	$scope.name = "test";
            $scope.base64Test = function() {
            	$scope.encoded = $base64.encode('a string');
                $scope.decoded = $base64.decode('VGVzdA==');
                alert($scope.encoded);
                alert($scope.decoded);
            }
            
            $scope.md5Test = function() {
            	$scope.md5Str = md5.createHash("123456");
            	alert($scope.md5Str);
            }
            
    }]);