'use strict';
// handles / catches browser back events and call js function actionBack() which redirects to last page 
$(function() {
	var appId = '07cd5140-5fe2-11e2-bcfd-0800200c9a66';
	if (typeof history.pushState === 'function') {
		// firefox doesn't fire popstate-event after page loaded -> set false, chrome does -> set true!
		var ignorePushState = $.browser.mozilla ? false : true;
		history.pushState(appId, null, null);
        $(window).bind('popstate', function () {
        	if (!ignorePushState) {
        		actionBack();
        	} else {
        		ignorePushState = false;
        	}
        });
	} else {
		var ignoreHashChange = true;
		$(window).bind('hashchange', function(e) {
			if (!ignoreHashChange) {
				actionBack();
			} else {
				ignoreHashChange = false;
			} 
		}); 
		window.location.hash = appId; 
	}
});