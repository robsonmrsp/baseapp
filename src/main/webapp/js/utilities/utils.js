define([ 'underscore', 'jquery', 'bootstrap' ], function(_, $) {
	utils = {
		displayValidationErrors : function(messages) {
			for ( var key in messages) {
				if (messages.hasOwnProperty(key)) {
					this.addValidationError(key, messages[key]);
				}
			}
			this.showAlert('Erro!', 'Fix validation errors and try again', 'alert-error');
		},

		escapeById : function(id) {
			return _.escape($('#' + id).val());
		},

		escapeByAttr : function(id, attr) {

			return _.escape($('#' + id).attr(attr));
		},

		addValidationError : function(field, message) {
			var controlGroup = $('#' + field).parent().parent();
			controlGroup.addClass('error');
			$('.help-inline', controlGroup).html(message);
		},

		removeValidationError : function(field) {
			var controlGroup = $('#' + field).parent().parent();
			controlGroup.removeClass('error');
			$('.help-inline', controlGroup).html('');
		},

		showAlert : function(title, messages, klass) {
			var text = '';
			$('.alert').removeClass("alert-error alert-warning alert-success alert-info");
			$('.alert').addClass(klass);
			text = '<ul>';
			for ( var key in messages) {
				text += '<li>' + messages[key] + '</li>';
			}
			text += '</ul>';
			$('.alert').html('<strong>' + title + '</strong> ' + text);
			$('.alert').show();
		},

		hideAlert : function() {
			// $('.alert').hide();
		},

		getTodayDate : function() {
			var today = new Date();
			return today.getDate() + '/' + (today.getMonth() + 1) + '/' + today.getFullYear();
		},

		getActualHour : function() {

		},
		changeEvent : function(event, model) {
			// Apply the change to the model
			var target = event.target;
			var change = {};
			if (target.name === '')
				return;
			change[target.name] = target.value;
			model.set(change);

			// Run validation rule (if any) on changed item
			var check = model.validateItem(target.id);
			if (check.isValid === false) {
				utils.showAlert('Erro!', check.messages, 'alert-error');
			} else {
				utils.removeValidationError(target.id);
				$('.alert').hide();
			}
		},

		getParameterByName : function(name) {
			name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
			var regexS = "[\\?&]" + name + "=([^&#]*)";
			var regex = new RegExp(regexS);
			var results = regex.exec(window.location.href);
			if (results == null)
				return "";
			else
				return decodeURIComponent(results[1].replace(/\+/g, " "));
		},

		backListEvents : function() {
			var that = this;
			console.log("voltando para a lista de midias");
			window.setTimeout(function() {
				that.navigate('sintsview/showMedias');
			}, 1500);
		},

		showErrorResponsePage : function(xhr) {
			$('body').html(xhr.responseText);
		},

		markActiveItem : function(itemId) {
			var itens = $('.nav.nav-list li');
			itens.removeClass('active');
			$('#' + itemId).addClass('active');
		},
	};
	return utils;
});
