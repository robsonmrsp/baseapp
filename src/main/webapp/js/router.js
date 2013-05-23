define([ 'jquery', 'underscore', 'backbone', 'views/user/UsersPage', ], function($, _, Backbone, PageUsers) {
	var AppRouter = Backbone.Router.extend({
		routes : {// js/collections/UserCollection.js
			'' : 'index',
			'sintsview/users' : 'users',
			'sintsview/someEntity' : 'someEntity',
		},

		initialize : function() {
			this.on('route', this.changeHash, this);
		},
	});

	var initialize = function() {
		
		var appRouter = new AppRouter;

		appRouter.on('route:index', function() {

		});

		appRouter.on('route:users', function() {
			var pageUsers = new PageUsers();
			pageUsers.render();
		});

		Backbone.history.start();
	};

	return {
		initialize : initialize
	};
});
