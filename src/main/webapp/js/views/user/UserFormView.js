define([ 'utilities/utils', 'underscore', 'backbone', 'collections/RolesCollection', 'text!templates/TemplateUserFormView.html', 'models/UserModel', 'views/BaseFormView', 'jquery', 'bootstrap', 'jqueryform' ], function(utils, _, Backbone, RolesCollection, TemplateUserFormView,
		UserModel, BaseFormView, $) {
	var UserFormView = BaseFormView.extend({
		template : _.template(TemplateUserFormView),

		events : {
			'change #inputUploadImage' : 'startUpload',
			'change' : 'changeUser',
			'click #save-user-button' : 'saveUser'
		},

		postRender : function() {
			this.populateCombos('inputRole', RolesCollection, 'id', 'authority', 'roleId');
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
	});

	return UserFormView;
});
