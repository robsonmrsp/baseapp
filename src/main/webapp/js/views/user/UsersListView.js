define([ 'utilities/utils',  'underscore', 'backbone', 'views/user/UserView', 'text!templates/TemplatePageUsers.html','jquery', 'bootstrap' ],
function(utils, _, Backbone, UserView, templatePageUsers,$) {
	var UsersListView = Backbone.View.extend({
		tagName : 'ul',
		className : 'thumbnails',

		initialize : function(options) {
			this.collection.on('add', this.addOne, this);
		},

		render : function() {
			this.collection.each(this.addOne, this);
			return this;
		},

		addOne : function(userModel) {
			var userView = new UserView({
				model : userModel,
			});
			this.$el.append(userView.render().el);
		}

	});
	return UsersListView;
});
