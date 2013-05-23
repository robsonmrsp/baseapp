define([ 'jquery', 'underscore', 'backbone','models/RoleModel'], function($, _, Backbone, RoleModel) {
	
	var RolesCollection = Backbone.Collection.extend({
		model : RoleModel,
		url : 'rs/crud/roles'
	});
	return RolesCollection;
});