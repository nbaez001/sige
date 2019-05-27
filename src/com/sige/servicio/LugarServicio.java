package com.sige.servicio;

import java.util.List;

import com.sige.entidad.Lugar;
import com.sige.util.Busqueda;

public interface LugarServicio extends BaseServicio<Lugar, Long> {

	public Busqueda buscarPorLugar(Lugar lugar, Long paginaActual);

	public void eliminiarLugar(Lugar lugar);

	public List<Lugar> getAllProvincias(String idDepartamento);

	public List<Lugar> getAllDepartamentos();

	public List<Lugar> getAllDistritos(String idDepartamento, String idProvincia);

	public Boolean validarDuplicado(Lugar lugar);

	public Long getCorrelativoDepartamento();

	public Long getCorrelativoProvincia(String idDepartamento);

	public Long getCorrelativoDistrito(String idDepartamento, String idProvincia);

	public Long getCorrelativoLugar(String idDepartamento, String idProvincia,
			String idDistrito);

	public Long getUltimoCorrelativo(String idDepartamento, String idProvincia,
			String idDistrito);

	public String getDistrito();

	public String getDepartamento();

	public String getProvincia();

}