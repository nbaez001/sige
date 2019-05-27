package com.sige.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sige.entidad.Lugar;
import com.sige.repositorio.LugarRepositorio;
import com.sige.servicio.LugarServicio;
import com.sige.util.Busqueda;
import com.sige.util.Constantes;
import com.sige.util.Criterio;

@Service
public class LugarServicioImpl extends BaseServicioImpl<Lugar, Long> implements
		LugarServicio {

	@Autowired
	private LugarRepositorio lugarRepositorio;

	@Value("${distrito}")
	private String distrito;

	@Value("${departamento}")
	private String departamento;

	@Value("${provincia}")
	private String provincia;

	@Autowired
	protected LugarServicioImpl(LugarRepositorio lugarRepositorio) {
		super(lugarRepositorio);
	}

	@Override
	public Busqueda buscarPorLugar(Lugar lugar, Long paginaActual) {
		Busqueda busqueda = new Busqueda();
		// Preparamos para obtener la cantidad de paginas
		Criterio filtro = Criterio.forClass(Lugar.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.ilike("codigoLugar", lugar.getCodigoLugar(),
				MatchMode.START));
		filtro.add(Restrictions.ilike("nombre", lugar.getNombre(),
				MatchMode.ANYWHERE));
		filtro.add(Restrictions.not(Restrictions.ilike("codigoLugar", "000",
				MatchMode.END)));
		Long totalRegistros = lugarRepositorio.cantidadPorCriteria(filtro);
		if (totalRegistros % Constantes.PAGINACION.LUGAR == 0) {
			busqueda.setNumeroPaginas(totalRegistros / Constantes.PAGINACION.LUGAR);
		} else {
			busqueda.setNumeroPaginas(totalRegistros / Constantes.PAGINACION.LUGAR + 1);
		}
		// validaciones
		if (paginaActual > busqueda.getNumeroPaginas()) {
			busqueda.setPaginaActual(busqueda.getNumeroPaginas());
		} else {
			busqueda.setPaginaActual(paginaActual);
		}
		if (paginaActual <= 0) {
			busqueda.setPaginaActual(1L);
		}
		// /
		// obtenemos los registros de la pagina solicitada
		filtro.setMaxResults(Constantes.PAGINACION.LUGAR.intValue());
		filtro.setFirstResult((busqueda.getPaginaActual().intValue() - 1)
				* Constantes.PAGINACION.LUGAR.intValue());
		if(totalRegistros>0){
		busqueda.setRegistos(lugarRepositorio.buscarPorCriteria(filtro));
		}else{
			busqueda.setRegistos(new ArrayList<>());
		}

		return busqueda;
	}

	public void eliminiarLugar(Lugar lugar) {
		lugar.setEstado(Boolean.FALSE);
		lugarRepositorio.actualizar(lugar);
	}

	@Override
	public List<Lugar> getAllProvincias(String idDepartamento) {
		Criterio filtro = Criterio.forClass(Lugar.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.ilike("codigoLugar", idDepartamento,
				MatchMode.START));
		filtro.add(Restrictions.ilike("codigoLugar", "00000", MatchMode.END));

		List<Lugar> lugares = buscarPorCriteria(filtro);
		for (int i = 0; i < lugares.size(); i++) {
			if (lugares.get(i).getCodigoLugar().substring(2, 4).equals("00")) {
				lugares.remove(i);
			}
		}
		return lugares;
	}

	@Override
	public List<Lugar> getAllDepartamentos() {
		Criterio filtro = Criterio.forClass(Lugar.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.ilike("codigoLugar", "0000000", MatchMode.END));
		List<Lugar> lugares = buscarPorCriteria(filtro);
		return lugares;
	}

	@Override
	public List<Lugar> getAllDistritos(String idDepartamento, String idProvincia) {
		Criterio filtro = Criterio.forClass(Lugar.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.ilike("codigoLugar", idDepartamento
				+ idProvincia, MatchMode.START));
		filtro.add(Restrictions.ilike("codigoLugar", "000", MatchMode.END));

		List<Lugar> lugares = buscarPorCriteria(filtro);
		for (int i = 0; i < lugares.size(); i++) {
			if (lugares.get(i).getCodigoLugar().substring(2, 4).equals("00")
					|| lugares.get(i).getCodigoLugar().substring(4, 6)
							.equals("00")) {
				lugares.remove(i);
			}
		}
		return lugares;
	}

	@Override
	public Boolean validarDuplicado(Lugar lugar) {
		Criterio filtro = Criterio.forClass(Lugar.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.eq("nombre", lugar.getNombre()));
		filtro.createCriteria("tipoLugar").add(
				Restrictions.eq("nombre", lugar.getTipoLugar().getNombre()));
		if (lugar.getId() != null) {
			filtro.add(Restrictions.not(Restrictions.eq("id", lugar.getId())));
		}
		if (buscarPorCriteria(filtro).size() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public Long getCorrelativoDepartamento() {
		Criterio filtro = Criterio.forClass(Lugar.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.ilike("codigoLugar", "0000000", MatchMode.END));
		return new Long(buscarPorCriteria(filtro).size() + 1);
	}

	@Override
	public Long getCorrelativoProvincia(String idDepartamento) {
		Criterio filtro = Criterio.forClass(Lugar.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.sqlRestriction("codlugar like '"
				+ idDepartamento + "%'"));
		List<Lugar> lugares = buscarPorCriteria(filtro);
		Long respuesta = new Long(buscarPorCriteria(filtro).size() + 1);
		for (int i = 0; i < lugares.size(); i++) {
			if (lugares.get(i).getCodigoLugar().substring(2, 4).equals("00")) {
				respuesta--;
			}
		}
		return respuesta;

	}

	@Override
	public Long getCorrelativoDistrito(String idDepartamento, String idProvincia) {
		Criterio filtro = Criterio.forClass(Lugar.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.sqlRestriction("codlugar like '"
				+ idDepartamento + idProvincia + "%'"));
		List<Lugar> lugares = buscarPorCriteria(filtro);
		Long respuesta = new Long(buscarPorCriteria(filtro).size() + 1);
		for (int i = 0; i < lugares.size(); i++) {
			if (lugares.get(i).getCodigoLugar().substring(4, 6).equals("00")) {
				respuesta--;
			}
		}
		return respuesta;

	}

	@Override
	public Long getCorrelativoLugar(String idDepartamento, String idProvincia,
			String idDistrito) {
		Criterio filtro = Criterio.forClass(Lugar.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.sqlRestriction("codlugar like '"
				+ idDepartamento + idProvincia + idDistrito + "%'"));
		List<Lugar> lugares = buscarPorCriteria(filtro);
		Long respuesta = new Long(buscarPorCriteria(filtro).size() + 1);
		for (int i = 0; i < lugares.size(); i++) {
			if (lugares.get(i).getCodigoLugar().substring(6, 9).equals("000")) {
				respuesta--;
			}
		}
		return respuesta;
	}

	public String getDistrito() {
		return distrito;
	}

	public String getDepartamento() {
		return departamento;
	}

	public String getProvincia() {
		return provincia;
	}

	@Override
	public Long getUltimoCorrelativo(String idDepartamento, String idProvincia,
			String idDistrito) {
		// return null;
		Criterio filtro = Criterio.forClass(Lugar.class);
		filtro.add(Restrictions.eq("estado", Boolean.TRUE));
		filtro.add(Restrictions.sqlRestriction("codlugar like '"
				+ idDepartamento + idProvincia + idDistrito + "%'"));
		filtro.addOrder(Order.desc("codigoLugar"));
		List<Lugar> lista = buscarPorCriteria(filtro);
		if (lista.size() > 0) {
			return Long
					.parseLong(lista.get(0).getCodigoLugar().substring(6, 9)) + 1L;
		}
		return 1L;
	}

}
