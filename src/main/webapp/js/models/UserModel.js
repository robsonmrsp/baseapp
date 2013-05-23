define(['jquery', 'underscore', 'backbone', 'models/BaseModel' ], function($, _, Backbone, BaseModel) {
	
	var UserModel = BaseModel.extend({
		urlRoot : "rs/crud/users",

		defaults : {
			id : null,
			username : '',
			roleId : '',
			name : '',
			password : '',
			photo : 'img/no-image-user-128.png',
			role : null,
		},

		initialize : function() {
			this.validators = {};
			this.validators.name = function(value) {
				return value.length > 0 ? {
					isValid : true
				} : {
					isValid : false,
					message : "Deve ser informado um nome!"
				};
			};

			this.validators.username = function(value) {
				return value.length > 0 ? {
					isValid : true
				} : {
					isValid : false,
					message : "Deve ser informado um nome de usuário!"
				};
			};
		},
	});
	return UserModel;
});
