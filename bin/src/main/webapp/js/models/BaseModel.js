define([ 'jquery', 'underscore', 'backbone' ], function($, _, BB) {

	var BaseModel = BB.Model.extend({
		validateItem : function(key) {
			return (this.validators[key]) ? this.validators[key](this.get(key)) : {
				isValid : true
			};
		},

		initialize : function() {

		},

		validateAll : function() {
			var messages = {};
			for ( var key in this.validators) {
				if (this.validators.hasOwnProperty(key)) {
					var check = this.validators[key](this.get(key));
					if (check.isValid === false) {
						messages[key] = check.message;
					}
				}
			}

			return _.size(messages) > 0 ? {
				isValid : false,
				messages : messages
			} : {
				isValid : true
			};
		},

		isValid : function() {
			var isValid = this.validateAll().isValid;
			return isValid;
		},

		getMessages : function() {
			var messages = this.validateAll().messages;
			return messages;
		}

	});

	return BaseModel;
});
