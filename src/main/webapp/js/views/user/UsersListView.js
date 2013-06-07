define([ 'backgrid', 'utilities/utils', 'underscore', 'backbone', 'views/user/UserView', 'text!templates/TemplatePageUsers.html', 'jquery', 'bootstrap' ],

function(Backgrid, utils, _, Backbone, UserView, templatePageUsers, $) {
	var UsersListView = Backbone.View.extend({
		tagName : 'ul',
		className : 'thumbnails',

		showThumbnails : function() {
			this.showingThumbnails = true;
			this.gridUser.remove();
			$(this).html(this.render().$el);
		},

		showTable : function() {
			this.showingTable = true;
			var that = this
			if (!this.gridUser) {
				this.gridUser = new Backgrid.Grid({
					className : "backgrid span9",
					columns : that.getColumns(),
					collection : that.collection
				});
			}
			this.$el.html(this.gridUser.render().$el);
		},

		initialize : function(options) {
			this.showingThumbnails = true;
			this.showingTable = false;
			// $('#showThumbnails').click(this.showThumbnails());
			// $('#showTable').click(this.showTable());
			this.collection.on('add', this.addOne, this);
		},

		render : function() {
			this.collection.each(this.addOne, this);
			return this;
		},

		addOne : function(userModel) {
			if (this.showingThumbnails) {
				var userView = new UserView({
					model : userModel,
				});
				this.$el.append(userView.render().el);
			}
		},
		getColumns : function() {
			var columns = [ {
				name : "id",
				label : "ID",
				editable : false,
				cell : Backgrid.IntegerCell.extend({
					orderSeparator : ''
				})
			}, {
				name : "name",

				label : "Name",
				cell : "string",
				editable : false,
			}, {
				name : "username",
				label : "Login",
				cell : "string",
				editable : false,
			} ];

			return columns;
		}

	});
	return UsersListView;
});
