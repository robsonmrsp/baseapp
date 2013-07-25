define([ 'jquery', 'underscore', 'backbone', 'views/user/UsersPage', 'models/UserModel' ], function($, _, Backbone, PageUsers, UserModel) {
	var AppRouter = Backbone.Router.extend({
		routes : {// js/collections/UserCollection.js
			'' : 'index',
			'app/users' : 'users',
			'app/someEntity' : 'someEntity',
		},
	});

	var loadUser = function() {
		var loggedUser = new UserModel();
		loggedUser.urlRoot = 'rs/crud/users/loggedUser';
		loggedUser.fetch({
			success : function(_user, _resp, _options) {
				if (_user) {
					$('#menuUserPhoto').attr('src', _.unescape(_user.get('photo')));
					$('#menuUserName').html(_user.get('username'));
					$('#menuUserRole').html(_user.get('role').description);
				}
			},
			error : function(_model, _xhr, _options) {
				console.log('Erro');
			}
		});
	}
	
	var initialize = function() {

		var appRouter = new AppRouter;
		appRouter.on('route:index', function() {
			loadUser();
		});

		appRouter.on('route:users', function() {
			loadUser()
			var pageUsers = new PageUsers();
			pageUsers.render();
		});

		Backbone.history.start();
	};

	return {
		initialize : initialize
	};
});
