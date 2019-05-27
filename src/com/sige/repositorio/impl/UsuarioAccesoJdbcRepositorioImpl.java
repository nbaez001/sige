package com.sige.repositorio.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sige.repositorio.UsuarioAccesoJdbcRepositorio;
import com.sige.util.ConnectionUtil;

@Repository
public class UsuarioAccesoJdbcRepositorioImpl implements
		UsuarioAccesoJdbcRepositorio {

	//@Autowired
	//private DataSource dataSource;

	@Override
	public Long getCodigoGrupo(Long codigoUsuarioAcceso) {
		Long idUsuarioAcceso = null;
		try {
			Connection connection = new ConnectionUtil().getConexion();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("select codgrupo from usuarioacceso where id="
							+ codigoUsuarioAcceso);
			while (resultSet.next()) {
				idUsuarioAcceso = Long.parseLong(resultSet
						.getString("codgrupo"));
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return idUsuarioAcceso;
	}

}
