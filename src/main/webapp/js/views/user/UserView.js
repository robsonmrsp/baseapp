define([ 'utilities/utils', 'underscore', 'backbone', 'views/user/UserFormView', 'text!templates/UserView.html', 'jquery', 'bootstrap' ], function(utils, _, Backbone, UserFormView, TemplateUserView, $) {

	var UserView = Backbone.View.extend({
		tagName : 'li',
		className : 'span3',

		template : _.template(TemplateUserView),
		events : {
			'change' : 'changeEvent',
		},

		initialize : function() {
			this.model.on('change', this.render, this);
			this.model.on('destroy', this.destroyUser, this);
		},
		events : {
			'click .edit' : 'editUser',
			'click .changePassword' : 'changePassword',
			'click .saveNewPassword' : 'saveNewPassword',
			'click .delete' : 'showConfirmMessage',
			'click .confirm-delete' : 'deleteUser'
		},

		changePassword : function(userModel) {
			var id = this.model.get('id');
			$('#error-messages' + id).hide();
			$('#changePassModal' + id).modal('show');
		},

		showConfirmMessage : function(userModel) {
			var id = this.model.get('id');

			$('#myModal' + id).modal('show');
		},

		_saveNewPassword : function(userModel) {

		},
		saveNewPassword : function(userModel) {
			// O clone foi necessário pois o objeto this.model está assinando o
			// evento on change, onde efetua a renderização da tela, fazendo com
			// que o modal suma
			var user = this.model.clone();
			var id = this.model.get('id');
			var oldPass = $('#inputOldPassword' + id).val();
			var confPass = $('#inputConfirmPassword' + id).val();
			var newPass = $('#inputNewPassword' + id).val();

			if (this.model.get('password') !== oldPass) {
				utils.showAlert('Erro!', [ 'Senha antiga incorreta' ], 'alert-error');
				return;
			}

			if ((newPass !== '' || confPass !== '') && (newPass !== confPass)) {
				utils.showAlert('Erro!', [ 'A confirmação não bate' ], 'alert-error');
				return;
			}
			user.set('password', newPass);
			user.save({}, {
				success : function(_collection, _resp, _options) {
					utils.showAlert('Info!', [ 'Senha alterada com sucesso' ], 'alert-info');
				},
				error : function(model, xhr, options) {
				},
			});
		},

		deleteUser : function(userModel) {
			var id = this.model.get('id');
			$('#myModal' + id).modal('hide');

			this.model.destroy();
		},

		destroyUser : function() {
			this.$el.remove();
		},
		editUser : function(userModel) {
			this.showEditForm();
		},
		render : function() {
			if (this.model && this.model.get('id')) {
				var template = this.template(this.model.toJSON());
				var und = _;
				this.$el.html(template);
			}
			return this;
		},
		showEditForm : function() {
			formUserView = new UserFormView({
				model : this.model,
				pageContainer : '#modal-body-user'
			});
			$('#form-user-modal').modal('show');
		},
		changeEvent : function(event) {
			utils.hideAlert();
			utils.changeEvent(event, this.model);
		}
	});

	return UserView;
});
