define([ 'utilities/utils',
         'underscore', 
         'backbone',
         'collections/RolesCollection',
         'text!templates/TemplateUserFormView.html', 
         'models/UserModel', 
         'jquery','bootstrap', 
         'jqueryform' ],
		function( utils, _, Backbone, RolesCollection, TemplateUserFormView,UserModel, $) {

	var UserFormView = Backbone.View.extend({
		template : _.template(TemplateUserFormView),

		initialize : function(options) {
			this.pageContainer = options.pageContainer;
			$(this.pageContainer).html(this.render().el);
			this.postRender();
			_.templateSettings = {
				interpolate : /\{\{(.+?)\}\}/g,
			};
		},

		events : {
			'change #inputUploadImage' : 'startUpload',
			'change' : 'changeUser',
			'click #save-user-button' : 'saveUser'
		},

		render : function() {
			$(this.el).html(this.template(this.model.toJSON()));
			return this;
		},

		postRender : function() {
			this.populateCombos();
		},

		populateCombos : function() {
			var comboRole = $('#inputRole');
			var self = this;
			var roles = new RolesCollection();

			roles.fetch({
				success : function(_collection, _resp, _options) {
					roles.each(function(role) {
						comboRole.append('<option value=' + role.get('id') + '>' + role.get('authority') + '</option>');
					});
					comboRole.val(self.model.get('roleId'));
				},

				error : function(model, xhr, options) {
					$("#contents").html(xhr.responseText);
				},
			});
		},

		startUpload : function(e) {
			$('form').ajaxSubmit({
				success : function(responseText) {
					var picture = $('#inputPhoto').get(0);
					if (responseText.dataType === 'IMG') {
						picture.setAttribute('src', responseText.dataUrl);
					}
				}
			});
		},

		getFormModel : function() {
			var localUser = new UserModel({
				id : $('#inputId').val(),
				name : $('#inputName').val(),
				username : $('#inputUsername').val(),
				password : $('#inputPassword').val(),
				roleId : $('#inputRole').val(),
				photo : $('#inputPhoto').attr('src'),
			});

			if (localUser.get('id') === "")
				localUser.set('id', null);
			return localUser;
		},

		saveUser : function() {
			var self = this;
			$('#save-button').button('loading');
			var localUser = this.getFormModel();

			var valitation = localUser.validateAll();
			if (valitation.isValid === false) {
				utils.showAlert('Erro!', valitation.messages, 'alert-error');
				$('#save-button').button('complete');
			} else {
				localUser.save({}, {
					success : function(_collection, _resp, _options) {
						$('#save-button').button('complete');
						utils.showAlert('Info', [ 'Usuario salvo com sucesso!' ], 'alert-info');
						self.clearForm();
					},

					error : function(model, xhr, options) {
						$("#contents").html(xhr.responseText);
					},
				});
			}
		},

		clearForm : function(user) {
			$('#inputId').val(null);
			$('#inputName').val('');
			$('#inputDescription').val('');
			$('#inputInitialDate').val(utils.getTodayDate());
			$('#inputFinalDate').val(utils.getTodayDate());
			$('#inputInitialHour').val('00:00');
			$('#inputFinalHour').val('00:00');
			$('#inputType').val('');
			$('#inputLocal').val('');
			$('#inputPicture').attr('src', 'pics/no-image.png');
		},

		changeUser : function(user) {
			utils.hideAlert();
			utils.changeEvent(user, this.model);
		}
	});

	return UserFormView;
});
