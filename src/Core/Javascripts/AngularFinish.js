try
{
	if (document.readyState !== "complete")
	{ return false; }
	if (window.jQuery)
	{
		if (window.jQuery.active)
		{ return false; }
		else if (window.jQuery.ajax && window.jQuery.ajax.active)
		{ return false; }
	}
	if (window.angular)
	{
		if (!window.qa)
		{ window.qa = { doneRendering: false }; }
		var injector = window.angular.element("body").injector();
		var $rootScope = injector.get("$rootScope");
		var $http = injector.get("$http");
		var $timeout = injector.get("$timeout");
		if ($rootScope.$$phase === "$apply" || $rootScope.$$phase === "$digest" || $http.pendingRequests.length !== 0 || $rootScope.$$applyAsyncQueue.length > 0)
		{
			window.qa.doneRendering = false;
			return false;
		}
		if (!window.qa.doneRendering)
		{
			$timeout(function() { window.qa.doneRendering = true; }, 0);
			return false;
		}
	}
	return true;
}
catch (ex)
{ return false; }
