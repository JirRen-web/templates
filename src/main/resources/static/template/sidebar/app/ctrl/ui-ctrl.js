/**
 * ui-router test
 * 
 */

var jq = $.noConflict();

var ctrl = angular.module('app.ctrl',[]);

ctrl.controller('rootController', ['$scope', function($scope) {
		$scope.test = function($event) {
			$event.preventDefault();
			jq("#wrapper").toggleClass("toggled");
		}
		
		$scope.showNotify = function($event) {
			$event.preventDefault();
			jq(function(){
			    new PNotify({
			        title: 'Regular Notice',
			        text: 'Check me out! I\'m a notice.',
			        desktop: {
			        	desktop: true
			        }
			    });
			});
		}
		$scope.showDeskTopNotify = function($event) {
			PNotify.desktop.permission();
			(new PNotify({
			    title: 'Desktop Notice',
			    text: 'If you\'ve given me permission, I\'ll appear as a desktop notification. If you haven\'t, I\'ll still appear as a regular PNotify notice.',
			    desktop: {
			        desktop: true
			    }
			})).get().click(function(e) {
			    if ($('.ui-pnotify-closer, .ui-pnotify-sticker, .ui-pnotify-closer *, .ui-pnotify-sticker *').is(e.target)) return;
			    alert('Hey! You clicked the desktop notification!');
			});
		}
		
		$scope.removeAllNotify = function($event) {
			PNotify.removeAll();
		}
		
		$scope.showDynamicNotify = function($event) {
			dyn_notice();
		}
}]);

function dyn_notice() {
    var percent = 0;
    var notice = new PNotify({
        text: "Please Wait",
        type: 'info',
        icon: 'fa fa-spinner fa-spin',
        hide: false,
        buttons: {
            closer: false,
            sticker: false
        },
        opacity: .75,
        shadow: false,
        width: "170px"
    });
    notice.update({
        title: false
    });
    var interval = setInterval(function() {
        percent += 2;
        var options = {
            text: percent + "% complete."
        };
        if (percent == 80) options.title = "Almost There";
        if (percent >= 100) {
            window.clearInterval(interval);
            options.title = "Done!";
            options.type = "success";
            options.hide = true;
            options.buttons = {
                closer: true,
                sticker: true
            };
            options.icon = 'fa fa-check';
            options.opacity = 1;
            options.shadow = true;
            options.width = PNotify.prototype.options.width;
        }
        notice.update(options);
    }, 120);
   /* setTimeout(function() {
     
    }, 2000);*/
}