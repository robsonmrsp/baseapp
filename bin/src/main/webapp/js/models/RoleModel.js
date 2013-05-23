define([ 'jquery', 'underscore', 'backbone', 'models/BaseModel' ], function($, _, Backbone, BaseModel) {

	var RoleModel = BaseModel.extend({
		urlRoot : "rs/crud/roles",
		defaults : {
			id : null,
			authority : '',
			description : '',
		},
	});

	return RoleModel;
});
