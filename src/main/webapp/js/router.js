define([ 'jquery', 'underscore', 'backbone', 'views/user/UsersPage', 'models/UserModel' ], function($, _, Backbone, PageUsers, UserModel) {
	var AppRouter = Backbone.Router.extend({
		routes : {// js/collections/UserCollection.js
			'' : 'index',
			'app/users' : 'users',
			'app/someEntity' : 'someEntity',
		},

		initialize : function() {
			this.on('route', this.changeHash, this);
		},
	});

	var initialize = function() {

		var appRouter = new AppRouter;
		var that = this;
		appRouter.on('route:index', function() {
			var loggedUser = new UserModel();
			loggedUser.urlRoot = 'rs/crud/users/loggedUser';
			loggedUser.fetch({
				success : function(_user, _resp, _options) {
					if (_user) {
						$('#menuUserPhoto').attr('src', _user.get('photo'));
						$('#menuUserName').html(_user.get('username'));
						$('#menuUserRole').html(_user.get('role').description);
					}
				},

				error : function(_model, _xhr, _options) {
					console.log('Erro');
				}
			});
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
