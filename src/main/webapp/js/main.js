require.config({
	paths : {
		jquery : 'lib/jquery/jquery',
		underscore : 'lib/underscore/underscore-amd-1.4.4',
		backbone : 'lib/backbone/backbone-amd-1.0.0',
		bootstrap : 'lib/bootstrap/bootstrap',
		jqueryform : 'lib/jquery/jquery.form',
		templates : '../tpl'
	},

	// Use shim for jquery-plugins that does not support AMD
	shim : {
		bootstrap : [ 'jquery' ],
		jqueryform : [ 'jquery' ],
	}

});

require([ 'app', ], function(App) {

	App.initialize();
});
