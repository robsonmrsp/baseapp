package br.com.mr.app.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.mr.app.json.JsonRole;
import br.com.mr.app.json.JsonUser;
import br.com.mr.app.model.AppUser;
import br.com.mr.app.model.Role;

public class Parser {

	static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	static SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");

	public static String getHourAsString(Date date) {
		String format = "";
		try {
			format = hourFormat.format(date);
		} catch (Exception e) {
			format = "00:00";
		}
		return format;
	}

	public static String getDateAsString(Date date) {
		String format = "";
		try {
			format = dateFormat.format(date);
		} catch (Exception e) {
			format = dateFormat.format(new Date());
		}
		return format;
	}

	private static Timestamp getHour(String date) {
		if (!date.isEmpty()) {
			try {
				return new Timestamp(hourFormat.parse(date).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private static Timestamp getDate(String date) {
		if (!date.isEmpty()) {
			try {
				return new Timestamp(dateFormat.parse(date).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}



	public static JsonUser toJson(AppUser user) {

		JsonUser jsonUser = new JsonUser(user.getId(), user.getUsername(), user.getName(), user.getPassword(), user.getPhoto(), toJson(user.getRole()));
		Role role = user.getRole();
		if (role != null) {
			jsonUser.setRoleId(role.getId());
		}
		return jsonUser;
	}

	public static ArrayList<JsonRole> toListJsonRole(List<Role> roles) {
		ArrayList<JsonRole> list = new ArrayList<JsonRole>();
		for (Role role : roles) {
			JsonRole jsonRole = new JsonRole(role.getId(), role.getAuthority(), role.getDescription());
			list.add(jsonRole);
		}
		return list;
	}

	public static AppUser toEntity(JsonUser user) {
		AppUser appUser = new AppUser();
		appUser.setId(user.getId());
		appUser.setUsername(user.getUsername());
		appUser.setName(user.getName());
		appUser.setPhoto(user.getPhoto());
		appUser.setRole(new Role(user.getRoleId()));
		appUser.setPassword(user.getPassword());
		return appUser;
	}

	public static JsonRole toJson(Role role) {
		JsonRole jsonRole = new JsonRole(role.getId(), role.getAuthority(), role.getDescription());
		return jsonRole;
	}

	public static Role toEntity(JsonRole jsonRole) {
		Role role = new Role();
		role.setId(jsonRole.getId());
		role.setDescription(jsonRole.getDescription());
		role.setAuthority(jsonRole.getAuthority());
		return role;
	}
}
