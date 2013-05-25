define([ 'utilities/utils', 'underscore', 'backbone', 'models/UserModel', 'views/user/UsersListView', 'collections/UsersCollection', 'views/user/UserFormView', 'text!templates/TemplatePageUsers.html', 'jquery', 'bootstrap' ],

function(utils, _, Backbone, UserModel, UsersListView, UserCollection, UserFormView, templatePageUsers, $) {

	var PageUsers = Backbone.View.extend({
		template : _.template(templatePageUsers),

		events : {
			'click #btn-add' : 'showAddFormUser',
			'click #btn-save' : 'saveUser',
			'click #showThumbnails' : 'showThumbnails',
			'click #showTable' : 'showTable',

		},

		initialize : function() {
			var that = this;
			this.AppUsers = new UserCollection();
			this.AppUsers.fetch({
				success : function(_usersCollection, _resp, _options) {
					$("#page").html(that.render().el);
					// $('.tips').tooltip();
					that.setUsers(_usersCollection);
				},
				error : function(_usersCollection, _resp, _options) {
					console.log("Erro ao obter Usuários!");
					$('body').html(_resp.responseText);
				}
			})
		},

		render : function(model) {
			var blankUser = new UserModel({});
			this.form = new UserFormView({
				model : blankUser,
				pageContainer : '#modal-body-user'
			});

			$(this.el).html(this.template(blankUser.toJSON()));
			return this;
		},

		setUsers : function(_usersCollection) {
			this.collection = _usersCollection;
			this.allUsersView = new UsersListView({
				collection : _usersCollection,
			});
			$("#users").html(this.allUsersView.render().el);
		},
		showTable : function() {
			this.allUsersView.showTable();
		},
		showThumbnails : function() {
			this.allUsersView.showThumbnails();
		},

		showAddFormUser : function() {
			var blankUser = new UserModel({});
			this.form = new UserFormView({
				model : blankUser,
				pageContainer : '#modal-body-user'
			});

			$('#form-user-modal').modal('show');
		},

		saveUser : function() {
			var model = this.form.getFormModel();
			var that = this;
			if (model.isValid()) {
				model.save({}, {
					success : function(_collection, _resp, _options) {
						$('#save-button').button('complete');
						utils.showAlert('Info', [ 'Usero salvo com sucesso!' ], 'alert-info');
						that.form.clearForm();

						that.collection.add(model, {
							merge : true
						});
					},

					error : function(model, xhr, options) {
						utils.showErrorResponsePage(xhr);
					},
				});
			} else {
				utils.showAlert('Erro!', model.getMessages(), 'alert-error');
			}
		}
	});

	return PageUsers;
});
