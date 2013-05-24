define([ 'utilities/utils', 'underscore', 'backbone', 'jquery', 'bootstrap' ], function(utils, _, Backbone, $) {
	var BaseFormView = Backbone.View.extend({
		
		initialize : function(options) {
			this.pageContainer = options.pageContainer;
			$(this.pageContainer).html(this.render().el);
			this.postRender();
		},

		render : function() {
			$(this.el).html(this.template(this.model.toJSON()));
			return this;
		},
		
		/**
		 * @param (string)_fieldName : id do combobox que renderizará os objetos. 
		 * @param  _Collection: coleção dos Models que alimentarão o combo. 
		 * @param _id: atributo do Models que será usado como id da tag option. 
		 * @param  _name: Atributo do model que irá aparecer no combo. 
		 * @param _idName: nome do atributo de this.model que será usado para setar valor no combo.
		 */
		populateCombos : function(_fieldName, _Collection, _id, _name, _idName) {
			var combo = $('#' + _fieldName);
			var self = this;
			var collection = new _Collection();

			collection.fetch({
				success : function(_collection, _resp, _options) {
					collection.each(function(_model) {
						combo.append('<option value=' + _model.get(_id) + '>' + _model.get(_name) + '</option>');
					});
					combo.val(self.model.get(_idName));
				},

				error : function(_collection, _resp, _options) {
					$("#contents").html(_resp.responseText);
				},
			});
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
						utils.showAlert('Info', [ 'Salvo com sucesso!' ], 'alert-info');
						self.clearForm();
					},

					error : function(model, xhr, options) {
						$("#contents").html(xhr.responseText);
					},
				});
			}
		},
	
		changeUser : function(user) {
			utils.hideAlert();
			utils.changeEvent(user, this.model);
		}
	});

	return BaseFormView;
});
